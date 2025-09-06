package com.henriquebarucco.calculaai.service.presenter

import com.fasterxml.jackson.annotation.JsonProperty
import com.henriquebarucco.calculaai.photo.Photo

data class ProcessPhotoMessage(
    @JsonProperty("photo_id")
    val photoId: String,
    val session: ProcessPhotoSessionMessage,
    val type: String,
    val quantity: Int,
)

data class ProcessPhotoSessionMessage(
    val id: String,
    @JsonProperty("has_club")
    val hasClub: Boolean,
)

fun Photo.toProcessPhotoMessage(type: String) =
    ProcessPhotoMessage(
        photoId = this.price.id.value,
        session =
            ProcessPhotoSessionMessage(
                id = this.session.id.value,
                hasClub = this.session.hasClub,
            ),
        type = type,
        quantity = this.price.quantity,
    )
