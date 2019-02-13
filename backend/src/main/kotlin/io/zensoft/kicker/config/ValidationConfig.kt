package io.zensoft.kicker.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor

/**
 * Adding validation on @PathVariable and @RequestParam
 * In order validate param in method use @Validated over class
 *
 * @author Yauheni Efimenko
 */

@Configuration
class ValidationConfig {

    @Bean
    fun methodValidationPostProcessor(): MethodValidationPostProcessor {
        return MethodValidationPostProcessor()
    }

}