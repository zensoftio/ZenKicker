package io.zensoft.kicker.repository

import io.zensoft.kicker.domain.repository.CountGamesPerDayDto
import io.zensoft.kicker.model.*
import io.zensoft.kicker.model.base.BaseModel
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

    @Query("""SELECT p FROM Player p WHERE LOWER(username) LIKE LOWER(CONCAT('%', ?1,'%'))""")
    fun searchByKeyword(keyword: String): List<Player>

}

@Repository
interface GameRepository : BaseRepository<Game> {

    /**
     * Getting count of games for a specific interval of dates by player
     */
    @Query("""SELECT COUNT(g) FROM Game g WHERE (g.winner1 = ?1 OR g.winner2 = ?1 OR g.loser1 = ?1 OR g.loser2 = ?1)
                AND (?2 <= DATE(g.date) AND ?3 >= DATE(g.date))""")
    fun countByPlayerAndIntervalDates(player: Player, startDate: LocalDate, endDate: LocalDate): Long

    @Query("""SELECT new io.zensoft.kicker.domain.repository.CountGamesPerDayDto(MIN(g.date), COUNT(g))
        FROM Game g WHERE ?1 <= DATE(g.date) AND ?2 >= DATE(g.date) GROUP BY DATE(g.date)""")
    fun countPerDayByIntervalDates(startDate: LocalDate, endDate: LocalDate): List<CountGamesPerDayDto>

}

@Repository
interface PlayerToGameRepository : BaseRepository<PlayerToGame> {

    fun findByPlayer(player: Player, pageable: Pageable): Page<PlayerToGame>

    /**
     * Getting delta of rating for a specific interval of dates by player
     */
    @Query("""SELECT COALESCE(SUM(s.delta), 0) FROM PlayerToGame s JOIN Game g ON s.game = g AND s.player = ?1
                AND (?2 <= DATE(g.date) AND ?3 >= DATE(g.date))""")
    fun calculateDeltaByPlayerAndIntervalDates(player: Player, startDate: LocalDate, endDate: LocalDate): Double

}

@Repository
interface PlayerRelationsRepository : BaseRepository<PlayerRelations> {

    fun findByPlayerAndPartnerPlayerStatsActiveTrue(player: Player, pageable: Pageable): Page<PlayerRelations>

    fun findByPlayerAndPartner(player: Player, partner: Player): PlayerRelations?

    fun findFirstByPlayerAndPartnerPlayerStatsActiveTrueOrderByCountGamesDesc(player: Player): PlayerRelations?

    fun findFirstByPlayerAndPartnerPlayerStatsActiveTrueOrderByWinningPercentage(player: Player): PlayerRelations?

    fun findFirstByPlayerAndPartnerPlayerStatsActiveTrueOrderByWinningPercentageDesc(player: Player): PlayerRelations?

}

@Repository
interface PlayerStatsRepository : BaseRepository<PlayerStats> {

    fun findByPlayer(player: Player): PlayerStats

    fun findAllByActiveTrue(pageable: Pageable): Page<PlayerStats>

}
