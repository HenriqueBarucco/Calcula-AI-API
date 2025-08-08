package com.henriquebarucco.calculaai.session

import com.henriquebarucco.calculaai.shared.utils.DomainId

data class SessionId(
    val value: String,
) : DomainId() {
    companion object {
        fun unique(): SessionId = SessionId(generate())

        fun with(value: String): SessionId {
            validate<SessionId>(value)
            return SessionId(value)
        }
    }
}
