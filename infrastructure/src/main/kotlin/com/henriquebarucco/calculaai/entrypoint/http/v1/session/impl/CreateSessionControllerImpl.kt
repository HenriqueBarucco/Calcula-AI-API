package com.henriquebarucco.calculaai.entrypoint.http.v1.session.impl

import com.henriquebarucco.calculaai.entrypoint.http.v1.session.CreateSessionController
import com.henriquebarucco.calculaai.entrypoint.http.v1.session.dto.CreateSessionResponse
import com.henriquebarucco.calculaai.session.create.CreateSessionUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class CreateSessionControllerImpl(
    private val createSessionUseCase: CreateSessionUseCase,
) : CreateSessionController {
    override fun createSession(): ResponseEntity<CreateSessionResponse> {
        val session = this.createSessionUseCase.execute()

        val response = CreateSessionResponse.fromOutput(session)
        return ResponseEntity.ok(response)
    }
}
