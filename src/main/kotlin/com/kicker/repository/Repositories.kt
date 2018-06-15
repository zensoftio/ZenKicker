package com.kicker.repository

import com.kicker.model.Award
import com.kicker.model.Game
import com.kicker.model.Player
import com.kicker.model.base.BaseModel
import org.springframework.data.jpa.repository.JpaRepository
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
interface GameRepository : BaseRepository<Game>

@Repository
interface AwardRepository : BaseRepository<Award>