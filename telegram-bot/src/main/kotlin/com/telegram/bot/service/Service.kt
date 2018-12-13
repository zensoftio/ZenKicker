package com.telegram.bot.service

import com.telegram.bot.domain.PageRequest
import com.telegram.bot.domain.PlayerDto
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

interface ApiService {

    fun getPlayers(pageRequest: PageRequest): List<PlayerDto>

}

interface TelegramService {

    fun getCommands(): SendMessage

    fun getPlayers(pageRequest: PageRequest): SendMessage

}