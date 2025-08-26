package com.henriquebarucco.calculaai.entrypoint.http.v1.session.impl

import com.henriquebarucco.calculaai.entrypoint.http.v1.session.AddSessionPriceController
import com.henriquebarucco.calculaai.session.add.AddSessionPriceUseCase
import com.henriquebarucco.calculaai.session.add.dto.AddSessionPriceCommand
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.util.concurrent.CompletableFuture

@RestController
class AddSessionPriceControllerImpl(
    private val addSessionPriceUseCase: AddSessionPriceUseCase,
) : AddSessionPriceController {
    override fun createPrice(
        file: MultipartFile?,
        name: String?,
        value: Double?,
        quantity: Int,
        sessionId: String,
    ): ResponseEntity<Void> {
        CompletableFuture.runAsync {
            val command =
                AddSessionPriceCommand(
                    sessionId = sessionId,
                    file = file?.bytes,
                    name =
                        name
                            ?.split(" ")
                            ?.filter { it.isNotBlank() }
                            ?.joinToString(" ") { word ->
                                word.lowercase().replaceFirstChar { it.titlecase() }
                            },
                    value = value,
                    quantity = quantity,
                )
            this.addSessionPriceUseCase.execute(command)
        }

        return ResponseEntity.accepted().build()
    }
}
