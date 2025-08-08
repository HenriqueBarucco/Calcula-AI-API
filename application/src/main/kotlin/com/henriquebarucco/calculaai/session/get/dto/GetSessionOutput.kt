package com.henriquebarucco.calculaai.session.get.dto

import com.henriquebarucco.calculaai.price.enum.Status
import com.henriquebarucco.calculaai.session.Session

data class GetSessionOutput(
    val id: String,
    val total: Double,
    val prices: List<PriceOutput>,
) {
    constructor(session: Session) : this(
        id = session.id.value,
        total =
            session.prices
                .filter { it.status == Status.SUCCESS }
                .sumOf { (it.value ?: 0.0) * it.quantity },
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
