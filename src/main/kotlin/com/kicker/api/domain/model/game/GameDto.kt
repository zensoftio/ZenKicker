package com.kicker.api.domain.model.game

import com.kicker.model.Game
import java.sql.Timestamp

/**
 * @author Yauheni Efimenko
 */
data class GameDto(
        val id: Long,
        val losersGoals: Int,
        val winner1Id: Long,
        val winner2Id: Long,
        val loser1Id: Long,
        val loser2Id: Long,
        val reportedById: Long,
        val date: Long
) {

    constructor(game: Game) : this(
            game.id,
            game.losersGoals,
            game.winner1.id,
            game.winner2.id,
            game.loser1.id,
            game.loser2.id,
            game.reportedBy.id,
            Timestamp.valueOf(game.date).time
    )

}