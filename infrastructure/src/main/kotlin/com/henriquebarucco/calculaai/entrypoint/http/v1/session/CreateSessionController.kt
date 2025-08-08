package com.henriquebarucco.calculaai.entrypoint.http.v1.session

import com.henriquebarucco.calculaai.entrypoint.http.v1.session.dto.CreateSessionResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping(value = ["/v1/sessions"])
interface CreateSessionController {
    @PostMapping
    fun createSession(): ResponseEntity<CreateSessionResponse>
}
