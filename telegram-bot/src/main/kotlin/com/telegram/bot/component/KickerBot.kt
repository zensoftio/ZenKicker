package com.telegram.bot.component

import com.telegram.bot.config.properties.TelegramBotProperties
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException

@Component
class KickerBot(
        private val telegramBotProperties: TelegramBotProperties
) : TelegramLongPollingBot() {

    companion object {
        private val logger = LoggerFactory.getLogger(KickerBot::class.java)
    }


    override fun getBotUsername(): String = telegramBotProperties.username!!

    override fun getBotToken(): String = telegramBotProperties.token!!

    override fun onUpdateReceived(update: Update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.message.hasText()) {
            val chatId = update.message.chatId
            val text = update.message.text

            val msg = SendMessage()
                    .setChatId(chatId)
                    .setText(text)

            try {
                execute(msg) // Sending our message object to user
            } catch (e: TelegramApiException) {
                logger.error(e.message, e)
            }
        }
    }

}