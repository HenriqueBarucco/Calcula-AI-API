package com.henriquebarucco.calculaai

abstract class UseCase<IN, OUT> {
    abstract fun execute(input: IN): OUT
}
