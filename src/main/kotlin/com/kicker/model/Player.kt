package com.kicker.model

import com.kicker.model.base.BaseModel
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

/**
 * @author Yauheni Efimenko
 */
@Entity
@Table(name = "players")
class Player(

        @Column(name = "username", nullable = false, unique = true)
        private var username: String,

        @Column(name = "password", nullable = false)
        private var password: String,

        @Column(name = "rating", nullable = false)
        var rating: Double = PLAYER_RATING,

        @Column(name = "active", nullable = false)
        var active: Boolean = false,

        @Column(name = "icon_name")
        var iconName: String? = null,

        @Column(name = "current_win_streak", nullable = false)
        private var currentWinStreak: Int = 0,

        @Column(name = "current_loss_streak", nullable = false)
        private var currentLossStreak: Int = 0,

        @Column(name = "longest_win_streak", nullable = false)
        private var longestWinStreak: Int = 0,

        @Column(name = "longest_loss_streak", nullable = false)
        private var longestLossStreak: Int = 0

) : BaseModel(), UserDetails {

    companion object {
        const val PLAYER_RATING: Double = 10000.0
    }


    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = mutableListOf()

    override fun isEnabled(): Boolean = true

    override fun getUsername(): String = username

    override fun isCredentialsNonExpired(): Boolean = true

    override fun getPassword(): String = password

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    fun setUsername(username: String) {
        this.username = username
    }

    fun setPassword(password: String) {
        this.password = password
    }

    fun getCurrentWinStreak(): Int = currentWinStreak

    fun getCurrentLossStreak(): Int = currentLossStreak

    fun getLongestWinStreak(): Int = longestWinStreak

    fun getLongestLossStreak(): Int = longestLossStreak

    fun changeWinAndLossStreak(won: Boolean) {
        if (won) {
            currentWinStreak++
            currentLossStreak = 0
            if (longestWinStreak < currentWinStreak) {
                longestWinStreak = currentWinStreak
            }
        } else {
            currentLossStreak++
            currentWinStreak = 0
            if (longestLossStreak < currentLossStreak) {
                longestLossStreak = currentLossStreak
            }
        }
    }

}