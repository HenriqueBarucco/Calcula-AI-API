package com.henriquebarucco.calculaai.configuration.annotations

import org.springframework.beans.factory.annotation.Qualifier

@Qualifier("processPhoto")
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD, AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER)
annotation class ProcessPhotoQueue
