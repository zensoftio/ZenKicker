package com.telegram.bot.service

import com.telegram.bot.domain.PageRequest
import com.telegram.bot.model.dictionary.Menu.PLAYERS
import com.telegram.bot.model.dictionary.Menu.values
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

}