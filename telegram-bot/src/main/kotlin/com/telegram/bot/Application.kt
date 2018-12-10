package com.telegram.bot

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.telegram.telegrambots.ApiContextInitializer

@SpringBootApplication
class Application

fun main(args: Array<String>) {
    //Add this line to initialize bots context
    ApiContextInitializer.init()

    SpringApplication.run(Application::class.java, *args)
}