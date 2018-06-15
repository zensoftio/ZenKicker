package com.kicker.service

import com.kicker.model.Game
import com.kicker.repository.GameRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author Yauheni Efimenko
 */
@Service
@Transactional(readOnly = true)
class DefaultGameService(
        private val gameRepository: GameRepository
) : DefaultBaseService<Game, GameRepository>(gameRepository), GameService