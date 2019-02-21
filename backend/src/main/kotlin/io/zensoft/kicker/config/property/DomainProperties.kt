package io.zensoft.kicker.config.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 * @author Yauheni Efimenko
 */
@ConfigurationProperties(prefix = "registration")
@Component
class DomainProperties(
        var domains: List<String> = listOf()
)