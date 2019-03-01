package io.zensoft.kicker.model.dictionary

import io.zensoft.kicker.model.base.Dictionary

/**
 * @author Yauheni Efimenko
 */
enum class AchievementType(private val id: Int) : Dictionary {

    WEEK_END_OVERALL_RATING(1);

    override fun getId(): Int = id

}