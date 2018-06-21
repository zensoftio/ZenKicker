package com.kicker.service

import com.kicker.model.Award
import com.kicker.model.Player
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
        private val repository: AwardRepository
) : DefaultBaseService<Award, AwardRepository>(repository), AwardService {

    override fun getAllByPlayer(player: Player): Page<Award> {
        return PageImpl(repository.findByPlayer(player))
    }

}