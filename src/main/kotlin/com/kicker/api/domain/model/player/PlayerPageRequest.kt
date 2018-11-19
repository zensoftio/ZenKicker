package com.kicker.api.domain.model.player

import com.kicker.domain.PageRequest
import org.springframework.data.domain.Sort.Direction.DESC

/**
 * @author Yauheni Efimenko
 */
class PlayerPageRequest : PageRequest(
        sortBy = "rating",
        sortDirection = DESC,
        maySortBy = mapOf("id" to "id", "rating" to "rating")
)