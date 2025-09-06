package com.henriquebarucco.calculaai.photo

import com.henriquebarucco.calculaai.price.Price
import com.henriquebarucco.calculaai.session.Session

class Photo(
    val price: Price,
    val session: Session,
    val content: ByteArray,
) {
    companion object {
        fun new(
            price: Price,
            session: Session,
            content: ByteArray,
        ): Photo =
            Photo(
                price = price,
                session = session,
                content = content,
            )
    }
}
