package com.kicker.api.repository

import com.kicker.api.domain.repository.CountGamesPerDayDto
import com.kicker.api.domain.repository.PlayerDeltaDto
import com.kicker.api.model.Game
import com.kicker.api.model.Player
import com.kicker.api.model.PlayerRelations
import com.kicker.api.model.PlayerStats
import com.kicker.api.model.base.BaseModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.stereotype.Repository
import java.time.LocalDate

/**
 * @author Yauheni Efimenko
 */
@NoRepositoryBean
interface BaseRepository<T : BaseModel> : JpaRepository<T, Long>

@Repository
interface PlayerRepository : BaseRepository<Player> {

    fun findByUsername(username: String): Player?

    fun findAllByActiveTrue(pageable: Pageable): Page<Player>

}

@Repository
interface GameRepository : BaseRepository<Game> {

    @Query("SELECT g FROM Game g WHERE g.winner1 = ?1 OR g.winner2 = ?1 OR g.loser1 = ?1 OR g.loser2 = ?1")
    fun findAllByPlayer(player: Player, pageable: Pageable): Page<Game>

    @Query("""SELECT new com.kicker.api.domain.repository.CountGamesPerDayDto(MIN(g.date), COUNT(g))
        FROM Game g WHERE ?1 <= DATE(g.date) AND ?2 >= DATE(g.date) GROUP BY DATE(g.date)""")
    fun countPerDayByIntervalDates(startDate: LocalDate, endDate: LocalDate): List<CountGamesPerDayDto>

    @Query("SELECT COUNT(g) FROM Game g WHERE g.winner1 = ?1 OR g.winner2 = ?1 OR g.loser1 = ?1 OR g.loser2 = ?1")
    fun countByPlayer(player: Player): Long

    /**
     * Getting count of games for a specific interval of dates by player
     */
    @Query("""SELECT COUNT(g) FROM Game g WHERE (g.winner1 = ?1 OR g.winner2 = ?1 OR g.loser1 = ?1 OR g.loser2 = ?1)
                AND (?2 <= DATE(g.date) AND ?3 >= DATE(g.date))""")
    fun countByPlayerAndIntervalDates(player: Player, startDate: LocalDate, endDate: LocalDate): Long

}

@Repository
interface PlayerStatsRepository : BaseRepository<PlayerStats> {

    fun findByPlayer(player: Player, pageable: Pageable): Page<PlayerStats>

    /**
     * Getting delta of rating for a specific interval of dates by player
     */
    @Query("""SELECT COALESCE(SUM(s.delta), 0) FROM PlayerStats s JOIN Game g ON s.game = g AND s.player = ?1
                AND (?2 <= DATE(g.date) AND ?3 >= DATE(g.date))""")
    fun calculateDeltaByPlayerAndIntervalDates(player: Player, startDate: LocalDate, endDate: LocalDate): Double

    /**
     * Getting delta of rating of players for a specific interval of dates
     */
    @Query("""SELECT new com.kicker.api.domain.repository.PlayerDeltaDto(s.player, SUM(s.delta) AS deltaSum)
        FROM PlayerStats s JOIN Game g ON s.game = g
        AND (?1 <= DATE(g.date) AND ?2 >= DATE(g.date)) AND (s.player.active = true)
        GROUP BY s.player ORDER BY deltaSum DESC""")
    fun calculateDeltaPlayersForIntervalDates(startDate: LocalDate, endDate: LocalDate): List<PlayerDeltaDto>

    fun countGamesByPlayerAndWon(player: Player, won: Boolean): Long

    @Query("""SELECT COALESCE(SUM(g.losersGoals), 0) FROM PlayerStats s JOIN Game g ON s.game = g AND s.player = ?1
                AND s.won = ?2""")
    fun countGoalsByPlayerAndWon(player: Player, won: Boolean): Long

}

@Repository
interface PlayerRelationsRepository : BaseRepository<PlayerRelations> {

    fun findByPlayerAndPartnerActiveTrue(player: Player, pageable: Pageable): Page<PlayerRelations>

    fun findByPlayerAndPartner(player: Player, partner: Player): PlayerRelations?

}