package com.kicker.api.config.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotBlank

@ConfigurationProperties(prefix = "aws")
@Validated
@Component
data class AwsProperties(

        @field:NotBlank var accessKey: String? = null,

        @field:NotBlank var secretKey: String? = null,

        @field:NotBlank var endpointUrl: String? = null,

        @field:NotBlank var region: String? = null,

        @field:NotBlank var bucketName: String? = null

)