package com.kicker.service

import com.kicker.model.Award
import com.kicker.model.Player
import com.kicker.repository.AwardRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

/**
 * @author Yauheni Efimenko
 */
@Service
@Transactional(readOnly = true)
class DefaultAwardService(
        private val repository: AwardRepository
) : DefaultBaseService<Award, AwardRepository>(repository), AwardService {

    override fun getAllByPlayer(player: Player): List<Award> {
        val awards = repository.findByPlayer(player)
        Collections.sort(awards, AwardsComparator())
        return awards
    }

    private class AwardsComparator : Comparator<Award> {
        override fun compare(o1: Award, o2: Award): Int {
            val result = o1.getAwardType().getId().compareTo(o2.getAwardType().getId())
            if (0 == result) {
                return o1.getAwardDegree().getId().compareTo(o2.getAwardDegree().getId())
            }

            return result
        }
    }

}