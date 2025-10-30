package com.henriquebarucco.calculaai.entrypoint.http.v1.session

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping(value = ["/v1/sessions"])
interface GetSessionPricePhotoController {
    @GetMapping(value = ["/prices/{priceId}/photo"])
    fun getPhoto(
        @RequestHeader("session") sessionId: String,
        @PathVariable priceId: String,
    ): ResponseEntity<ByteArray>
}
