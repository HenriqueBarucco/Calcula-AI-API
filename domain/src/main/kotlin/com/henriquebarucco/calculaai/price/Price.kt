package com.henriquebarucco.calculaai.price

import com.henriquebarucco.calculaai.price.enum.Status
import com.henriquebarucco.calculaai.price.enum.Status.PENDING
import com.henriquebarucco.calculaai.session.SessionId

class Price(
    val id: PriceId,
    val sessionId: SessionId,
    var name: String?,
    var value: Double?,
    val quantity: Int,
    var status: Status,
) {
    companion object {
        fun new(
            sessionId: SessionId,
            quantity: Int,
        ): Price =
            Price(
                id = PriceId.unique(),
                sessionId = sessionId,
                name = null,
                value = null,
                quantity = quantity,
                status = PENDING,
            )
    }
}
