package com.henriquebarucco.calculaai.configuration

import com.henriquebarucco.calculaai.configuration.properties.S3Properties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.S3Configuration
import java.net.URI

@Configuration
class S3ClientConfig {
    @Bean
    @ConfigurationProperties(prefix = "minio")
    fun s3Properties() = S3Properties()

    @Bean
    fun s3Client(s3Properties: S3Properties): S3Client? {
        val credentials = AwsBasicCredentials.create(s3Properties.accessKey, s3Properties.secretKey)

        return S3Client
            .builder()
            .endpointOverride(URI.create(s3Properties.endpoint!!))
            .credentialsProvider(StaticCredentialsProvider.create(credentials))
            .region(Region.of(s3Properties.region))
            .serviceConfiguration(
                S3Configuration
                    .builder()
                    .pathStyleAccessEnabled(true)
                    .build(),
            ).build()
    }
}
