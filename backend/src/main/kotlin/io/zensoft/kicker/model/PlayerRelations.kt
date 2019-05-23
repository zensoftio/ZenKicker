package io.zensoft.kicker.model

import io.zensoft.kicker.model.base.BaseModel
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
        val partner: Player,

        @ManyToOne
        @JoinColumn(name = "game_id", nullable = false)
        val game: Game,

        win: Boolean

) : BaseModel() {

    @Column(name = "count_wins", nullable = false)
    var countWins: Int = if (win) 1 else 0
        private set

    @Column(name = "count_games", nullable = false)
    var countGames: Int = 1
        private set

    @Column(name = "winning_percentage", nullable = false)
    var winningPercentage: Double = countWins.div(countGames.toDouble()) * 100
        private set

    constructor(relations: PlayerRelations, game: Game, win: Boolean) : this(
            relations.player, relations.partner, game, win
    ) {
        this.countWins = if (win) relations.countWins + 1 else relations.countWins
        this.countGames = relations.countGames + 1
        this.winningPercentage = this.countWins.div(this.countGames.toDouble()) * 100
    }

}
