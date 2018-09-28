package com.kicker.model

import com.kicker.model.base.BaseModel
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.Pattern

/**
 * @author Yauheni Efimenko
 */
@Entity
@Table(name = "games")
class Game(

        @Pattern(regexp = "\\d:\\d")
        @Column(name = "score", nullable = false)
        val score: String,

        @ManyToOne
        @JoinColumn(name = "reported_by", nullable = false)
        val reportedBy: Player,

        @Column(name = "date", nullable = false, columnDefinition = "DATE")
        val date: LocalDateTime = LocalDateTime.now(),

        @OneToMany(mappedBy = "game")
        val playerStatsList: List<PlayerStats> = listOf()

) : BaseModel()