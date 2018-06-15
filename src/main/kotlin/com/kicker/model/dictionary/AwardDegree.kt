package com.kicker.model.dictionary

/**
 * @author Yauheni Efimenko
 */
enum class AwardDegree(private val id: Int) : Dictionary {

    GOLD(1),
    SILVER(2),
    BRONZE(3)
    ;

    override fun getId(): Int = id

}