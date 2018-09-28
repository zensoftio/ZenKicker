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
        var rating: Int = PLAYER_RATING,

        @Column(name = "active", nullable = false)
        var active: Boolean = false

) : BaseModel(), UserDetails {

    companion object {
        const val PLAYER_RATING: Int = 10000
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

}