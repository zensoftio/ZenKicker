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
        private val playerService: PlayerService,
        private val playerStatsService: PlayerStatsService
) : DefaultBaseService<PlayerRelations>(repository), PlayerRelationsService {

    @Cacheable("relations")
    override fun getAllByPlayer(playerId: Long, pageRequest: PageRequest): Page<PlayerRelations> {
        val player = playerService.get(playerId)
        return repository.findAllByPlayerOrderByCountGamesDesc(player.id, pageRequest)
    }

    override fun getByPlayerAndPartner(playerId: Long, partnerId: Long): PlayerRelations? {
        val player = playerService.get(playerId)
        val partner = playerService.get(partnerId)

        return repository.findFirstByPlayerAndPartnerOrderByIdDesc(player, partner)
    }

    @Cacheable("relationsDashboard")
    override fun getDashboard(playerId: Long): List<PlayerRelations> {
        val player = playerService.get(playerId)

        val bestPartner = repository.findAllByPlayerOrderByWinningPercentageDesc(player.id).firstOrNull {
            val stats = playerStatsService.getByPlayer(it.partner.id)
            stats.active
        }

        val worstPartner = repository.findAllByPlayerOrderByWinningPercentage(player.id).firstOrNull {
            val stats = playerStatsService.getByPlayer(it.partner.id)
            stats.active
        }

        val favoritePartner = repository.findAllByPlayerOrderByCountGamesDesc(player.id).firstOrNull {
            val stats = playerStatsService.getByPlayer(it.partner.id)
            stats.active
        }

        if (Objects.isNull(bestPartner)) {
            return listOf()
        }

        return listOf(bestPartner!!, worstPartner!!, favoritePartner!!)
    }

}
