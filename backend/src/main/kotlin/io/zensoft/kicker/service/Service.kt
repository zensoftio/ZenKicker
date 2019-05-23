package io.zensoft.kicker.service

import io.zensoft.kicker.domain.PageRequest
import io.zensoft.kicker.domain.model.game.GameRegistrationRequest
import io.zensoft.kicker.domain.model.player.CreatePlayerRequest
import io.zensoft.kicker.domain.model.player.UpdatePlayerEmailRequest
import io.zensoft.kicker.domain.model.player.UpdatePlayerFullNameRequest
import io.zensoft.kicker.domain.model.player.UpdatePlayerPasswordRequest
import io.zensoft.kicker.model.*
import io.zensoft.kicker.model.base.BaseModel
import io.zensoft.kicker.model.dictionary.AchievementLevel
import io.zensoft.kicker.model.dictionary.AchievementType
import org.springframework.data.domain.Page
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.multipart.MultipartFile

/**
 * @author Yauheni Efimenko
 */
interface BaseService<T : BaseModel> {

    fun get(id: Long): T

    fun add(entity: T): T

}

interface PlayerService : BaseService<Player>, UserDetailsService {

    fun getAll(): List<Player>

    fun getAll(pageRequest: PageRequest): Page<Player>

    fun findByEmail(email: String): Player?

    fun searchByKeyword(keyword: String): List<Player>

    fun create(request: CreatePlayerRequest): Player

    fun updateEmail(playerId: Long, request: UpdatePlayerEmailRequest): Player

    fun updateFullName(playerId: Long, request: UpdatePlayerFullNameRequest): Player

    fun updatePassword(playerId: Long, request: UpdatePlayerPasswordRequest): Player

    fun updateIcon(playerId: Long, icon: MultipartFile): Player

}

interface GameService : BaseService<Game> {

    fun getAll(pageRequest: PageRequest): Page<Game>

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

interface PlayerRelationsService : BaseService<PlayerRelations> {

    fun getAllByPlayer(playerId: Long, pageRequest: PageRequest): Page<PlayerRelations>

    fun getByPlayerAndPartner(playerId: Long, partnerId: Long): PlayerRelations?

    fun getDashboard(playerId: Long): List<PlayerRelations>

}

interface PlayerStatsService : BaseService<PlayerStats> {

    fun getAll(pageRequest: PageRequest): Page<PlayerStats>

    fun getByPlayer(playerId: Long): PlayerStats

    fun getDashboard(): List<Player>

    fun getAllActive(pageRequest: PageRequest): Page<PlayerStats>

    fun updateStatsAfterWeek(player: Player): PlayerStats

}

interface AchievementService : BaseService<Achievement> {

    fun getAllByPlayer(playerId: Long): List<Achievement>

    fun achieve(player: Player, type: AchievementType, level: AchievementLevel): Achievement

}
