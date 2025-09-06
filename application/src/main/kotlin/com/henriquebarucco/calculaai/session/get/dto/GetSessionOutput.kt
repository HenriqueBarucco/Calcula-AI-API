package com.henriquebarucco.calculaai.session.get.dto

import com.henriquebarucco.calculaai.session.Session

data class GetSessionOutput(
    val id: String,
    val hasClub: Boolean,
    val total: Double,
    val prices: List<PriceOutput>,
) {
    constructor(session: Session) : this(
        id = session.id.value,
        hasClub = session.hasClub,
        total = session.total(),
        prices =
            session.prices.map {
                PriceOutput(
                    id = it.id.value,
                    name = it.name,
                    value = it.value,
                    quantity = it.quantity,
                    status = it.status.name,
                )
            },
    )
}

data class PriceOutput(
    val id: String,
    val name: String?,
    val value: Double?,
    val quantity: Int,
    val status: String,
)
