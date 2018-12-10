package com.telegram.bot.component

import com.telegram.bot.config.properties.TelegramBotProperties
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import java.util.*


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
//            val text = update.message.text
//            val msg = SendMessage()
//                    .setChatId(chatId)
//                    .setText(text)

            try {
                execute(sendInlineKeyBoardMessage(chatId)) // Sending our message object to user
            } catch (e: TelegramApiException) {
                logger.error(e.message, e)
            }
        } else if (update.hasCallbackQuery()) {
            try {
                execute(SendMessage()
                        .setText(update.callbackQuery.data)
                        .setChatId(update.callbackQuery.message.chatId))
            } catch (e: TelegramApiException) {
                logger.error(e.message, e)
            }
        }
    }

    fun sendInlineKeyBoardMessage(chatId: Long): SendMessage {
        val inlineKeyboardMarkup = InlineKeyboardMarkup()
        val inlineKeyboardButton1 = InlineKeyboardButton()
        val inlineKeyboardButton2 = InlineKeyboardButton()
        inlineKeyboardButton1.text = "Тык"
        inlineKeyboardButton1.callbackData = "Button \"Тык\" has been pressed"
        inlineKeyboardButton2.text = "Тык2"
        inlineKeyboardButton2.callbackData = "Button \"Тык2\" has been pressed"

        val keyboardButtonsRow1 = ArrayList<InlineKeyboardButton>()
        val keyboardButtonsRow2 = ArrayList<InlineKeyboardButton>()
        keyboardButtonsRow1.add(inlineKeyboardButton1)
        keyboardButtonsRow1.add(InlineKeyboardButton().setText("Fi4a").setCallbackData("CallFi4a"))
        keyboardButtonsRow2.add(inlineKeyboardButton2)

        val rowList = ArrayList<List<InlineKeyboardButton>>()
        rowList.add(keyboardButtonsRow1)
        rowList.add(keyboardButtonsRow2)

        inlineKeyboardMarkup.keyboard = rowList

        return SendMessage().setChatId(chatId).setText("Пример").setReplyMarkup(inlineKeyboardMarkup)
    }

}