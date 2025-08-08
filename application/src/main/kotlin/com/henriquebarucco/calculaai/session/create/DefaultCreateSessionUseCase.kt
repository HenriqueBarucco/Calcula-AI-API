package com.henriquebarucco.calculaai.session.create

import com.henriquebarucco.calculaai.session.Session
import com.henriquebarucco.calculaai.session.SessionGateway
import com.henriquebarucco.calculaai.session.create.dto.CreateSessionOutput

class DefaultCreateSessionUseCase(
    private val sessionGateway: SessionGateway,
) : CreateSessionUseCase() {
    override fun execute(): CreateSessionOutput {
        val session = Session.Companion.new()

        val savedSession = this.sessionGateway.save(session)

        return CreateSessionOutput(savedSession)
    }
}
