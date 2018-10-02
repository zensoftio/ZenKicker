package com.kicker.repository

import com.kicker.model.Award
import com.kicker.model.Game
import com.kicker.model.Player
import com.kicker.model.PlayerStats
import com.kicker.model.base.BaseModel
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
    @Query("""SELECT SUM(s.delta) FROM PlayerStats s INNER JOIN Game g WHERE s.player = ?1
                AND (?2 <= DATE(g.date) AND ?3 >= DATE(g.date))""")
    fun calculateDeltaByPlayerAndIntervalDates(player: Player, startDate: LocalDate, endDate: LocalDate): Double

}

@Repository
interface AwardRepository : BaseRepository<Award> {

    fun findByPlayer(player: Player): List<Award>

}