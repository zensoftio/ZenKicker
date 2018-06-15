package com.kicker.model.dictionary

/**
 * @author Yauheni Efimenko
 */
enum class AwardType(
        private val id: Int,
        private val view: String
) : Dictionary {

    WEEK_END_OVERALL_RATING(1, "star"),
    WEEK_MAX_RATING_DELTA(2, "hammersickle"),
    TOURNAMENT(3, TODO())
    ;

    override fun getId(): Int = id

    fun getView(): String = view

}