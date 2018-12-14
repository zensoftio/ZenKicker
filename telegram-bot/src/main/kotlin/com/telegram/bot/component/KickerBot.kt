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
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import java.util.*
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
            val dataParts = update.message.text.split(" ")
            try {
                var data: String? = null
                if (dataParts.size != 1) {
                    data = dataParts.subList(1, dataParts.size).joinToString(separator = " ")
                }
                val sendMessage = handleMenu(dataParts[0], data)

                execute(sendMessage.setChatId(update.message.chatId))
            } catch (e: TelegramApiException) {
                logger.error(e.message, e)
            } catch (e: BadRequest) {
                execute(SendMessage().setChatId(update.message.chatId)
                        .setText(e.message))
            }
        } else if (update.hasCallbackQuery()) {
            val dataParts = update.callbackQuery.data.split(Pattern.compile(":{3}"))

            try {
                val sendMessage = handleMenu(dataParts[0], dataParts[1])

                execute(sendMessage.setChatId(update.callbackQuery.message.chatId))
                execute(DeleteMessage(update.callbackQuery.message.chatId, update.callbackQuery.message.messageId))
            } catch (e: TelegramApiException) {
                logger.error(e.message, e)
            } catch (e: BadRequest) {
                execute(SendMessage().setChatId(update.callbackQuery.message.chatId)
                        .setText(e.message))
            }
        }
    }

    private fun handleMenu(command: String, data: String?): SendMessage {
        return when (Menu.getItemMenu((command))) {
            HELP -> telegramService.getCommands()
            PLAYERS -> {
                var pageRequest = PageRequest()
                if (Objects.nonNull(data)) {
                    pageRequest = jacksonObjectMapper().readValue(data!!, PageRequest::class.java)
                }
                telegramService.getPlayers(pageRequest)
            }
            PLAYER_STATS -> telegramService.getPlayerStats(data!!.toLong())
            GAME_REGISTRATION -> {
                val dataParts = data!!.split(" ")
                if (dataParts.size == 5) {
                    telegramService.gameRegistration(data)
                } else {
                    telegramService.doAgreementRegistration(data)
                }
            }
            else -> {
                SendMessage().setText("The command hasn`t found!")
            }
        }
    }

}