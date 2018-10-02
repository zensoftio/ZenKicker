package com.kicker.config

import com.kicker.service.PlayerService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod.POST
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

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

        override fun configure(web: WebSecurity) {
            web.ignoring().antMatchers("/css/**", "/js/**", "/images/**")
        }

        override fun configure(http: HttpSecurity) {
            http.csrf().disable()
                .cors()

            http.authorizeRequests()
                    .antMatchers("/sign-up").permitAll()
                    .antMatchers(POST, "/api/players").permitAll()
                    .antMatchers("/**").authenticated()

                    .and()

                    .formLogin()
                    .loginPage("/login").permitAll()
        }

    }

}