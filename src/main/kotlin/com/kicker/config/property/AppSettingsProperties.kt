package com.kicker.config.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotNull

/**
 * @author Yauheni Efimenko
 */

@ConfigurationProperties(prefix = "players.rating")
@Validated
@Component
class AppSettingsProperties(

        @field:NotNull var countGames: Long? = null,

        @field:NotNull var countWeeks: Long? = null

)