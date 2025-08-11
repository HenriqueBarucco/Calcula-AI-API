package com.henriquebarucco.calculaai.service

import com.henriquebarucco.calculaai.price.Price
import com.henriquebarucco.calculaai.price.PriceGateway
import com.henriquebarucco.calculaai.service.presenter.PriceAnnounceMessage
import com.henriquebarucco.calculaai.session.Session
import com.henriquebarucco.calculaai.shared.utils.Logger.Companion.getLogger
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service

@Service
class PriceService(
    private val messagingTemplate: SimpMessagingTemplate,
) : PriceGateway {
    private val logger = getLogger()

    override fun announce(
        price: Price,
        session: Session,
    ) {
        this.logger.info("Announcing price: ${price.id.value} for session: ${session.id.value}")

        val message = PriceAnnounceMessage.fromDomain(session, price)
        this.messagingTemplate.convertAndSend("/topic/${session.id.value}", message)
    }
}
