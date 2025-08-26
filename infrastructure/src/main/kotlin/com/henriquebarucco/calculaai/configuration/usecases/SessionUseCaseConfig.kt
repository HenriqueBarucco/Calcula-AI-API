package com.henriquebarucco.calculaai.configuration.usecases

import com.henriquebarucco.calculaai.photo.PhotoGateway
import com.henriquebarucco.calculaai.price.PriceGateway
import com.henriquebarucco.calculaai.session.SessionGateway
import com.henriquebarucco.calculaai.session.add.AddSessionPriceUseCase
import com.henriquebarucco.calculaai.session.add.DefaultAddSessionPriceUseCase
import com.henriquebarucco.calculaai.session.create.CreateSessionUseCase
import com.henriquebarucco.calculaai.session.create.DefaultCreateSessionUseCase
import com.henriquebarucco.calculaai.session.delete.DefaultDeleteSessionPriceUseCase
import com.henriquebarucco.calculaai.session.delete.DeleteSessionPriceUseCase
import com.henriquebarucco.calculaai.session.get.DefaultGetSessionUseCase
import com.henriquebarucco.calculaai.session.get.GetSessionUseCase
import com.henriquebarucco.calculaai.session.update.DefaultUpdateSessionPriceUseCase
import com.henriquebarucco.calculaai.session.update.UpdateSessionPriceUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SessionUseCaseConfig(
    private val sessionGateway: SessionGateway,
    private val photoGateway: PhotoGateway,
    private val priceGateway: PriceGateway,
) {
    @Bean
    fun createSessionUseCase(): CreateSessionUseCase = DefaultCreateSessionUseCase(sessionGateway)

    @Bean
    fun addSessionPriceUseCase(): AddSessionPriceUseCase = DefaultAddSessionPriceUseCase(sessionGateway, photoGateway, priceGateway)

    @Bean
    fun updateSessionPriceUseCase(): UpdateSessionPriceUseCase = DefaultUpdateSessionPriceUseCase(sessionGateway, priceGateway)

    @Bean
    fun deleteSessionPriceUseCase(): DeleteSessionPriceUseCase = DefaultDeleteSessionPriceUseCase(sessionGateway)

    @Bean
    fun getSessionUseCase(): GetSessionUseCase = DefaultGetSessionUseCase(sessionGateway)
}
