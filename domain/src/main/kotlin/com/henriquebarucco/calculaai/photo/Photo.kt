package com.henriquebarucco.calculaai.photo

import com.henriquebarucco.calculaai.price.PriceId
import com.henriquebarucco.calculaai.session.SessionId

class Photo(
    val priceId: PriceId,
    val sessionId: SessionId,
    val content: ByteArray,
) {
    companion object {
        fun new(
            priceId: PriceId,
            sessionId: SessionId,
            content: ByteArray,
        ): Photo =
            Photo(
                priceId = priceId,
                sessionId = sessionId,
                content = content,
            )
    }
}
