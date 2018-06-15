package com.kicker.model

import com.kicker.model.base.BaseModel
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.OneToMany
import javax.persistence.Table

/**
 * @author Yauheni Efimenko
 */
@Entity
@Table(name = "players")
class Player(

        @Column(name = "username", nullable = false, unique = true)
        var username: String,

        @Column(name = "first_name", nullable = false)
        var firstName: String,

        @Column(name = "last_name", nullable = false)
        var lastName: String,

        @Column(name = "active", nullable = false)
        var active: Boolean = false,

        @OneToMany(mappedBy = "player")
        val awards: List<Award> = listOf()

) : BaseModel()