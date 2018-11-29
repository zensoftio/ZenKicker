package com.kicker.api.domain.model.game

import com.kicker.api.domain.PageRequest

class GamePageRequest : PageRequest(maySortBy = mapOf("id" to "id", "date" to "date"))