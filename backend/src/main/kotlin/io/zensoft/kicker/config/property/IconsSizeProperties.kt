package io.zensoft.kicker.config.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotNull

/**
 * @author Yauheni Efimenko
 */

@ConfigurationProperties(prefix = "icons.size")
@Validated
@Component
data class IconsSizeProperties(

        @field:NotNull var height: Int? = null,

        @field:NotNull var width: Int? = null

)