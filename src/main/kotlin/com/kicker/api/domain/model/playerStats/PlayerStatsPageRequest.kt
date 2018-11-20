package com.kicker.api.domain.model.playerStats

import com.kicker.api.domain.PageRequest
import org.springframework.data.domain.Sort.Direction.DESC

/**
 * @author Yauheni Efimenko
 */
class PlayerStatsPageRequest : PageRequest(
        sortDirection = DESC
)