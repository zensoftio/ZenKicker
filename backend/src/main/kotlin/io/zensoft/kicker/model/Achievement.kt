package io.zensoft.kicker.model

import io.zensoft.kicker.model.base.BaseModel
import io.zensoft.kicker.model.dictionary.AchievementLevel
import io.zensoft.kicker.model.dictionary.AchievementType
import io.zensoft.kicker.utils.DictionaryUtils
import java.time.LocalDateTime
import javax.persistence.*

/**
 * @author Yauheni Efimenko
 */

@Entity
@Table(name = "achievements")
class Achievement(

        @ManyToOne
        @JoinColumn(name = "player_id", nullable = false)
        val player: Player,

        @Column(name = "achievement_type_id", nullable = false)
        private val achievementTypeId: Int,

        @Column(name = "achievement_level_id", nullable = false)
        private val achievementLevelId: Int,

        @Column(name = "date", nullable = false, columnDefinition = "TIMESTAMP")
        val date: LocalDateTime = LocalDateTime.now()

) : BaseModel() {

    constructor(player: Player, achievementType: AchievementType, achievementLevel: AchievementLevel) : this(
            player,
            achievementType.getId(),
            achievementLevel.getId()
    )


    fun getAchievementType(): AchievementType = DictionaryUtils.valueOf(AchievementType::class.java, achievementTypeId)

    fun getAchievementLevel(): AchievementLevel = DictionaryUtils.valueOf(AchievementLevel::class.java, achievementLevelId)

}