package io.zensoft.kicker.model

import io.zensoft.kicker.model.base.BaseModel
import javax.persistence.*

/**
 * @author Yauheni Efimenko
 */
@Entity
@Table(name = "players2games")
class PlayerToGame(

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