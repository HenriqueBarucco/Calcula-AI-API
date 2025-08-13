package com.henriquebarucco.calculaai.entrypoint.http.v1.session

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile

@RequestMapping(value = ["/v1/sessions"])
interface AddSessionPriceController {
    @PostMapping(value = ["/prices"], consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun createPrice(
        @RequestParam("file") file: MultipartFile,
        @RequestParam("quantity", required = false, defaultValue = "1") quantity: Int,
        @RequestHeader("session") sessionId: String,
    ): ResponseEntity<Void>
}
