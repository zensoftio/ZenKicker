package io.zensoft.kicker.service

import io.zensoft.kicker.domain.PageRequest
import io.zensoft.kicker.domain.model.game.GameRegistrationRequest
import io.zensoft.kicker.domain.model.player.CreatePlayerRequest
import io.zensoft.kicker.domain.model.player.UpdatePlayerPasswordRequest
import io.zensoft.kicker.domain.model.player.UpdatePlayerUsernameRequest
import io.zensoft.kicker.domain.model.playerRelations.PlayerRelationsDashboard
import io.zensoft.kicker.domain.model.playerStats.PlayersDashboard
import io.zensoft.kicker.model.*
import io.zensoft.kicker.model.base.BaseModel
import org.springframework.data.domain.Page
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.multipart.MultipartFile

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

    fun findByUsername(username: String): Player?

    fun searchByKeyword(keyword: String): List<Player>

    fun create(request: CreatePlayerRequest): Player

    fun updateUsername(playerId: Long, request: UpdatePlayerUsernameRequest): Player

    fun updatePassword(playerId: Long, request: UpdatePlayerPasswordRequest): Player

    fun updateIcon(playerId: Long, icon: MultipartFile): Player

}

interface GameService : BaseService<Game> {

    fun countPerWeekDuring10WeeksByPlayer(playerId: Long): List<Long>

    fun countDuring10WeeksByPlayer(playerId: Long): Long

    fun countPerDayDuringLast7Days(): List<Long>

    fun gameRegistration(playerId: Long, request: GameRegistrationRequest): Game

}

interface PlayerToGameService : BaseService<PlayerToGame> {

    fun getPlayerGames(playerId: Long, pageRequest: PageRequest): Page<PlayerToGame>

    fun getDeltaPerWeekDuring10WeeksByPlayer(playerId: Long): List<Double>

    fun getActualRatingByPlayer(playerId: Long): Double

}

interface PlayerRelationsService {

    fun getAllByPlayer(playerId: Long, pageRequest: PageRequest): Page<PlayerRelations>

    fun getByPlayerAndPartner(playerId: Long, partnerId: Long): PlayerRelations

    fun getDashboard(playerId: Long): PlayerRelationsDashboard

    fun create(playerRelations: PlayerRelations): PlayerRelations

}

interface PlayerStatsService : BaseService<PlayerStats> {

    fun getByPlayer(playerId: Long): PlayerStats

    fun getDashboard(): PlayersDashboard

    fun getAllActive(pageRequest: PageRequest): Page<PlayerStats>

    fun updateActivity(playerId: Long, active: Boolean): PlayerStats

    fun updateRatingAndRated(player: Player): PlayerStats

}