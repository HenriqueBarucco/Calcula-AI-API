package com.henriquebarucco.calculaai.session.delete

import com.henriquebarucco.calculaai.session.SessionGateway
import com.henriquebarucco.calculaai.session.SessionId
import com.henriquebarucco.calculaai.session.delete.dto.DeleteSessionPriceCommand
import com.henriquebarucco.calculaai.shared.exceptions.ResourceNotFoundException

class DefaultDeleteSessionPriceUseCase(
    private val sessionGateway: SessionGateway,
) : DeleteSessionPriceUseCase() {
    override fun execute(input: DeleteSessionPriceCommand) {
        val session =
            this.sessionGateway.findById(SessionId.with(input.sessionId))
                ?: throw ResourceNotFoundException("Session with id ${input.sessionId} not found")

        session.deletePrice(input.priceId)

        this.sessionGateway.save(session)
    }
}
