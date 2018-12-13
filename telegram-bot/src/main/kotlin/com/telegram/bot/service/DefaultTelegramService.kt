package com.telegram.bot.service

import com.telegram.bot.domain.PageRequest
import com.telegram.bot.model.dictionary.Menu
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
        Menu.values().forEach { response.add("*${it.getCommand()}* - ${it.getDescription()}") }

        return TelegramUtil.doSendMessage(response.toString())
    }

    override fun getPlayers(pageRequest: PageRequest): SendMessage {
        val players = apiService.getPlayers(pageRequest)

        val response = StringJoiner("\n").setEmptyValue(END_LIST)
        players.forEach { response.add("${it.username} - *id: ${it.id}*") }

        return TelegramUtil.doSendMessagePageable(response.toString(), Menu.PLAYERS.getCommand(), pageRequest,
                (response.toString() == END_LIST))
    }

}