package com.henriquebarucco.calculaai.session.update

import com.henriquebarucco.calculaai.session.SessionGateway
import com.henriquebarucco.calculaai.session.SessionId
import com.henriquebarucco.calculaai.session.update.dto.UpdateSessionPriceCommand
import com.henriquebarucco.calculaai.shared.exceptions.ResourceNotFoundException

class DefaultUpdateSessionPriceUseCase(
    private val sessionGateway: SessionGateway,
) : UpdateSessionPriceUseCase() {
    override fun execute(input: UpdateSessionPriceCommand) {
        val (sessionId, priceId, name, value) = input

        val session =
            this.sessionGateway.findById(SessionId.with(sessionId))
                ?: throw ResourceNotFoundException("Session with id $sessionId not found")

        session.updatePrice(
            priceId = priceId,
            name = name,
            value = value,
        )

        this.sessionGateway.save(session)
    }
}
