package com.kicker.service

import com.kicker.model.Player
import com.kicker.repository.PlayerRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author Yauheni Efimenko
 */
@Service
@Transactional(readOnly = true)
class DefaultPlayerService(
        private val playerRepository: PlayerRepository
) : DefaultBaseService<Player, PlayerRepository>(playerRepository), PlayerService