package com.telegram.bot.util

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.telegram.bot.domain.PageRequest
import org.telegram.telegrambots.meta.api.methods.ParseMode
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

object TelegramUtil {

    fun doSendMessage(text: String): SendMessage = SendMessage()
            .setText(text).setParseMode(ParseMode.HTML)

    fun doSendMessagePageable(text: String, command: String, pageRequest: PageRequest, finished: Boolean = false): SendMessage =
            doSendMessage(text)
                    .setReplyMarkup(pages(command, pageRequest, finished))

    fun doSendMessageAgreement(text: String, command: String, data: String): SendMessage =
            doSendMessage(text)
                    .setReplyMarkup(agreement(command, data))

    private fun pages(command: String, pageRequest: PageRequest, finished: Boolean = false): ReplyKeyboard {
        val row = mutableListOf<InlineKeyboardButton>()

        if (pageRequest.offset != 0) {
            val previous = jacksonObjectMapper().writeValueAsString(pageRequest.previous())
            row.add(InlineKeyboardButton().setText("Previous").setCallbackData("$command:::$previous"))
        }

        if (!finished) {
            val next = jacksonObjectMapper().writeValueAsString(pageRequest.next())
            row.add(InlineKeyboardButton().setText("Next").setCallbackData("$command:::$next"))
        }

        return InlineKeyboardMarkup().apply { this.keyboard = listOf(row) }
    }

    private fun agreement(command: String, data: String): ReplyKeyboard {
        val row = listOf(
                InlineKeyboardButton().setText("No").setCallbackData("$command:::$data false"),
                InlineKeyboardButton().setText("Yes").setCallbackData("$command:::$data true")
        )

        return InlineKeyboardMarkup().apply { this.keyboard = listOf(row) }
    }

}