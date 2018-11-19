package com.kicker.api.model

import com.kicker.api.model.base.BaseModel
import javax.persistence.*

/**
 * @author Yauheni Efimenko
 */

@Entity
@Table(name = "players_relations")
class PlayerRelations(

        @ManyToOne
        @JoinColumn(name = "player_id", nullable = false)
        val player: Player,

        @ManyToOne
        @JoinColumn(name = "partner_id", nullable = false)
        val partner: Player

) : BaseModel() {

    @Column(name = "count_wins", nullable = false)
    var countWins: Int = 0
        private set

    @Column(name = "count_games", nullable = false)
    var countGames: Int = 0
        private set

    @Column(name = "winning_percentage", nullable = false)
    var winningPercentage: Double = 0.0
        private set


    fun addWin() {
        countWins++
        countGames++
        winningPercentage = countWins.div(countGames.toDouble()) * 100
    }

    fun addLoss() {
        countGames++
        winningPercentage = countWins.div(countGames.toDouble()) * 100
    }

}