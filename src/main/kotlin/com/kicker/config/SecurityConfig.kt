package com.kicker.config

import com.kicker.service.PlayerService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


/**
 * @author Yauheni Efimenko
 */
@Configuration
@EnableGlobalMethodSecurity
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
                    .cors().configurationSource(corsConfigurationSource())

            http.authorizeRequests()
                    .antMatchers("/js/**").permitAll()
                    .antMatchers("/styles/**").permitAll()
                    .antMatchers("/images/**").permitAll()
                    .antMatchers(HttpMethod.POST, "/api/players").permitAll()

                    .antMatchers("/**").authenticated()

                    .and()

                    .formLogin()
        }

        @Bean
        fun corsConfigurationSource(): CorsConfigurationSource {
            val configuration = CorsConfiguration()
            configuration.allowedOrigins = listOf("*")
            configuration.allowedMethods = listOf("*")

            val source = UrlBasedCorsConfigurationSource()
            source.registerCorsConfiguration("/**", configuration)

            return source
        }

    }

}