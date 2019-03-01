package io.zensoft.kicker.model.dictionary

import io.zensoft.kicker.model.base.Dictionary

/**
 * @author Yauheni Efimenko
 */
enum class AchievementLevel(private val id: Int) : Dictionary {

    GOLD(1),
    SILVER(2),
    BRONZE(3);

    override fun getId(): Int = id

}