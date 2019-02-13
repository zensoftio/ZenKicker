package io.zensoft.kicker.controller.api

import io.swagger.annotations.Api
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * @author Yauheni Efimenko
 */
@Api(description = "Login Controller", tags = ["login-controller"])
@RestController
class SwaggerController {

    @PostMapping("/login")
    fun login(@RequestParam username: String, @RequestParam password: String) {
    }

    @PostMapping("/logout")
    fun logout() {
    }

}