package com.telegram.bot.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotBlank

@ConfigurationProperties(prefix = "kicker.api")
@Validated
@Component
data class KickerProperties(

        @field:NotBlank var host: String? = null

)