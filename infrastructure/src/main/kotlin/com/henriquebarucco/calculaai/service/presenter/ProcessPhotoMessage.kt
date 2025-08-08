package com.henriquebarucco.calculaai.service.presenter

import com.fasterxml.jackson.annotation.JsonProperty
import com.henriquebarucco.calculaai.photo.Photo

data class ProcessPhotoMessage(
    @JsonProperty("photo_id")
    val photoId: String,
    @JsonProperty("session_id")
    val sessionId: String,
    val type: String,
)

fun Photo.toProcessPhotoMessage(type: String) =
    ProcessPhotoMessage(
        photoId = this.priceId.value,
        sessionId = this.sessionId.value,
        type = type,
    )
