package com.henriquebarucco.calculaai.configuration

import com.henriquebarucco.calculaai.amqp.rabbitmq.RabbitmqEventService
import com.henriquebarucco.calculaai.configuration.annotations.ProcessPhotoQueue
import com.henriquebarucco.calculaai.configuration.properties.QueueProperties
import com.henriquebarucco.calculaai.service.EventService
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class EventConfig {
    @Bean
    @ProcessPhotoQueue
    fun processPhotoEventService(
        @ProcessPhotoQueue queueProperties: QueueProperties,
        rabbitTemplate: RabbitTemplate,
    ): EventService =
        RabbitmqEventService(
            rabbitTemplate = rabbitTemplate,
            routingKey = queueProperties.routingKey!!,
            exchange = queueProperties.exchange!!,
        )
}
