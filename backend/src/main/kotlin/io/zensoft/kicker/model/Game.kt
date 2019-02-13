package io.zensoft.kicker.model

import io.zensoft.kicker.model.base.BaseModel
import java.time.LocalDateTime
import javax.persistence.*

/**
 * @author Yauheni Efimenko
 */
@Entity
@Table(name = "games")
class Game(

        @Column(name = "losers_goals", nullable = false)
        val losersGoals: Int,

        @ManyToOne
        @JoinColumn(name = "winner1_id", nullable = false)
        val winner1: Player,

        @ManyToOne
        @JoinColumn(name = "winner2_id", nullable = false)
        val winner2: Player,

        @ManyToOne
        @JoinColumn(name = "loser1_id", nullable = false)
        val loser1: Player,

        @ManyToOne
        @JoinColumn(name = "loser2_id", nullable = false)
        val loser2: Player,

        @ManyToOne
        @JoinColumn(name = "reported_by", nullable = false)
        val reportedBy: Player,

        @Column(name = "date", nullable = false, columnDefinition = "TIMESTAMP")
        val date: LocalDateTime = LocalDateTime.now()

) : BaseModel()