package io.zensoft.kicker.service

import io.zensoft.kicker.domain.PageRequest
import io.zensoft.kicker.domain.model.playerRelations.PlayerRelationsDashboard
import io.zensoft.kicker.model.PlayerRelations
import io.zensoft.kicker.repository.PlayerRelationsRepository
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
        return repository.findByPlayerAndPartnerPlayerStatsActiveTrue(player, pageRequest)
    }

    @Transactional
    override fun getByPlayerAndPartner(playerId: Long, partnerId: Long): PlayerRelations {
        val player = playerService.get(playerId)
        val partner = playerService.get(partnerId)

        val playerRelations = repository.findByPlayerAndPartner(player, partner)

        return playerRelations ?: create(PlayerRelations(player, partner))
    }

    @Cacheable("relationsDashboard")
    override fun getDashboard(playerId: Long): PlayerRelationsDashboard {
        val player = playerService.get(playerId)

        val bestPartner =
                repository.findFirstByPlayerAndPartnerPlayerStatsActiveTrueOrderByWinningPercentageDesc(player)
        val worstPartner =
                repository.findFirstByPlayerAndPartnerPlayerStatsActiveTrueOrderByWinningPercentage(player)
        val favoritePartner =
                repository.findFirstByPlayerAndPartnerPlayerStatsActiveTrueOrderByCountGamesDesc(player)

        return PlayerRelationsDashboard(bestPartner, worstPartner, favoritePartner)
    }

    @Transactional
    override fun create(playerRelations: PlayerRelations): PlayerRelations = repository.save(playerRelations)

}