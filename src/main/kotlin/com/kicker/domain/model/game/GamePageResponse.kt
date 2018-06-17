package com.kicker.domain.model.game

import com.kicker.domain.PageResponse
import com.kicker.domain.model.player.PlayerDto
import org.springframework.data.domain.Page

/**
 * @author Yauheni Efimenko
 */
class GamePageResponse(totalCount: Long, list: List<GameDto>, val players: List<PlayerDto>
) : PageResponse<GameDto>(totalCount, list) {
    constructor(page: Page<GameDto>, players: List<PlayerDto>) : this(page.totalElements, page.content, players)
}