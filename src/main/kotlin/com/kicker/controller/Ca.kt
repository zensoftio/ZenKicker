package com.kicker.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class Ca {

    @GetMapping(value = ["/", "/rating", "/group-stage",
        "/playoffs", "/player/**", "/player", "/not-found"])
    fun main(): String {
        return "index"
    }

}