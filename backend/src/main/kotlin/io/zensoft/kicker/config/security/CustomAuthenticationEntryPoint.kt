package io.zensoft.kicker.config.security

import org.springframework.http.HttpStatus.UNAUTHORIZED
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CustomAuthenticationEntryPoint : AuthenticationEntryPoint {

    override fun commence(request: HttpServletRequest, response: HttpServletResponse, authException: AuthenticationException) {
        if (request.requestURI.startsWith("/api")) {
            response.sendError(UNAUTHORIZED.value())
            return
        }

        response.sendRedirect("/login")
    }

}