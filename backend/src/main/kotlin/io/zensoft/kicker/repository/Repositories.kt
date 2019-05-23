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

    fun findByEmail(email: String): Player?

    @Query("""SELECT p FROM Player p WHERE LOWER(fullName) LIKE LOWER(CONCAT('%', ?1,'%'))""")
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

    @Query("SELECT * FROM players_relations pr INNER JOIN (SELECT player_id, partner_id, max(game_id), " +
            "max(id) as unqid FROM players_relations GROUP BY player_id, partner_id) unq ON pr.id = unq.unqid " +
            "WHERE pr.player_id = ?1 ORDER BY pr.count_games DESC", nativeQuery = true)
    fun findAllByPlayerOrderByCountGamesDesc(playerId: Long, pageable: Pageable): Page<PlayerRelations>

    fun findFirstByPlayerAndPartnerOrderByIdDesc(player: Player, partner: Player): PlayerRelations?

    @Query("SELECT * FROM players_relations pr INNER JOIN (SELECT player_id, partner_id, max(game_id), " +
            "max(id) as unqid FROM players_relations GROUP BY player_id, partner_id) unq ON pr.id = unq.unqid " +
            "WHERE pr.player_id = ?1 ORDER BY pr.count_games DESC", nativeQuery = true)
    fun findAllByPlayerOrderByCountGamesDesc(playerId: Long): List<PlayerRelations>

    @Query("SELECT * FROM players_relations pr INNER JOIN (SELECT player_id, partner_id, max(game_id), " +
            "max(id) as unqid FROM players_relations GROUP BY player_id, partner_id) unq ON pr.id = unq.unqid " +
            "WHERE pr.player_id = ?1 ORDER BY pr.winning_percentage ASC", nativeQuery = true)
    fun findAllByPlayerOrderByWinningPercentage(playerId: Long): List<PlayerRelations>

    @Query("SELECT * FROM players_relations pr INNER JOIN (SELECT player_id, partner_id, max(game_id), " +
            "max(id) as unqid FROM players_relations GROUP BY player_id, partner_id) unq ON pr.id = unq.unqid " +
            "WHERE pr.player_id = ?1 ORDER BY pr.winning_percentage DESC", nativeQuery = true)
    fun findAllByPlayerOrderByWinningPercentageDesc(playerId: Long): List<PlayerRelations>

}

@Repository
interface PlayerStatsRepository : BaseRepository<PlayerStats> {

    @Query("SELECT * FROM players_stats ps INNER JOIN (SELECT player_id, max(game_id) as g, max(id) as unqid " +
            "FROM players_stats GROUP BY player_id) unq ON ps.id = unq.unqid", nativeQuery = true)
    fun getAll(pageable: Pageable): Page<PlayerStats>

    fun findFirstByPlayerOrderByIdDesc(player: Player): PlayerStats

    @Query("SELECT * FROM players_stats ps INNER JOIN (SELECT player_id, max(game_id) as g, max(id) as unqid " +
            "FROM players_stats GROUP BY player_id) unq ON ps.id = unq.unqid WHERE ps.active = TRUE", nativeQuery = true)
    fun findAllByActiveTrue(pageable: Pageable): Page<PlayerStats>

}

@Repository
interface AchievementRepository : BaseRepository<Achievement> {

    fun findAllByPlayer(player: Player): List<Achievement>

}
