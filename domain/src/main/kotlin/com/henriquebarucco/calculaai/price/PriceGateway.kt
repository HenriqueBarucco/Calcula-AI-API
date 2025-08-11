package com.henriquebarucco.calculaai.price

import com.henriquebarucco.calculaai.session.Session

interface PriceGateway {
    fun announce(
        price: Price,
        session: Session,
    )
}
