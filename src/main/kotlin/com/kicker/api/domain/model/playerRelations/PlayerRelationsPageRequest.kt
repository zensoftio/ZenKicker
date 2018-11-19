package com.kicker.api.domain.model.playerRelations

import com.kicker.api.domain.PageRequest
import org.springframework.data.domain.Sort.Direction.DESC

/**
 * @author Yauheni Efimenko
 */
class PlayerRelationsPageRequest : PageRequest(
        sortBy = "winningPercentage",
        sortDirection = DESC,
        maySortBy = mapOf("id" to "id", "winningPercentage" to "winningPercentage")
)