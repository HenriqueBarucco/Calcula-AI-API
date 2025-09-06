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

        hashOps.put(key, "_hasClub", session.hasClub.toString())

        val existingKeys = hashOps.keys(key).filter { it != "_empty" && it != "_hasClub" }

        val currentPriceIds = session.prices.map { it.id.value }.toSet()

        val keysToDelete = existingKeys.filter { it !in currentPriceIds }
        if (keysToDelete.isNotEmpty()) {
            hashOps.delete(key, *keysToDelete.toTypedArray())
        }

        if (session.prices.isEmpty()) {
            hashOps.put(key, "_empty", "true")
        } else {
            hashOps.delete(key, "_empty")
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

        val hasClub = entries["_hasClub"]?.toBoolean() ?: false

        val prices =
            entries
                .filterKeys { it != "_empty" && it != "_hasClub" }
                .map { (_, value) -> this.objectMapper.readValue(value, Price::class.java) }

        return Session.with(sessionId, hasClub, prices)
    }

    private fun buildKey(sessionId: SessionId) = "session:${sessionId.value}"
}
