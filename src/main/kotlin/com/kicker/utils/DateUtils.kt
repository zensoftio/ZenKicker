package com.kicker.utils

import java.time.DayOfWeek.MONDAY
import java.time.DayOfWeek.SUNDAY
import java.time.LocalDate

/**
 * @author Yauheni Efimenko
 */
object DateUtils {

    fun getIntervalDatesOfWeek(weeksAgo: Long): Pair<LocalDate, LocalDate> {
        if (weeksAgo == 0L) {
            return Pair(LocalDate.now().with(MONDAY), LocalDate.now())
        }

        val monday = LocalDate.now().with(MONDAY).minusWeeks(weeksAgo)
        val sunday = LocalDate.now().with(SUNDAY).minusWeeks(weeksAgo)
        return Pair(monday, sunday)
    }

    /*
    * Current week is number 0, so 10 week is number 9
    * */
    fun getStartDate10Weeks(): LocalDate = LocalDate.now().with(MONDAY).minusWeeks(9)

}