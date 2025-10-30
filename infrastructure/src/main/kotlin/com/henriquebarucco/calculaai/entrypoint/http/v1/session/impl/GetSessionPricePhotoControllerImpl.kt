package com.henriquebarucco.calculaai.entrypoint.http.v1.session.impl

import com.henriquebarucco.calculaai.entrypoint.http.v1.session.GetSessionPricePhotoController
import com.henriquebarucco.calculaai.session.photo.get.GetSessionPricePhotoUseCase
import com.henriquebarucco.calculaai.session.photo.get.dto.GetSessionPricePhotoCommand
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class GetSessionPricePhotoControllerImpl(
    private val getSessionPricePhotoUseCase: GetSessionPricePhotoUseCase,
) : GetSessionPricePhotoController {
    override fun getPhoto(
        sessionId: String,
        priceId: String,
    ): ResponseEntity<ByteArray> {
        val output = this.getSessionPricePhotoUseCase.execute(GetSessionPricePhotoCommand(sessionId, priceId))

        val contentType =
            try {
                MediaType.parseMediaType(output.contentType)
            } catch (_: Exception) {
                MediaType.IMAGE_JPEG
            }

        return ResponseEntity.ok().contentType(contentType).body(output.bytes)
    }
}
