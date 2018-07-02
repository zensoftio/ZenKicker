package com.kicker.domain.model.player

import com.kicker.domain.PageRequest

/**
 * @author Yauheni Efimenko
 */
class PlayerPageRequest : PageRequest(maySortBy = mapOf("id" to "id", "rating" to "currentRating"))