package com.kicker.service

import com.kicker.model.Award
import com.kicker.repository.AwardRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author Yauheni Efimenko
 */
@Service
@Transactional(readOnly = true)
class DefaultAwardService(
        private val awardRepository: AwardRepository
) : DefaultBaseService<Award, AwardRepository>(awardRepository), AwardService