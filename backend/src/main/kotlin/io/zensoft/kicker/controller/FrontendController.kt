package io.zensoft.kicker.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

/**
 * @author Yauheni Efimenko
 */

@Controller
class FrontendController {

    @GetMapping(value = ["/", "/dashboard", "/group-stage", "/playoffs", "/players/**", "/players", "/games", "/not-found"])
    fun frontend(): String = "index"

    @GetMapping("/login")
    fun login(): String = "login"

    @GetMapping("/sign-up")
    fun signUp(): String = "sign-up"

}