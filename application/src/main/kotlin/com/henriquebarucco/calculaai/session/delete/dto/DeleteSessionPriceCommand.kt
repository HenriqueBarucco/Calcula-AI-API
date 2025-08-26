package com.henriquebarucco.calculaai.session.delete.dto

data class DeleteSessionPriceCommand(
    val sessionId: String,
    val priceId: String,
)
