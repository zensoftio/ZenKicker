package io.zensoft.kicker.config.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotNull

/**
 * @author Yauheni Efimenko
 */

@ConfigurationProperties(prefix = "player.rating")
@Validated
@Component
data class PlayerSettingsProperties(

        /**
         * Count of games in order to became an active player
         */
        @field:NotNull var countGames: Long? = null,

        /**
         * Count of weeks for which the rating is taken
         */
        @field:NotNull var countWeeks: Long? = null

)