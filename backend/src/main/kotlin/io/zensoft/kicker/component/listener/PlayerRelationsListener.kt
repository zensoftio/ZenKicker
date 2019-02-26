package io.zensoft.kicker.component.listener

import io.zensoft.kicker.model.Game
import io.zensoft.kicker.service.PlayerRelationsService
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

/**
 * @author Yauheni Efimenko
 */

@Component
class PlayerRelationsListener(
        private val playerRelationsService: PlayerRelationsService
) {

    @EventListener
    fun handleRegistrationGame(game: Game) {
        with(game) {
            val winner1ToWinner2 = playerRelationsService.getByPlayerAndPartner(winner1.id, winner2.id)
            val winner2ToWinner3 = playerRelationsService.getByPlayerAndPartner(winner2.id, winner1.id)
            val loser1ToLoser2 = playerRelationsService.getByPlayerAndPartner(loser1.id, loser2.id)
            val loser2ToLoser1 = playerRelationsService.getByPlayerAndPartner(loser2.id, loser1.id)

            winner1ToWinner2.addWin()
            winner2ToWinner3.addWin()
            loser1ToLoser2.addLoss()
            loser2ToLoser1.addLoss()
        }
    }

}
