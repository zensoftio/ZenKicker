package com.kicker.service

import com.kicker.domain.model.game.GameRegistrationRequest
import com.kicker.domain.model.player.CreatePlayerRequest
import com.kicker.domain.model.player.UpdateDataPlayerRequest
import com.kicker.domain.model.player.UpdatePasswordPlayerRequest
import com.kicker.model.Award
import com.kicker.model.DashboardRating
import com.kicker.model.Game
import com.kicker.model.Player
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

}

interface PlayerService : BaseService<Player>, UserDetailsService {

    fun getByUsername(username: String): Player?

    fun create(request: CreatePlayerRequest): Player

    fun updateData(playerId: Long, request: UpdateDataPlayerRequest): Player

    fun updatePassword(playerId: Long, request: UpdatePasswordPlayerRequest): Player

    fun updateRating(playerId: Long, newRating: Double): Player

    fun updateActivation(playerId: Long, active: Boolean): Player

}

interface GameService : BaseService<Game> {

    fun getAllBelongGames(player: Player, pageable: Pageable): Page<Game>

    fun gameRegistration(reporter: Player, request: GameRegistrationRequest): Game

}

interface AwardService : BaseService<Award> {

    fun getAllByPlayer(player: Player): List<Award>

}

interface DashboardRatingService : BaseService<DashboardRating> {

    fun getAllByPlayer(player: Player): List<DashboardRating>

    fun recalculate(player: Player)

}