package com.henriquebarucco.calculaai.session.update.dto

sealed interface UpdateSessionPriceCommand {
    val sessionId: String
    val priceId: String
}

data class SuccessUpdateSessionPriceCommand(
    override val sessionId: String,
    override val priceId: String,
    val name: String,
    val value: Double,
) : UpdateSessionPriceCommand

data class FailureUpdateSessionPriceCommand(
    override val sessionId: String,
    override val priceId: String,
) : UpdateSessionPriceCommand
