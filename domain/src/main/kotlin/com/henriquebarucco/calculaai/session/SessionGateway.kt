package com.henriquebarucco.calculaai.session

interface SessionGateway {
    fun save(session: Session): Session

    fun findById(sessionId: SessionId): Session?
}
