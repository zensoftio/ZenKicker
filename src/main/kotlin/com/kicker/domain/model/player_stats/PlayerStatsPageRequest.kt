package com.kicker.domain.model.player_stats

import com.kicker.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.domain.Sort.Direction.DESC

/**
 * @author Yauheni Efimenko
 */
class PlayerStatsPageRequest : PageRequest(
        sortDirection = DESC
)