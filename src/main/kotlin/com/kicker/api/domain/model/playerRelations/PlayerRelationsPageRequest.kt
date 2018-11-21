package com.kicker.api.domain.model.playerRelations

import com.kicker.api.domain.PageRequest

/**
 * @author Yauheni Efimenko
 */
class PlayerRelationsPageRequest : PageRequest(maySortBy = mapOf("id" to "id", "winningPercentage" to "winningPercentage"))