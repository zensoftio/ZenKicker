package io.zensoft.kicker.service

import io.zensoft.kicker.config.property.PlayerSettingsProperties
import io.zensoft.kicker.domain.PageRequest
import io.zensoft.kicker.domain.model.playerStats.PlayerStatsPageRequest
import io.zensoft.kicker.domain.model.playerStats.PlayerStatsPageRequest.Companion.RATING_FIELD
import io.zensoft.kicker.model.Player
import io.zensoft.kicker.model.PlayerStats
import io.zensoft.kicker.repository.PlayerStatsRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.context.event.EventListener
import org.springframework.data.domain.Page
import org.springframework.data.domain.Sort.Direction.DESC
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class DefaultPlayerStatsService(
        private val repository: PlayerStatsRepository,
        private val gameService: GameService,
        private val playerService: PlayerService,
        private val playerToGameService: PlayerToGameService,
        private val playerSettingsProperties: PlayerSettingsProperties
) : DefaultBaseService<PlayerStats>(repository), PlayerStatsService {

    @EventListener
    fun createPlayer(player: Player) {
        repository.save(PlayerStats(player))
    }

    override fun getAll(pageRequest: PageRequest): Page<PlayerStats> = repository.getAll(pageRequest)

    override fun getByPlayer(playerId: Long): PlayerStats {
        val player = playerService.get(playerId)
        return repository.findFirstByPlayerOrderByIdDesc(player)
    }

    @Cacheable("playersDashboard")
    override fun getDashboard(): List<Player> {
        val pageRequest = PlayerStatsPageRequest().apply { limit = 0; sortBy = RATING_FIELD.first }

        if (getAllActive(pageRequest).totalElements < 4) {
            return listOf()
        }

        val loser = getAllActive(pageRequest.apply { limit = 1; sortBy = RATING_FIELD.first })
        val top3 = getAllActive(pageRequest.apply { limit = 3; sortBy = RATING_FIELD.first; sortDirection = DESC })

        return listOf(
                top3.content[0].player,
                top3.content[1].player,
                top3.content[2].player,
                loser.content[0].player
        )
    }

    override fun getAllActive(pageRequest: PageRequest): Page<PlayerStats> = repository.findAllByActiveTrue(pageRequest)

    @Transactional
    override fun updateStatsAfterWeek(player: Player): PlayerStats {
        val stats = getByPlayer(player.id)
        val rated = gameService.countDuring10WeeksByPlayer(player.id).toInt()

        stats.rating = playerToGameService.getActualRatingByPlayer(player.id)
        stats.rated = rated
        stats.active = rated >= playerSettingsProperties.countGames!!

        return repository.save(stats)
    }

}
