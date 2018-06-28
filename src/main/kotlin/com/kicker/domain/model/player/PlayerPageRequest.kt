package com.kicker.domain.model.player

import com.kicker.domain.PageRequest
import org.springframework.data.domain.Sort

/**
 * @author Yauheni Efimenko
 */
class PlayerPageRequest : PageRequest(sortDirection = Sort.Direction.DESC, sortBy = "rating",
        maySortBy = mapOf("id" to "id", "rating" to "currentRating"))