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

    companion object {
        const val PLAYER_RATING: Double = 10000.0
    }


    fun addGame(won: Boolean, goalsAgainst: Int, goalsFor: Int, delta: Double) {
        if (won) {
            countWins++
        }
        rating += delta
        changeWinAndLossStreak(won)
        countGames++
        rated++
        winningPercentage = countWins.div(countGames.toDouble()) * 100
        this.goalsAgainst += goalsAgainst
        this.goalsFor += goalsFor
    }

    private fun changeWinAndLossStreak(won: Boolean) {
        if (won) {
            currentWinningStreak++
            currentLossesStreak = 0
            if (longestWinningStreak < currentWinningStreak) {
                longestWinningStreak = currentWinningStreak
            }
        } else {
            currentLossesStreak++
            currentWinningStreak = 0
            if (longestLossesStreak < currentLossesStreak) {
                longestLossesStreak = currentLossesStreak
            }
        }
    }

}