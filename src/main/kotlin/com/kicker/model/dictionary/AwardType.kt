package com.kicker.model.dictionary

/**
 * @author Yauheni Efimenko
 */
enum class AwardType(
        private val id: Int
) : Dictionary {

    WEEK_END_OVERALL_RATING(1),
    WEEK_MAX_RATING_DELTA(2),
    TOURNAMENT(3)
    ;

    override fun getId(): Int = id

}