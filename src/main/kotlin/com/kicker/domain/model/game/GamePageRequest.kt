package com.kicker.domain.model.game

import com.kicker.domain.PageRequest

/**
 * @author Yauheni Efimenko
 */
class GamePageRequest : PageRequest(maySortBy = mapOf("id" to "id", "date" to "date"))