package com.henriquebarucco.calculaai

abstract class NullaryUseCase<OUT> {
    abstract fun execute(): OUT
}
