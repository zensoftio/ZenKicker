package com.kicker.repository

import com.kicker.model.Award
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

}

@Repository
interface GameRepository : BaseRepository<Game> {

    @Query(value = "SELECT * FROM games g WHERE g.red_player1_id = ?1 OR g.red_player2_id = ?1 " +
            "OR g.yellow_player1_id = ?1 OR g.yellow_player2_id = ?1",
            countQuery = "SELECT COUNT(*) FROM games",
            nativeQuery = true)
    fun findAllBelongGames(currentPlayer: Player, pageable: Pageable): Page<Game>

}

@Repository
interface AwardRepository : BaseRepository<Award>