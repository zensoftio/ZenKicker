package com.kicker.config.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotBlank

/**
 * @author Yauheni Efimenko
 */

@ConfigurationProperties(prefix = "static.folders")
@Validated
@Component
data class StaticFoldersProperties(

        /**
         * Folder for static content (images)
         */
        @field:NotBlank var images: String? = null

)