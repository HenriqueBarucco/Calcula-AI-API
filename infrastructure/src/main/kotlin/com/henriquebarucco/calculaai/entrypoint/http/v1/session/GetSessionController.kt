package com.henriquebarucco.calculaai.entrypoint.http.v1.session

import com.henriquebarucco.calculaai.entrypoint.http.v1.session.dto.GetSessionResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping(value = ["/v1/sessions"])
interface GetSessionController {
    @GetMapping()
    fun getSession(
        @RequestHeader("session") sessionId: String,
    ): ResponseEntity<GetSessionResponse>
}
