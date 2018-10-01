package com.kicker.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor

/**
 * Adding validation on @PathVariable and @RequestParam
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