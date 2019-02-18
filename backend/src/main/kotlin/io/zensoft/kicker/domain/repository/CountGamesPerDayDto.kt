package io.zensoft.kicker.domain.repository

import java.time.LocalDate
import java.time.LocalDateTime

/**
 * @author Yauheni Efimenko
 */

data class CountGamesPerDayDto(
        val date: LocalDate,
        val count: Long
) {

    constructor(date: LocalDateTime, count: Long) : this(date.toLocalDate(), count)

}