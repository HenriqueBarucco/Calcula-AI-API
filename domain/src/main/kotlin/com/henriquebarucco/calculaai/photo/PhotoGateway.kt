package com.henriquebarucco.calculaai.photo

interface PhotoGateway {
    fun save(photo: Photo): Photo

    fun get(priceId: String): PhotoContent?
}
