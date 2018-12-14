package com.telegram.bot.component

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.telegram.bot.config.properties.TelegramBotProperties
import com.telegram.bot.domain.PageRequest
import com.telegram.bot.exception.BadRequest
import com.telegram.bot.model.dictionary.Menu
import com.telegram.bot.model.dictionary.Menu.*
import com.telegram.bot.service.TelegramService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import java.util.regex.Pattern

@Component
class KickerBot(
        private val telegramBotProperties: TelegramBotProperties,
        private val telegramService: TelegramService
) : TelegramLongPollingBot() {

    companion object {
        private val logger = LoggerFactory.getLogger(KickerBot::class.java)
    }


    override fun getBotUsername(): String = telegramBotProperties.username!!

    override fun getBotToken(): String = telegramBotProperties.token!!

    override fun onUpdateReceived(update: Update) {
        if (update.hasMessage() && update.message.hasText()) {
            try {
                val sendMessage = handleMenu(update.message.text, PageRequest())

                execute(sendMessage.setChatId(update.message.chatId))
            } catch (e: TelegramApiException) {
                logger.error(e.message, e)
            } catch (e: BadRequest) {
                execute(SendMessage().setChatId(update.message.chatId)
                        .setText(e.message))
            }
        } else if (update.hasCallbackQuery()) {
            val dataParts = update.callbackQuery.data.split(Pattern.compile(":{3}"))
            val pageRequest = jacksonObjectMapper().readValue(dataParts[1], PageRequest::class.java)

            try {
                val sendMessage = handleMenu(dataParts[0], pageRequest)

                execute(sendMessage.setChatId(update.callbackQuery.message.chatId))
            } catch (e: TelegramApiException) {
                logger.error(e.message, e)
            } catch (e: BadRequest) {
                execute(SendMessage().setChatId(update.message.chatId)
                        .setText(e.message))
            }
        }
    }

    private fun handleMenu(data: String, pageRequest: PageRequest): SendMessage {
        val dataParts = data.split(" ")
        return when (Menu.getItemMenu((dataParts[0]))) {
            HELP -> {
                telegramService.getCommands()
            }
            PLAYERS -> {
                telegramService.getPlayers(pageRequest)
            }
            PLAYER_STATS -> telegramService.getPlayerStats(dataParts[1].toLong())
            else -> {
                SendMessage().setText("The command hasn`t found!")
            }
        }
    }

}