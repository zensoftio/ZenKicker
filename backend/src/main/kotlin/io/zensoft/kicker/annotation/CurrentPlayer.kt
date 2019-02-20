package io.zensoft.kicker.annotation

import org.springframework.security.core.annotation.AuthenticationPrincipal

@AuthenticationPrincipal
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class CurrentPlayer