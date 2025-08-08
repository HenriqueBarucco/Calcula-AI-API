package com.henriquebarucco.calculaai.database.redis

import com.fasterxml.jackson.databind.ObjectMapper
import com.henriquebarucco.calculaai.price.Price
import com.henriquebarucco.calculaai.session.Session
import com.henriquebarucco.calculaai.session.SessionGateway
import com.henriquebarucco.calculaai.session.SessionId
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Repository

@Repository
class SessionRedisGateway(
    private val redisTemplate: StringRedisTemplate,
    private val objectMapper: ObjectMapper,
) : SessionGateway {
    override fun save(session: Session): Session {
        val key = buildKey(session.id)
        val hashOps = this.redisTemplate.opsForHash<String, String>()

        if (session.prices.isEmpty()) {
            hashOps.put(key, "_empty", "true")
        } else {
            session.prices.forEach { price ->
                hashOps.put(key, price.id.value, this.objectMapper.writeValueAsString(price))
            }
        }

        return session
    }

    override fun findById(sessionId: SessionId): Session? {
        val key = buildKey(sessionId)
        val hashOps = this.redisTemplate.opsForHash<String, String>()
        val entries = hashOps.entries(key)

        if (entries.isEmpty()) return null

        val prices =
            entries
                .filterKeys { it != "_empty" }
                .map { (_, value) -> this.objectMapper.readValue(value, Price::class.java) }

        return Session.with(sessionId, prices)
    }

    private fun buildKey(sessionId: SessionId) = "session:${sessionId.value}"
}
