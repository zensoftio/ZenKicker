package com.kicker.api.domain.model.playerStats

import com.kicker.api.domain.PageRequest

class PlayerStatsPageRequest : PageRequest(maySortBy = mapOf("id" to "id", "rating" to "rating"))