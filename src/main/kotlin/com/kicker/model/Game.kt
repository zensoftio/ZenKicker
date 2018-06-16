package com.kicker.model

import com.kicker.model.base.BaseModel
import java.time.LocalDateTime
import javax.persistence.*

/**
 * @author Yauheni Efimenko
 */
@Entity
@Table(name = "games")
class Game(

        @ManyToOne
        @JoinColumn(name = "red_player1_id", nullable = false)
        val redPlayer1: Player,

        @ManyToOne
        @JoinColumn(name = "red_player2_id", nullable = false)
        val redPlayer2: Player,

        @ManyToOne
        @JoinColumn(name = "yellow_player1_id", nullable = false)
        val yellowPlayer1: Player,

        @ManyToOne
        @JoinColumn(name = "yellow_player2_id", nullable = false)
        val yellowPlayer2: Player,

        @Column(name = "red_team_goals", nullable = false)
        val redTeamGoals: Int,

        @Column(name = "yellow_team_goals", nullable = false)
        val yellowTeamGoals: Int,

        @Column(name = "date", nullable = false, columnDefinition = "DATE")
        val date: LocalDateTime,

        @Column(name = "reported_by", nullable = false)
        val reportedBy: String

) : BaseModel()