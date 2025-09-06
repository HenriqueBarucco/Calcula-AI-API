package com.henriquebarucco.calculaai.entrypoint.http.v1.session.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.henriquebarucco.calculaai.session.create.dto.CreateSessionCommand

data class CreateSessionRequest(
    @JsonProperty(value = "has_club", required = false, defaultValue = "true")
    val hasClub: Boolean,
) {
    fun toCommand() =
        CreateSessionCommand(
            hasClub = this.hasClub,
        )
}
