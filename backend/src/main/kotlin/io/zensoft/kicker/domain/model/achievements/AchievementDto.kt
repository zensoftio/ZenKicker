package io.zensoft.kicker.domain.model.achievements

import io.zensoft.kicker.model.Achievement
import java.sql.Timestamp

data class AchievementDto(
        val type: String,
        val level: String,
        val date: Long
) {

    constructor(achievement: Achievement) : this(
            achievement.getAchievementType().name,
            achievement.getAchievementLevel().name,
            Timestamp.valueOf(achievement.date).time
    )

}