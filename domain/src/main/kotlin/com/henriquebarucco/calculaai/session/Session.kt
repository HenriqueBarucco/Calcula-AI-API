package com.henriquebarucco.calculaai.session

import com.henriquebarucco.calculaai.price.Price
import com.henriquebarucco.calculaai.price.enum.Status

class Session(
    val id: SessionId,
    val prices: MutableList<Price>,
) {
    companion object {
        fun new(): Session =
            Session(
                id = SessionId.unique(),
                prices = mutableListOf(),
            )

        fun with(
            id: SessionId,
            prices: List<Price>,
        ): Session =
            Session(
                id = id,
                prices = prices.toMutableList(),
            )
    }

    fun addPrice(price: Price) {
        prices.add(price)
    }

    fun updatePrice(
        priceId: String,
        name: String,
        value: Double,
    ) {
        val existingPrice =
            prices.find { it.id.value == priceId }
                ?: throw IllegalArgumentException("Price with id $priceId not found in session ${id.value}")

        existingPrice.name = name
        existingPrice.value = value
        existingPrice.status = Status.SUCCESS
    }
}
