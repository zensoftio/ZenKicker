package com.kicker.api.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

/**
 * @author Yauheni Efimenko
 */

@Controller
class FrontendController {

    @GetMapping(value = ["/", "/dashboard", "/group-stage", "/playoffs", "/player/**", "/player", "/not-found"])
    fun frontend(): String = "index"

    @GetMapping("/login")
    fun login(): String = "login"

    @GetMapping("/sign-up")
    fun signUp(): String = "sign-up"

}