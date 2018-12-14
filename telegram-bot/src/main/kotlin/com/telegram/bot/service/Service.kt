package com.telegram.bot.service

import com.telegram.bot.domain.GameRegistrationRequest
import com.telegram.bot.domain.PageRequest
import com.telegram.bot.domain.PlayerDto
import com.telegram.bot.domain.PlayerStatsDto
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

interface ApiService {

    fun getPlayer(playerId: Long): PlayerDto

    fun getPlayers(pageRequest: PageRequest): List<PlayerDto>

    fun getPlayerStats(playerId: Long): PlayerStatsDto

    fun gameRegistration(request: GameRegistrationRequest)

}

interface TelegramService {

    fun getCommands(): SendMessage

    fun getPlayers(pageRequest: PageRequest): SendMessage

    fun getPlayerStats(playerId: Long): SendMessage

    fun gameRegistration(data: String): SendMessage

    fun doAgreementRegistration(data: String): SendMessage

}