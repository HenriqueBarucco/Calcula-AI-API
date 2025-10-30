package com.henriquebarucco.calculaai.session.photo.get.dto

data class GetSessionPricePhotoCommand(
    val sessionId: String,
    val priceId: String,
)
