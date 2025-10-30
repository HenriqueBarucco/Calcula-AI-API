package com.henriquebarucco.calculaai.session.photo.get

import com.henriquebarucco.calculaai.UseCase
import com.henriquebarucco.calculaai.session.photo.get.dto.GetSessionPricePhotoCommand
import com.henriquebarucco.calculaai.session.photo.get.dto.GetSessionPricePhotoOutput

abstract class GetSessionPricePhotoUseCase : UseCase<GetSessionPricePhotoCommand, GetSessionPricePhotoOutput>()
