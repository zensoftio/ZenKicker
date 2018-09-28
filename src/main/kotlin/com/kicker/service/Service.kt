package com.kicker.service

import com.kicker.domain.model.game.GameRegistrationRequest
import com.kicker.domain.model.player.CreatePlayerRequest
import com.kicker.domain.model.player.UpdatePlayerUsernameRequest
import com.kicker.domain.model.player.UpdatePlayerPasswordRequest
import com.kicker.model.Award
import com.kicker.model.Game
import com.kicker.model.Player
import com.kicker.model.PlayerStats
import com.kicker.model.base.BaseModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.core.userdetails.UserDetailsService

/**
 * @author Yauheni Efimenko
 */
interface BaseService<T : BaseModel> {

    fun get(id: Long): T

    fun getAll(): List<T>

    fun getAll(pageable: Pageable): Page<T>

    fun save(entity: T): T

}

interface PlayerService : BaseService<Player>, UserDetailsService {

    fun getByUsername(username: String): Player?

    fun getAllActive(): List<Player>

    fun create(request: CreatePlayerRequest): Player

    fun updateUsername(playerId: Long, request: UpdatePlayerUsernameRequest): Player

    fun updatePassword(playerId: Long, request: UpdatePlayerPasswordRequest): Player

    fun updateRating(playerId: Long, newRating: Int): Player

    fun updateActivity(playerId: Long, active: Boolean): Player

}

interface GameService : BaseService<Game> {

    fun gameRegistration(playerId: Long, request: GameRegistrationRequest): Game

}

interface PlayerStatsService : BaseService<PlayerStats> {

    fun getByPlayer(playerId: Long): List<PlayerStats>

}

interface AwardService : BaseService<Award> {

    fun getAllByPlayer(playerId: Long): List<Award>

    fun doAwardMaxRatingForWeek()

    fun doAwardMaxDeltaRatingForWeek()

}