package io.zensoft.kicker.config

import io.zensoft.kicker.config.property.StaticDataProperties
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport

/**
 * @author Yauheni Efimenko
 */
@Configuration
class ResourcesConfig(
        private val staticDataProperties: StaticDataProperties
) : WebMvcConfigurationSupport() {

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        if (!registry.hasMappingForPattern("/**")) {
            registry.addResourceHandler("/**")
                    .addResourceLocations(*CLASSPATH_RESOURCE_LOCATIONS, staticDataProperties.location)
        }
    }

    companion object {
        private val CLASSPATH_RESOURCE_LOCATIONS = arrayOf(
                "classpath:/META-INF/resources/webjars/",
                "classpath:/META-INF/resources/",
                "classpath:/resources/",
                "classpath:/static/",
                "classpath:/public/"
        )
    }

}