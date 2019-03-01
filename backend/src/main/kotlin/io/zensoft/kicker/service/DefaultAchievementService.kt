package io.zensoft.kicker.service

import io.zensoft.kicker.model.Achievement
import io.zensoft.kicker.model.Player
import io.zensoft.kicker.model.dictionary.AchievementLevel
import io.zensoft.kicker.model.dictionary.AchievementType
import io.zensoft.kicker.repository.AchievementRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author Yauheni Efimenko
 */
@Service
@Transactional(readOnly = true)
class DefaultAchievementService(
        private val repository: AchievementRepository,
        private val playerService: PlayerService
) : DefaultBaseService<Achievement>(repository), AchievementService {

    override fun getAllByPlayer(playerId: Long): List<Achievement> {
        val player = playerService.get(playerId)
        return repository.findAllByPlayer(player)
    }

    @Transactional
    override fun achieve(player: Player, type: AchievementType, level: AchievementLevel): Achievement {
        val achievement = Achievement(player, type, level)
        return repository.save(achievement)
    }

}