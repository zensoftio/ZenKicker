package com.kicker.domain.model.game

import com.kicker.domain.PageRequest
import org.springframework.data.domain.Sort.Direction.DESC

/**
 * @author Yauheni Efimenko
 */
class GamePageRequest : PageRequest(
        sortBy = "date",
        sortDirection = DESC,
        maySortBy = mapOf("id" to "id", "date" to "date")
)