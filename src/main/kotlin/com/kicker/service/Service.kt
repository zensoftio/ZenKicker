package com.kicker.service

import com.kicker.domain.PageRequest
import com.kicker.domain.model.game.GameRegistrationRequest
import com.kicker.domain.model.player.CreatePlayerRequest
import com.kicker.domain.model.player.UpdatePlayerPasswordRequest
import com.kicker.domain.model.player.UpdatePlayerUsernameRequest
import com.kicker.model.Award
import com.kicker.model.Game
import com.kicker.model.Player
import com.kicker.model.PlayerStats
import com.kicker.model.base.BaseModel
import org.springframework.data.domain.Page
import org.springframework.security.core.userdetails.UserDetailsService

/**
 * @author Yauheni Efimenko
 */
interface BaseService<T : BaseModel> {

    fun get(id: Long): T

    fun getAll(): List<T>

    fun getAll(pageRequest: PageRequest): Page<T>

    fun save(entity: T): T

}

interface PlayerService : BaseService<Player>, UserDetailsService {

    fun getByUsername(username: String): Player?

    fun getAllActive(pageRequest: PageRequest): Page<Player>

    fun create(request: CreatePlayerRequest): Player

    fun updateUsername(playerId: Long, request: UpdatePlayerUsernameRequest): Player

    fun updatePassword(playerId: Long, request: UpdatePlayerPasswordRequest): Player

    fun updateRating(playerId: Long, newRating: Double): Player

    fun updateActivity(playerId: Long, active: Boolean): Player

}

interface GameService : BaseService<Game> {

    fun gameRegistration(playerId: Long, request: GameRegistrationRequest): Game

    fun getAllByPlayer(playerId: Long, pageRequest: PageRequest): Page<Game>

    fun countByPlayer(playerId: Long): Long

    fun countByPlayerAndWeeksAgo(playerId: Long, weeksAgo: Long): Long

    fun countFor10WeeksByPlayer(playerId: Long): Long

}

interface PlayerStatsService : BaseService<PlayerStats> {

    fun getByPlayer(playerId: Long, pageRequest: PageRequest): Page<PlayerStats>

    fun getDeltaByPlayerAndWeeksAgo(playerId: Long, weeksAgo: Long): Double

}

interface AwardService : BaseService<Award> {

    fun getAllByPlayer(playerId: Long): List<Award>

    fun doAwardMaxRatingForWeek()

    fun doAwardMaxDeltaRatingForWeek()

}