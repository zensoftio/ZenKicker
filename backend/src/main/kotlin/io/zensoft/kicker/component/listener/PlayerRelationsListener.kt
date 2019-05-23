package io.zensoft.kicker.component.listener

import io.zensoft.kicker.model.Game
import io.zensoft.kicker.model.PlayerRelations
import io.zensoft.kicker.service.PlayerRelationsService
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

/**
 * @author Yauheni Efimenko
 */

@Component
class PlayerRelationsListener(
        private val playerRelationsService: PlayerRelationsService
) {

    @Transactional
    @EventListener
    fun handleRegistrationGame(game: Game) {
        with(game) {
            val winner1ToWinner2 = playerRelationsService.getByPlayerAndPartner(winner1.id, winner2.id)
            val winner2ToWinner1 = playerRelationsService.getByPlayerAndPartner(winner2.id, winner1.id)
            val loser1ToLoser2 = playerRelationsService.getByPlayerAndPartner(loser1.id, loser2.id)
            val loser2ToLoser1 = playerRelationsService.getByPlayerAndPartner(loser2.id, loser1.id)

            if (null == winner1ToWinner2) {
                playerRelationsService.add(PlayerRelations(winner1, winner2, game, true))
            } else {
                playerRelationsService.add(PlayerRelations(winner1ToWinner2, game, true))
            }

            if (null == winner2ToWinner1) {
                playerRelationsService.add(PlayerRelations(winner2, winner1, game, true))
            } else {
                playerRelationsService.add(PlayerRelations(winner2ToWinner1, game, true))
            }

            if (null == loser1ToLoser2) {
                playerRelationsService.add(PlayerRelations(loser1, loser2, game, false))
            } else {
                playerRelationsService.add(PlayerRelations(loser1ToLoser2, game, false))
            }

            if (null == loser2ToLoser1) {
                playerRelationsService.add(PlayerRelations(loser2, loser1, game, false))
            } else {
                playerRelationsService.add(PlayerRelations(loser2ToLoser1, game, false))
            }
        }
    }

}
