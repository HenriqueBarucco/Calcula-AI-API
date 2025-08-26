package com.henriquebarucco.calculaai.entrypoint.http.v1.session.impl

import com.henriquebarucco.calculaai.entrypoint.http.v1.session.DeleteSessionPriceController
import com.henriquebarucco.calculaai.session.delete.DeleteSessionPriceUseCase
import com.henriquebarucco.calculaai.session.delete.dto.DeleteSessionPriceCommand
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class DeleteSessionPriceControllerImpl(
    private val deleteSessionPriceUseCase: DeleteSessionPriceUseCase,
) : DeleteSessionPriceController {
    override fun deletePrice(
        sessionId: String,
        priceId: String,
    ): ResponseEntity<Void> {
        this.deleteSessionPriceUseCase.execute(DeleteSessionPriceCommand(sessionId, priceId))

        return ResponseEntity.noContent().build()
    }
}
