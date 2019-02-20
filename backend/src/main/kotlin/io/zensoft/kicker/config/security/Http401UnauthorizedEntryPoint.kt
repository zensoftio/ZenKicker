package io.zensoft.kicker.config.security

import org.springframework.http.HttpStatus.UNAUTHORIZED
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class Http401UnauthorizedEntryPoint : AuthenticationEntryPoint {

    override fun commence(request: HttpServletRequest, response: HttpServletResponse, authException: AuthenticationException) {
        response.sendError(UNAUTHORIZED.value(), UNAUTHORIZED.reasonPhrase)
    }

}