package com.henriquebarucco.calculaai.entrypoint.http.v1.session.dto

import com.henriquebarucco.calculaai.session.create.dto.CreateSessionOutput

data class CreateSessionResponse(
    val id: String,
) {
    companion object {
        fun fromOutput(output: CreateSessionOutput) =
            CreateSessionResponse(
                id = output.id,
            )
    }
}
