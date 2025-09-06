package com.henriquebarucco.calculaai.session.add

import com.henriquebarucco.calculaai.photo.Photo
import com.henriquebarucco.calculaai.photo.PhotoGateway
import com.henriquebarucco.calculaai.price.Price
import com.henriquebarucco.calculaai.price.PriceGateway
import com.henriquebarucco.calculaai.session.SessionGateway
import com.henriquebarucco.calculaai.session.SessionId
import com.henriquebarucco.calculaai.session.add.dto.AddSessionPriceCommand
import com.henriquebarucco.calculaai.shared.exceptions.ResourceNotFoundException
import com.henriquebarucco.calculaai.utils.Logger.Companion.getLogger

class DefaultAddSessionPriceUseCase(
    private val sessionGateway: SessionGateway,
    private val photoGateway: PhotoGateway,
    private val priceGateway: PriceGateway,
) : AddSessionPriceUseCase() {
    private val logger = getLogger()

    override fun execute(input: AddSessionPriceCommand) {
        val (sessionId, file, name, value, quantity) = input

        val session =
            this.sessionGateway.findById(SessionId.with(sessionId))
                ?: throw ResourceNotFoundException("Session with id $sessionId not found")

        if (file == null && (name == null || name.isEmpty() || value == null || value <= 0)) {
            this.logger.warn("Either a file or valid name and value must be provided")
            throw IllegalArgumentException("Either a file or valid name and value must be provided")
        }

        val price = Price.new(session.id, quantity)
        session.addPrice(price)

        if (name != null && name.isNotEmpty() && value != null && value > 0) {
            price.isSuccessful(name, value)
        }

        if (file != null) {
            val photo = Photo.new(price, session, file)
            this.photoGateway.save(photo)
        }

        this.priceGateway.announce(price, session)
        this.sessionGateway.save(session)
    }
}
