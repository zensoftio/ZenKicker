package io.zensoft.kicker.utils

/**
 * @author Yauheni Efimenko
 */
object RatingUtils {

    private const val MAX_LOSING_PERCENTS = 6.0
    private const val GOAL_DECREASE_COEFF = 1.0 / 4.0
    private const val SKILL_CORRECTION_DEGREE = 0.2


    fun getLosingPercents(goalsAgainst: Int, skillCorrection: Double): Double =
            skillCorrection * (MAX_LOSING_PERCENTS - (goalsAgainst * GOAL_DECREASE_COEFF))

    fun getSkillCorrection(losersTotalRating: Double, winnersTotalRating: Double): Double =
            Math.pow(losersTotalRating / winnersTotalRating, SKILL_CORRECTION_DEGREE)

    fun getObsolescenceDelta(delta: Double, weeks: Long, weeksAgo: Long): Double {
        val obsolescenceStep = 1.0 / weeks
        return delta * (1.0 - weeksAgo * obsolescenceStep)
    }

}