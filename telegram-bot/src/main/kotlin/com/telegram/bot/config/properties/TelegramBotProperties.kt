package com.telegram.bot.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotBlank

@ConfigurationProperties(prefix = "telegrambot")
@Validated
@Component
data class TelegramBotProperties(

        @field:NotBlank var username: String? = null,

        @field:NotBlank var token: String? = null

)