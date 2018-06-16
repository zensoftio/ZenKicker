package com.kicker.service

import com.kicker.domain.model.game.GameRegistrationRequest
import com.kicker.domain.model.player.CreatePlayerRequest
import com.kicker.domain.model.player.UpdateDataPlayerRequest
import com.kicker.domain.model.player.UpdatePasswordPlayerRequest
import com.kicker.model.Award
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

    fun save(model: T): T

}

interface PlayerService : BaseService<Player>, UserDetailsService {

    fun getByUsername(username: String): Player?

    fun create(request: CreatePlayerRequest): Player

    fun updateData(currentPlayer: Player, request: UpdateDataPlayerRequest): Player

    fun updatePassword(currentPlayer: Player, request: UpdatePasswordPlayerRequest): Player

}

interface GameService : BaseService<Game> {

    fun gameRegistration(currentPlayer: Player, request: GameRegistrationRequest): Game

}

interface AwardService : BaseService<Award>