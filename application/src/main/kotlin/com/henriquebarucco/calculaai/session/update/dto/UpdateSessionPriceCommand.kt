package com.henriquebarucco.calculaai.session.update.dto

data class UpdateSessionPriceCommand(
    val sessionId: String,
    val priceId: String,
    val name: String,
    val value: Double,
)
