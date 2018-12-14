package com.telegram.bot.service

import com.telegram.bot.domain.GameRegistrationRequest
import com.telegram.bot.domain.PageRequest
import com.telegram.bot.exception.BadRequest
import com.telegram.bot.model.dictionary.Menu.*
import com.telegram.bot.util.TelegramUtil
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import java.util.*

@Service
class DefaultTelegramService(
        private val apiService: ApiService
) : TelegramService {

    companion object {
        private const val END_LIST = "The list has finished!"
    }


    override fun getCommands(): SendMessage {
        val response = StringJoiner("\n")
        values().forEach { response.add("<b>${it.getCommand()}</b> - ${it.getDescription()}") }

        return TelegramUtil.doSendMessage(response.toString())
    }

    override fun getPlayers(pageRequest: PageRequest): SendMessage {
        val players = apiService.getPlayers(pageRequest)

        val response = StringJoiner("\n").setEmptyValue(END_LIST)
        players.forEach { response.add("${it.username} - <b>id: ${it.id}</b>") }

        return TelegramUtil.doSendMessagePageable(response.toString(), PLAYERS.getCommand(), pageRequest,
                (response.toString() == END_LIST))
    }

    override fun getPlayerStats(playerId: Long): SendMessage {
        val playerStats = apiService.getPlayerStats(playerId)
        val response = StringJoiner("\n")
        with(response) {
            with(playerStats) {
                add("Rating: <b>$rating</b>")
                add("Games played: <b>$countGames</b>")
                add("Games rated: <b>$rated</b>")
                add("Games won: <b>$countWins</b>")
                add("Games lost: <b>$countLosses</b>")
                add("Winning percentage: <b>$winningPercentage</b>")
                add("Goals for: <b>$goalsFor</b>")
                add("Goals against: <b>$goalsAgainst</b>")
                add("Current loss streak: <b>$currentLossesStreak</b>")
                add("Longest loss streak: <b>$longestLossesStreak</b>")
                add("Current win streak: <b>$currentWinningStreak</b>")
                add("Longest win streak: <b>$longestWinningStreak</b>")
                add("Active: <b>$active</b>")
            }
        }

        return TelegramUtil.doSendMessage(response.toString())
    }

    override fun gameRegistration(data: String): SendMessage {
        val dataParts = data.split(" ")
        if (!validateRegistrationData(dataParts)) {
            throw BadRequest("Bad request!!!")
        }

        val winner1 = apiService.getPlayer(dataParts[0].toLong())
        val winner2 = apiService.getPlayer(dataParts[1].toLong())
        val loser1 = apiService.getPlayer(dataParts[2].toLong())
        val loser2 = apiService.getPlayer(dataParts[3].toLong())

        val response = StringJoiner("\n")
        with(response) {
            add(winner1.username)
            add(winner2.username)
            add("10:${dataParts[4]}")
            add(loser1.username)
            add(loser2.username)
            add("\n<b>All right?</b>")
        }
        return TelegramUtil.doSendMessageAgreement(response.toString(), GAME_REGISTRATION.getCommand(), data)
    }

    override fun doAgreementRegistration(data: String): SendMessage {
        val dataParts = data.split(" ")
        val agreement = dataParts[5].toBoolean()

        if (!agreement) {
            return TelegramUtil.doSendMessage("Registration is reject!")
        }

        val request = GameRegistrationRequest(
                dataParts[0].toLong(),
                dataParts[1].toLong(),
                dataParts[2].toLong(),
                dataParts[3].toLong(),
                dataParts[4].toInt()
        )
        apiService.gameRegistration(request)
        return TelegramUtil.doSendMessage("Success!")
    }

    private fun validateRegistrationData(dataParts: List<String>): Boolean {
        if (dataParts.size != 5) {
            return false
        }

        val winner1 = dataParts[0].toLongOrNull()
        val winner2 = dataParts[1].toLongOrNull()
        val loser1 = dataParts[2].toLongOrNull()
        val loser2 = dataParts[3].toLongOrNull()
        val goalsAgainst = dataParts[4].toIntOrNull()

        if (Objects.isNull(winner1)
                || Objects.isNull(winner2)
                || Objects.isNull(loser1)
                || Objects.isNull(loser2)
                || Objects.isNull(goalsAgainst)
                || goalsAgainst!! > 9) {
            return false
        }

        return true
    }

}