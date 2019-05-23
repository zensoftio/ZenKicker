package io.zensoft.kicker.model

import io.zensoft.kicker.model.base.BaseModel
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

        @Column(name = "email", nullable = false, unique = true)
        var email: String,

        @Column(name = "full_name", nullable = false)
        var fullName: String,

        @Column(name = "password", nullable = false)
        private var password: String

) : BaseModel(), UserDetails {

    @Column(name = "icon_path")
    var iconPath: String? = null


    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = mutableListOf()

    override fun isEnabled(): Boolean = true

    override fun getUsername(): String = email

    override fun isCredentialsNonExpired(): Boolean = true

    override fun getPassword(): String = password

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    fun setPassword(password: String) {
        this.password = password
    }

}