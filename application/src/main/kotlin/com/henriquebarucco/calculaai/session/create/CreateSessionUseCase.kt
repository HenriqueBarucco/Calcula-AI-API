package com.henriquebarucco.calculaai.session.create

import com.henriquebarucco.calculaai.UseCase
import com.henriquebarucco.calculaai.session.create.dto.CreateSessionCommand
import com.henriquebarucco.calculaai.session.create.dto.CreateSessionOutput

abstract class CreateSessionUseCase : UseCase<CreateSessionCommand, CreateSessionOutput>()
