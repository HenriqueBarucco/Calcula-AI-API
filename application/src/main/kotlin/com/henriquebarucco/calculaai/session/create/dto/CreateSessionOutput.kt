package com.henriquebarucco.calculaai.session.create.dto

import com.henriquebarucco.calculaai.session.Session

data class CreateSessionOutput(
    val id: String,
) {
    constructor(session: Session) : this(
        id = session.id.value,
    )
}
