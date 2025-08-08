package com.henriquebarucco.calculaai.session.get

import com.henriquebarucco.calculaai.UseCase
import com.henriquebarucco.calculaai.session.get.dto.GetSessionCommand
import com.henriquebarucco.calculaai.session.get.dto.GetSessionOutput

abstract class GetSessionUseCase : UseCase<GetSessionCommand, GetSessionOutput>()
