package com.kicker.model

import com.kicker.model.base.BaseModel
import javax.persistence.*

/**
 * @author Yauheni Efimenko
 */
@Entity
@Table(name = "players_stats")
class PlayerStats(

        @ManyToOne
        @JoinColumn(name = "player_id", nullable = false)
        val player: Player,

        @ManyToOne
        @JoinColumn(name = "game_id", nullable = false)
        val game: Game,

        @Column(name = "won", nullable = false)
        val won: Boolean,

        @Column(name = "delta", nullable = false)
        val delta: Double

) : BaseModel()