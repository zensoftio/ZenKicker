package com.kicker.service

import com.kicker.model.Award
import com.kicker.repository.AwardRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author Yauheni Efimenko
 */
@Service
@Transactional(readOnly = true)
class DefaultAwardService(
        private val repository: AwardRepository,
        private val playerService: PlayerService
) : DefaultBaseService<Award, AwardRepository>(repository), AwardService {

    override fun getAllByPlayer(playerId: Long): Page<Award> {
        val player = playerService.get(playerId)
        return PageImpl(repository.findByPlayer(player))
    }

}