package com.henriquebarucco.calculaai

abstract class UnitUseCase<IN> {
    abstract fun execute(input: IN)
}
