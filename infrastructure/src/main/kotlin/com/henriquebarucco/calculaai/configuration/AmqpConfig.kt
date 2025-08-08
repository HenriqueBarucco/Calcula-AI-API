package com.henriquebarucco.calculaai.configuration

import com.henriquebarucco.calculaai.configuration.annotations.ProcessPhotoQueue
import com.henriquebarucco.calculaai.configuration.properties.QueueProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AmqpConfig {
    @Bean
    @ProcessPhotoQueue
    @ConfigurationProperties(prefix = "rabbitmq.queues.process-photo")
    fun processPhotoQueueProperties() = QueueProperties()
}
