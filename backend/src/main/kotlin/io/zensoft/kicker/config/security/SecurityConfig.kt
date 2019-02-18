package io.zensoft.kicker.config.security

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.zensoft.kicker.domain.exception.ExceptionResponse
import io.zensoft.kicker.service.PlayerService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders.CONTENT_TYPE
import org.springframework.http.HttpStatus.UNAUTHORIZED
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.AuthenticationException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author Yauheni Efimenko
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig : GlobalMethodSecurityConfiguration() {

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()


    @Configuration
    class SecurityConfigurer(
            private val userDetailsService: PlayerService,
            private val passwordEncoder: PasswordEncoder
    ) : WebSecurityConfigurerAdapter() {

        override fun configure(auth: AuthenticationManagerBuilder) {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder)
        }

        override fun configure(http: HttpSecurity) {
            http.csrf().disable()
            http.cors()

            http.authorizeRequests()
                    .antMatchers("/api/games/registration").authenticated()
                    .antMatchers("/api/players/current").authenticated()
                    .antMatchers("/api/players/username").authenticated()
                    .antMatchers("/api/players/icon").authenticated()
                    .antMatchers("/api/players/password").authenticated()
                    .antMatchers("/**").permitAll()

                    .and()

                    .exceptionHandling()
                    .authenticationEntryPoint(CustomAuthenticationEntryPoint())

                    .and()

                    .formLogin()
                    .loginPage("/login").permitAll()
                    .failureHandler(AuthenticationFailureHandler())
        }

//        companion object {
//            private val AUTH_WHITELIST = arrayOf(
//                    //static content
//                    "/css/**",
//                    "/js/**",
//                    "/images/**",
//                    "/static/**",
//                    //swagger
//                    "/v2/api-docs",
//                    "/swagger-resources",
//                    "/swagger-resources/**",
//                    "/configuration/ui",
//                    "/configuration/security",
//                    "/swagger-ui.html",
//                    "/webjars/**"
//            )
//        }

    }

    class AuthenticationFailureHandler : SimpleUrlAuthenticationFailureHandler() {

        override fun onAuthenticationFailure(request: HttpServletRequest, response: HttpServletResponse,
                                             exception: AuthenticationException) {
            val exceptionResponse = ExceptionResponse(UNAUTHORIZED.value(), "Invalid username or password")
            response.status = exceptionResponse.status
            response.addHeader(CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
            response.writer.write(jacksonObjectMapper().writeValueAsString(exceptionResponse))
        }

    }

}