package com.henriquebarucco.calculaai.configuration

import org.springframework.context.annotation.Configuration

@Configuration
class RedisConfig {
//    @Bean
//    fun redisConnectionFactory(): RedisConnectionFactory = LettuceConnectionFactory("localhost", 6379)
//
//    @Bean
//    fun objectMapper(): ObjectMapper =
//        jacksonObjectMapper()
//            .registerModule(KotlinModule()) // jackson-module-kotlin
//            .registerModule(JavaTimeModule()) // JavaTime if you use java.time types
//            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
//
//    @Bean
//    fun redisTemplate(
//        connectionFactory: RedisConnectionFactory,
//        objectMapper: ObjectMapper,
//    ): RedisTemplate<String, Any> {
//        val template = RedisTemplate<String, Any>()
//        template.setConnectionFactory(connectionFactory)
//        template.keySerializer = StringRedisSerializer()
//        template.valueSerializer = GenericJackson2JsonRedisSerializer(objectMapper)
//        template.afterPropertiesSet()
//        return template
//    }
}
