package com.kicker.service

import com.kicker.config.property.PlayerSettingsProperties
import com.kicker.domain.PageRequest
import com.kicker.domain.model.game.GameRegistrationRequest
import com.kicker.model.Game
import com.kicker.repository.GameRepository
import com.kicker.utils.DateUtils
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

/**
 * @author Yauheni Efimenko
 */
@Service
@Transactional(readOnly = true)
class DefaultGameService(
        private val repository: GameRepository,
        private val playerService: PlayerService,
        private val eventPublisher: ApplicationEventPublisher,
        private val playerSettingsProperties: PlayerSettingsProperties
) : DefaultBaseService<Game, GameRepository>(repository), GameService {

    override fun getAllByPlayer(playerId: Long, pageRequest: PageRequest): Page<Game> {
        val player = playerService.get(playerId)
        return repository.findAllByPlayer(player, pageRequest)
    }

    override fun countByPlayer(playerId: Long): Long {
        val player = playerService.get(playerId)
        return repository.countByPlayer(player)
    }

    override fun countByPlayerForWeeksAgo(playerId: Long, countWeeks: Long): Map<Long, Long> {
        val dashboard = mutableMapOf<Long, Long>()
        for (weeksAgo in 0..countWeeks) {
            dashboard[weeksAgo] = countByPlayerAndWeeksAgo(playerId, weeksAgo)
        }
        return dashboard
    }

    override fun countByPlayerAndWeeksAgo(playerId: Long, weeksAgo: Long): Long {
        val player = playerService.get(playerId)
        val dates = DateUtils.getIntervalDatesOfWeek(weeksAgo)

        return repository.countByPlayerAndIntervalDates(player, dates.first, dates.second)
    }

    /*
    * Current week is number 0, so 10 week is number 9
    * */
    override fun countFor10WeeksByPlayer(playerId: Long): Long {
        val player = playerService.get(playerId)
        return repository.countByPlayerAndIntervalDates(player,
                DateUtils.getStartDateOfWeek(playerSettingsProperties.countWeeks!! - 1), LocalDate.now())
    }

    @Transactional
    override fun gameRegistration(playerId: Long, request: GameRegistrationRequest): Game {
        val reporter = playerService.get(playerId)

        val winner1 = playerService.get(request.winner1Id!!)
        val winner2 = playerService.get(request.winner2Id!!)
        val loser1 = playerService.get(request.loser1Id!!)
        val loser2 = playerService.get(request.loser2Id!!)

        val game = Game(request.losersGoals!!, winner1, winner2, loser1, loser2, reporter)
        val persistGame = repository.save(game)

        eventPublisher.publishEvent(persistGame)

        return persistGame
    }

}