package com.kicker.service

import com.kicker.model.Award
import com.kicker.model.Game
import com.kicker.model.Player
import com.kicker.model.base.BaseModel

/**
 * @author Yauheni Efimenko
 */
interface BaseService<T : BaseModel> {

    fun get(id: Long): T?

    fun save(model: T): T

}

interface PlayerService : BaseService<Player>

interface GameService : BaseService<Game>

interface AwardService : BaseService<Award>