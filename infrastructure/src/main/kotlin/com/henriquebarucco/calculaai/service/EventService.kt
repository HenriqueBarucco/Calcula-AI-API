package com.henriquebarucco.calculaai.service

interface EventService {
    fun <T> publish(message: T)
}
