package com.henriquebarucco.calculaai.service

import com.henriquebarucco.calculaai.configuration.annotations.ProcessPhotoQueue
import com.henriquebarucco.calculaai.photo.Photo
import com.henriquebarucco.calculaai.photo.PhotoGateway
import com.henriquebarucco.calculaai.service.presenter.toProcessPhotoMessage
import com.henriquebarucco.calculaai.shared.utils.Logger.Companion.getLogger
import org.springframework.stereotype.Service
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.PutObjectRequest

@Service
class PhotoService(
    private val s3Client: S3Client,
    @ProcessPhotoQueue private val processPhotoService: EventService,
) : PhotoGateway {
    private val logger = getLogger()
    private val bucketName = "calcula-ai"
    private val type = "jpg"

    override fun save(photo: Photo): Photo {
        this.logger.info("Saving photo to S3 bucket: $bucketName")

        val key = "${photo.price.id.value}.$type"

        this.s3Client.putObject(
            PutObjectRequest
                .builder()
                .bucket(bucketName)
                .key(key)
                .contentType("image/jpg")
                .build(),
            RequestBody
                .fromBytes(photo.content),
        )

        this.logger.info("Photo saved successfully: $key")

        this.processPhotoService.publish(photo.toProcessPhotoMessage(type))
        this.logger.info("Photo processing message published for: ${photo.price.id.value}")

        return photo
    }
}
