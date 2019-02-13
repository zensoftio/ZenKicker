package io.zensoft.kicker.config.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

@ConfigurationProperties(prefix = "static.data")
@Validated
@Component
data class StaticDataProperties(

        @field:NotBlank var iconsUrl: String? = null,

        /**
         * Location must end in slash '/'
         */
        @field:Pattern(regexp = ".*/$")
        @field:NotBlank var location: String? = null

)