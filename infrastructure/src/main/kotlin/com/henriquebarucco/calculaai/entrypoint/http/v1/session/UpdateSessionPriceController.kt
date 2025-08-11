package com.henriquebarucco.calculaai.entrypoint.http.v1.session

import com.henriquebarucco.calculaai.entrypoint.http.v1.session.dto.UpdateSessionPriceRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping(value = ["/v1/sessions"])
interface UpdateSessionPriceController {
    @PatchMapping(value = ["/prices/{priceId}"])
    fun updatePrice(
        @RequestBody() request: UpdateSessionPriceRequest,
        @RequestHeader("session") sessionId: String,
        @PathVariable priceId: String,
    ): ResponseEntity<Void>
}
