package com.henriquebarucco.calculaai.service.presenter

import com.henriquebarucco.calculaai.price.Price
import com.henriquebarucco.calculaai.price.enum.Status
import com.henriquebarucco.calculaai.session.Session

data class PriceAnnounceMessage(
    val sessionId: String,
    val priceId: String,
    val name: String?,
    val value: Double?,
    val status: Status,
    val total: Double,
) {
    companion object {
        fun fromDomain(
            session: Session,
            price: Price,
        ) = PriceAnnounceMessage(
            sessionId = session.id.value,
            priceId = price.id.value,
            name = price.name,
            value = price.value,
            status = price.status,
            total = session.total(),
        )
    }
}
