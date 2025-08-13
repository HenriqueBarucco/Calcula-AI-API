package com.henriquebarucco.calculaai.session.add

import com.henriquebarucco.calculaai.photo.Photo
import com.henriquebarucco.calculaai.photo.PhotoGateway
import com.henriquebarucco.calculaai.price.Price
import com.henriquebarucco.calculaai.session.SessionGateway
import com.henriquebarucco.calculaai.session.SessionId
import com.henriquebarucco.calculaai.session.add.dto.AddSessionPriceCommand
import com.henriquebarucco.calculaai.shared.exceptions.ResourceNotFoundException

class DefaultAddSessionPriceUseCase(
    private val sessionGateway: SessionGateway,
    private val photoGateway: PhotoGateway,
) : AddSessionPriceUseCase() {
    override fun execute(input: AddSessionPriceCommand) {
        val (sessionId, file, quantity) = input

        val session =
            this.sessionGateway.findById(SessionId.with(sessionId))
                ?: throw ResourceNotFoundException("Session with id $sessionId not found")

        val price = Price.Companion.new(session.id, quantity)

        session.addPrice(price)
        this.sessionGateway.save(session)

        val photo = Photo.new(price.id, session.id, file)
        this.photoGateway.save(photo)
    }
}
