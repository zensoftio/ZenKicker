package io.zensoft.kicker.model

import io.zensoft.kicker.model.base.BaseModel
import javax.persistence.*

@Entity
@Table(name = "players_stats")
class PlayerStats(

        @OneToOne
        @JoinColumn(name = "player_id", nullable = false)
        val player: Player

) : BaseModel() {

    @Column(name = "rating", nullable = false)
    var rating: Double = PLAYER_RATING

    @Column(name = "current_winning_streak", nullable = false)
    var currentWinningStreak: Int = 0
        private set

    @Column(name = "current_losses_streak", nullable = false)
    var currentLossesStreak: Int = 0
        private set

    @Column(name = "longest_winning_streak", nullable = false)
    var longestWinningStreak: Int = 0
        private set

    @Column(name = "longest_losses_streak", nullable = false)
    var longestLossesStreak: Int = 0
        private set

    @Column(name = "count_wins", nullable = false)
    var countWins: Int = 0
        private set

    @Column(name = "count_games", nullable = false)
    var countGames: Int = 0
        private set

    @Column(name = "rated", nullable = false)
    var rated: Int = 0

    @Column(name = "winning_percentage", nullable = false)
    var winningPercentage: Double = 0.0
        private set

    @Column(name = "goals_against", nullable = false)
    var goalsAgainst: Int = 0
        private set

    @Column(name = "goals_for", nullable = false)
    var goalsFor: Int = 0
        private set

    @Column(name = "active", nullable = false)
    var active: Boolean = false

    @ManyToOne
    @JoinColumn(name = "game_id")
    var game: Game? = null

    companion object {
        const val PLAYER_RATING: Double = 10000.0
    }

    constructor(playerStats: PlayerStats, game: Game, won: Boolean, goalsAgainst: Int, goalsFor: Int,
                delta: Double, countGamesForActive: Long) : this(playerStats.player) {
        this.rating = playerStats.rating
        this.currentWinningStreak = playerStats.currentWinningStreak
        this.currentLossesStreak = playerStats.currentLossesStreak
        this.longestWinningStreak = playerStats.longestWinningStreak
        this.longestLossesStreak = playerStats.longestLossesStreak
        this.countWins = playerStats.countWins
        this.countGames = playerStats.countGames
        this.rated = playerStats.rated
        this.winningPercentage = playerStats.winningPercentage
        this.goalsAgainst = playerStats.goalsAgainst
        this.goalsFor = playerStats.goalsFor
        this.active = playerStats.active
        this.game = game

        if (won) {
            this.countWins++
        }
        this.rating += delta
        changeWinAndLossStreak(won)
        this.countGames++
        this.rated++
        this.winningPercentage = this.countWins.div(this.countGames.toDouble()) * 100
        this.goalsAgainst += goalsAgainst
        this.goalsFor += goalsFor
        this.active = this.rated >= countGamesForActive
    }


    private fun changeWinAndLossStreak(won: Boolean) {
        if (won) {
            this.currentWinningStreak++
            this.currentLossesStreak = 0
            if (this.longestWinningStreak < this.currentWinningStreak) {
                this.longestWinningStreak = this.currentWinningStreak
            }
        } else {
            this.currentLossesStreak++
            this.currentWinningStreak = 0
            if (this.longestLossesStreak < this.currentLossesStreak) {
                this.longestLossesStreak = this.currentLossesStreak
            }
        }
    }

}
