package com.kicker.repository

import com.kicker.model.Award
import com.kicker.model.DashboardRating
import com.kicker.model.Game
import com.kicker.model.Player
import com.kicker.model.base.BaseModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.stereotype.Repository

/**
 * @author Yauheni Efimenko
 */
@NoRepositoryBean
interface BaseRepository<T : BaseModel> : JpaRepository<T, Long>

@Repository
interface PlayerRepository : BaseRepository<Player> {

    fun findByUsername(username: String): Player?

    @Query(value = "SELECT p FROM Player p ORDER BY p.currentRating DESC")
    fun findOrderByCurrentRatingDesc(): List<Player>

}

@Repository
interface GameRepository : BaseRepository<Game> {

    @Query(value = "SELECT g FROM Game g WHERE g.redPlayer1 = ?1 OR g.redPlayer2 = ?1 OR g.yellowPlayer1 = ?1 " +
            "OR g.yellowPlayer2 = ?1")
    fun findAllBelongGames(currentPlayer: Player, pageable: Pageable): Page<Game>

}

@Repository
interface AwardRepository : BaseRepository<Award> {

    fun findByPlayer(player: Player): List<Award>

}

@Repository
interface DashboardRatingRepository : BaseRepository<DashboardRating> {

    fun findByPlayerOrderByWeeksAgoDesc(player: Player): List<DashboardRating>

    fun findByWeeksAgoOrderByDeltaDesc(weeksAgo: Int): List<DashboardRating>

}