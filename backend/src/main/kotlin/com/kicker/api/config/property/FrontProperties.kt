package com.kicker.api.config.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotBlank

@ConfigurationProperties(prefix = "static.images.icons")
@Validated
@Component
data class FrontProperties(

        @field:NotBlank var url: String? = null

)