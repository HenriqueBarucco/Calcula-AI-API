package com.henriquebarucco.calculaai.entrypoint.http.v1.session.impl

import com.henriquebarucco.calculaai.entrypoint.http.v1.session.GetSessionController
import com.henriquebarucco.calculaai.entrypoint.http.v1.session.dto.GetSessionResponse
import com.henriquebarucco.calculaai.session.get.GetSessionUseCase
import com.henriquebarucco.calculaai.session.get.dto.GetSessionCommand
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class GetSessionControllerImpl(
    private val getSessionUseCase: GetSessionUseCase,
) : GetSessionController {
    override fun getSession(sessionId: String): ResponseEntity<GetSessionResponse> {
        val output = this.getSessionUseCase.execute(GetSessionCommand(sessionId))

        val response = GetSessionResponse.fromOutput(output)
        return ResponseEntity.ok(response)
    }
}
