package com.henriquebarucco.calculaai.session.update

import com.henriquebarucco.calculaai.price.PriceGateway
import com.henriquebarucco.calculaai.session.SessionGateway
import com.henriquebarucco.calculaai.session.SessionId
import com.henriquebarucco.calculaai.session.update.dto.FailureUpdateSessionPriceCommand
import com.henriquebarucco.calculaai.session.update.dto.SuccessUpdateSessionPriceCommand
import com.henriquebarucco.calculaai.session.update.dto.UpdateSessionPriceCommand
import com.henriquebarucco.calculaai.shared.exceptions.ResourceNotFoundException

class DefaultUpdateSessionPriceUseCase(
    private val sessionGateway: SessionGateway,
    private val priceGateway: PriceGateway,
) : UpdateSessionPriceUseCase() {
    override fun execute(input: UpdateSessionPriceCommand) {
        val session =
            this.sessionGateway.findById(SessionId.with(input.sessionId))
                ?: throw ResourceNotFoundException("Session with id ${input.sessionId} not found")

        when (input) {
            is SuccessUpdateSessionPriceCommand -> {
                session.updatePriceSuccessfully(
                    priceId = input.priceId,
                    name = input.name,
                    value = input.value,
                )
            }

            is FailureUpdateSessionPriceCommand -> {
                session.updatePriceFailed(
                    priceId = input.priceId,
                )
            }
        }

        this.priceGateway.announce(session.getPrice(input.priceId), session)
        this.sessionGateway.save(session)
    }
}
