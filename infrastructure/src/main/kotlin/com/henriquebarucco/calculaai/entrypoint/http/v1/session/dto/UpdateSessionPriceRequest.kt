package com.henriquebarucco.calculaai.entrypoint.http.v1.session.dto

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.henriquebarucco.calculaai.session.update.dto.FailureUpdateSessionPriceCommand
import com.henriquebarucco.calculaai.session.update.dto.SuccessUpdateSessionPriceCommand
import com.henriquebarucco.calculaai.session.update.dto.UpdateSessionPriceCommand

@JsonTypeInfo(
    use = JsonTypeInfo.Id.DEDUCTION,
    include = JsonTypeInfo.As.PROPERTY,
)
@JsonSubTypes(
    JsonSubTypes.Type(value = SuccessUpdateSessionPriceRequest::class),
    JsonSubTypes.Type(value = FailureUpdateSessionPriceRequest::class),
)
sealed interface UpdateSessionPriceRequest {
    fun toCommand(
        priceId: String,
        sessionId: String,
    ): UpdateSessionPriceCommand
}

data class SuccessUpdateSessionPriceRequest(
    val name: String,
    val value: Double,
    val quantity: Int?,
) : UpdateSessionPriceRequest {
    override fun toCommand(
        priceId: String,
        sessionId: String,
    ) = SuccessUpdateSessionPriceCommand(
        sessionId = sessionId,
        priceId = priceId,
        name =
            name
                .split(" ")
                .filter { it.isNotBlank() }
                .joinToString(" ") { word ->
                    word.lowercase().replaceFirstChar { it.titlecase() }
                },
        value = value,
        quantity = quantity,
    )
}

data class FailureUpdateSessionPriceRequest(
    val error: Boolean,
) : UpdateSessionPriceRequest {
    override fun toCommand(
        priceId: String,
        sessionId: String,
    ) = FailureUpdateSessionPriceCommand(
        sessionId = sessionId,
        priceId = priceId,
    )
}
