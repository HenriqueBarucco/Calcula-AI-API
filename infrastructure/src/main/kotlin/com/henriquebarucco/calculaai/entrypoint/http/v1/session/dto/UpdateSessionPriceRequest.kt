package com.henriquebarucco.calculaai.entrypoint.http.v1.session.dto

import com.henriquebarucco.calculaai.session.update.dto.UpdateSessionPriceCommand

data class UpdateSessionPriceRequest(
    val priceId: String,
    val name: String,
    val value: Double,
)

fun UpdateSessionPriceRequest.toCommand(sessionId: String) =
    UpdateSessionPriceCommand(
        sessionId = sessionId,
        priceId = this.priceId,
        name = this.name,
        value = this.value,
    )
