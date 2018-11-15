package com.kicker.service

import com.kicker.domain.PageRequest
import com.kicker.model.PlayerRelations
import com.kicker.repository.PlayerRelationsRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author Yauheni Efimenko
 */
@Service
@Transactional(readOnly = true)
class DefaultPlayerRelationsService(
        private val repository: PlayerRelationsRepository,
        private val playerService: PlayerService
) : DefaultBaseService<PlayerRelations, PlayerRelationsRepository>(repository), PlayerRelationsService {

    @Cacheable("relations")
    override fun getAllByPlayer(playerId: Long, pageRequest: PageRequest): Page<PlayerRelations> {
        val player = playerService.get(playerId)
        return repository.findByPlayerAndPartnerActiveTrue(player, pageRequest)
    }

    override fun getByPlayerAndPartner(playerId: Long, partnerId: Long): PlayerRelations {
        val player = playerService.get(playerId)
        val partner = playerService.get(partnerId)

        val playerRelations = repository.findByPlayerAndPartner(player, partner)

        return playerRelations ?: create(PlayerRelations(player, partner))
    }

    @Transactional
    override fun create(playerRelations: PlayerRelations): PlayerRelations = repository.save(playerRelations)

}