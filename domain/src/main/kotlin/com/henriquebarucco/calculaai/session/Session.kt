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
                prices = prices.sortedByDescending { it.createdAt }.toMutableList(),
            )
    }

    fun total(): Double =
        this.prices
            .filter { it.status == Status.SUCCESS }
            .sumOf { (it.value ?: 0.0) * it.quantity }

    fun addPrice(price: Price) {
        prices.add(price)
    }

    fun updatePriceSuccessfully(
        priceId: String,
        name: String,
        value: Double,
    ) {
        val existingPrice = this.getPrice(priceId)
        existingPrice.isSuccessful(name, value)
    }

    fun updatePriceFailed(priceId: String) {
        val existingPrice = this.getPrice(priceId)
        existingPrice.isFailed()
    }

    fun getPrice(priceId: String): Price =
        this.prices.find { it.id.value == priceId }
            ?: throw IllegalArgumentException("Price with id $priceId not found in session ${id.value}")

    fun deletePrice(priceId: String) {
        val existingPrice = this.getPrice(priceId)
        this.prices.remove(existingPrice)
    }
}
