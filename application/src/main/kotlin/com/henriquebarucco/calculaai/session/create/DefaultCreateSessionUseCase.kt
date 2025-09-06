package com.henriquebarucco.calculaai.session.create

import com.henriquebarucco.calculaai.session.Session
import com.henriquebarucco.calculaai.session.SessionGateway
import com.henriquebarucco.calculaai.session.create.dto.CreateSessionCommand
import com.henriquebarucco.calculaai.session.create.dto.CreateSessionOutput

class DefaultCreateSessionUseCase(
    private val sessionGateway: SessionGateway,
) : CreateSessionUseCase() {
    override fun execute(input: CreateSessionCommand): CreateSessionOutput {
        val (hasClub) = input
        val session = Session.new(hasClub)

        val savedSession = this.sessionGateway.save(session)

        return CreateSessionOutput(savedSession)
    }
}
