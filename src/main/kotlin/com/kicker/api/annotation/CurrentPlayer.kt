package com.kicker.api.annotation

import org.springframework.security.core.annotation.AuthenticationPrincipal

/**
 * @author Yauheni Efimenko
 */
@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@AuthenticationPrincipal
annotation class CurrentPlayer
