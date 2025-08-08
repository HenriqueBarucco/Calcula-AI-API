package com.henriquebarucco.calculaai.session.get

import com.henriquebarucco.calculaai.session.SessionGateway
import com.henriquebarucco.calculaai.session.SessionId
import com.henriquebarucco.calculaai.session.get.dto.GetSessionCommand
import com.henriquebarucco.calculaai.session.get.dto.GetSessionOutput
import com.henriquebarucco.calculaai.shared.exceptions.ResourceNotFoundException

class DefaultGetSessionUseCase(
    private val sessionGateway: SessionGateway,
) : GetSessionUseCase() {
    override fun execute(input: GetSessionCommand): GetSessionOutput {
        val (sessionId) = input

        val session =
            this.sessionGateway.findById(SessionId.with(sessionId))
                ?: throw ResourceNotFoundException("Session with id $sessionId not found")

        return GetSessionOutput(session)
    }
}
