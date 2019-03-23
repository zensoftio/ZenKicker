package io.zensoft.kicker.service

import io.zensoft.kicker.domain.PageRequest
import io.zensoft.kicker.model.PlayerRelations
import io.zensoft.kicker.repository.PlayerRelationsRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

/**
 * @author Yauheni Efimenko
 */
@Service
@Transactional(readOnly = true)
class DefaultPlayerRelationsService(
        private val repository: PlayerRelationsRepository,
        private val playerService: PlayerService
) : DefaultBaseService<PlayerRelations>(repository), PlayerRelationsService {

    @Cacheable("relations")
    override fun getAllByPlayer(playerId: Long, pageRequest: PageRequest): Page<PlayerRelations> {
        val player = playerService.get(playerId)
        return repository.findByPlayerOrderByCountGamesDesc(player, pageRequest)
    }

    @Transactional
    override fun getByPlayerAndPartner(playerId: Long, partnerId: Long): PlayerRelations {
        val player = playerService.get(playerId)
        val partner = playerService.get(partnerId)

        val playerRelations = repository.findByPlayerAndPartner(player, partner)

        return playerRelations ?: repository.save(PlayerRelations(player, partner))
    }

    @Cacheable("relationsDashboard")
    override fun getDashboard(playerId: Long): List<PlayerRelations> {
        val player = playerService.get(playerId)

        val bestPartner =
                repository.findFirstByPlayerAndPartnerPlayerStatsActiveTrueOrderByWinningPercentageDesc(player)
        val worstPartner =
                repository.findFirstByPlayerAndPartnerPlayerStatsActiveTrueOrderByWinningPercentage(player)
        val favoritePartner =
                repository.findFirstByPlayerAndPartnerPlayerStatsActiveTrueOrderByCountGamesDesc(player)

        if (Objects.isNull(bestPartner)) {
            return listOf()
        }

        return listOf(bestPartner!!, worstPartner!!, favoritePartner!!)
    }

}