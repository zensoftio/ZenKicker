package com.kicker.domain.model.playerStats

import com.kicker.domain.PageRequest
import org.springframework.data.domain.Sort.Direction.DESC

/**
 * @author Yauheni Efimenko
 */
class PlayerStatsPageRequest : PageRequest(
        sortDirection = DESC
)