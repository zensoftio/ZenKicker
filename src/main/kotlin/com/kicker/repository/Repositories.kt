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
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

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

    @Query(value = """
                SELECT g
                FROM Game g
                WHERE g.winner1 = ?1
                OR g.winner2 = ?1
                OR g.loser1 = ?1
                OR g.loser2 = ?1
            """)
    fun findAllBelongGames(player: Player, pageable: Pageable): Page<Game>

}

@Repository
interface PlayerStatsRepository : BaseRepository<PlayerStats> {

    fun findByPlayer(player: Player, pageable: Pageable): Page<PlayerStats>

    @Query(nativeQuery = true,
            value = """
                SELECT COALESCE(SUM(s.delta), 0)
                FROM players_stats s INNER JOIN games g
                ON s.game_id = g.id
                AND s.player_id = :playerId
                AND (EXTRACT(WEEK FROM now()) - EXTRACT(WEEK FROM g.date)) = :week
            """)
    fun calculateDeltaByPlayerAndWeek(
            @Param("playerId") playerId: Long,
            @Param("week") week: Int
    ): Double

}

@Repository
interface AwardRepository : BaseRepository<Award> {

    fun findByPlayer(player: Player): List<Award>

}