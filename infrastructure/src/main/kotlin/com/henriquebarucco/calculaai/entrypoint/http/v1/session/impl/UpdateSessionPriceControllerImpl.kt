package com.henriquebarucco.calculaai.entrypoint.http.v1.session.impl

import com.henriquebarucco.calculaai.entrypoint.http.v1.session.UpdateSessionPriceController
import com.henriquebarucco.calculaai.entrypoint.http.v1.session.dto.UpdateSessionPriceRequest
import com.henriquebarucco.calculaai.session.update.UpdateSessionPriceUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class UpdateSessionPriceControllerImpl(
    private val updateSessionPriceUseCase: UpdateSessionPriceUseCase,
) : UpdateSessionPriceController {
    override fun updatePrice(
        request: UpdateSessionPriceRequest,
        sessionId: String,
        priceId: String,
    ): ResponseEntity<Void> {
        this.updateSessionPriceUseCase.execute(request.toCommand(priceId, sessionId))

        return ResponseEntity.noContent().build()
    }
}
