package io.zensoft.kicker.domain.exception

import com.fasterxml.jackson.annotation.JsonInclude

/**
 * @author Yauheni Efimenko
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class ExceptionResponse(
        val status: Int,
        val message: String,
        val errors: List<ErrorDto> = emptyList()
)
