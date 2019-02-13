package io.zensoft.kicker.config.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotNull

/**
 * @author Yauheni Efimenko
 */

@ConfigurationProperties(prefix = "game")
@Validated
@Component
data class GamesSettingsProperties(

        /**
         * Maximum score in game
         */
        @field:NotNull var defaultMaxScore: Int? = null

)