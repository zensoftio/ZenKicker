package com.telegram.bot.service

import com.telegram.bot.domain.PageRequest
import com.telegram.bot.domain.PlayerDto
import com.telegram.bot.domain.PlayerStatsDto
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

interface ApiService {

    fun getPlayers(pageRequest: PageRequest): List<PlayerDto>

    fun getPlayerStats(playerId: Long): PlayerStatsDto

}

interface TelegramService {

    fun getCommands(): SendMessage

    fun getPlayers(pageRequest: PageRequest): SendMessage

    fun getPlayerStats(playerId: Long): SendMessage

}