package com.henriquebarucco.calculaai.session.photo.get

import com.henriquebarucco.calculaai.photo.PhotoGateway
import com.henriquebarucco.calculaai.session.SessionGateway
import com.henriquebarucco.calculaai.session.SessionId
import com.henriquebarucco.calculaai.session.photo.get.dto.GetSessionPricePhotoCommand
import com.henriquebarucco.calculaai.session.photo.get.dto.GetSessionPricePhotoOutput
import com.henriquebarucco.calculaai.shared.exceptions.ResourceNotFoundException

class DefaultGetSessionPricePhotoUseCase(
    private val sessionGateway: SessionGateway,
    private val photoGateway: PhotoGateway,
) : GetSessionPricePhotoUseCase() {
    override fun execute(input: GetSessionPricePhotoCommand): GetSessionPricePhotoOutput {
        val session =
            this.sessionGateway.findById(SessionId.with(input.sessionId))
                ?: throw ResourceNotFoundException("Session with id ${input.sessionId} not found")

        val hasPrice = session.prices.any { it.id.value == input.priceId }
        if (!hasPrice) throw ResourceNotFoundException("Price with id ${input.priceId} not found in session ${input.sessionId}")

        val photo = this.photoGateway.get(input.priceId) ?: throw ResourceNotFoundException("Photo for price ${input.priceId} not found")
        return GetSessionPricePhotoOutput(bytes = photo.bytes, contentType = photo.contentType)
    }
}
