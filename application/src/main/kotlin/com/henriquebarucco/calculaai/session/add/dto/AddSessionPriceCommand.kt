package com.henriquebarucco.calculaai.session.add.dto

data class AddSessionPriceCommand(
    val sessionId: String,
    val file: ByteArray,
    val quantity: Int,
)
