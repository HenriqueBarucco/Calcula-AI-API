package com.henriquebarucco.calculaai.entrypoint.http.v1.session.dto

import com.henriquebarucco.calculaai.session.get.dto.GetSessionOutput

data class GetSessionResponse(
    val id: String,
    val hasClub: Boolean,
    val total: Double,
    val prices: List<GetSessionPriceResponse>,
) {
    companion object {
        fun fromOutput(output: GetSessionOutput) =
            GetSessionResponse(
                id = output.id,
                hasClub = output.hasClub,
                total = output.total,
                prices =
                    output.prices.map {
                        GetSessionPriceResponse(
                            id = it.id,
                            name = it.name,
                            value = it.value,
                            quantity = it.quantity,
                            status = it.status,
                        )
                    },
            )
    }
}

data class GetSessionPriceResponse(
    val id: String,
    val name: String?,
    val value: Double?,
    val quantity: Int,
    val status: String,
)
