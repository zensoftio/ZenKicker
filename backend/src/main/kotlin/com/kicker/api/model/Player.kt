package com.kicker.api.model

import com.kicker.api.model.base.BaseModel
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

/**
 * @author Yauheni Efimenko
 */
@Entity
@Table(name = "players")
class Player(

        @Column(name = "username", nullable = false, unique = true)
        private var username: String,

        @Column(name = "password", nullable = false)
        private var password: String

) : BaseModel(), UserDetails {

    @Column(name = "icon_path")
    var iconPath: String? = null

    @OneToOne(mappedBy = "player", fetch = FetchType.LAZY)
    val playerStats: PlayerStats? = null


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