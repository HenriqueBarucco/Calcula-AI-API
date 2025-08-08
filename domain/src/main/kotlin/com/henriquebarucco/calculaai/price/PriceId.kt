package com.henriquebarucco.calculaai.price

import com.henriquebarucco.calculaai.shared.utils.DomainId

data class PriceId(
    val value: String,
) : DomainId() {
    companion object {
        fun unique(): PriceId = PriceId(generate())

        fun with(value: String): PriceId {
            validate<PriceId>(value)
            return PriceId(value)
        }
    }
}
