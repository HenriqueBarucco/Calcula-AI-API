package com.henriquebarucco.calculaai.amqp.rabbitmq

import com.henriquebarucco.calculaai.service.EventService
import com.henriquebarucco.calculaai.shared.utils.Json
import org.springframework.amqp.rabbit.core.RabbitTemplate

class RabbitmqEventService(
    private val rabbitTemplate: RabbitTemplate,
    private val exchange: String,
    private val routingKey: String,
) : EventService {
    override fun <T> publish(message: T) {
        this.rabbitTemplate.convertAndSend(exchange, routingKey, Json.writeValueAsString(message)!!)
    }
}
