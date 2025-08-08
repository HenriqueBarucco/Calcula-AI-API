package com.henriquebarucco.calculaai.shared.exceptions

class ResourceConflictException(
    override val message: String,
    override val cause: Throwable? = null,
) : RuntimeException(message, cause)
