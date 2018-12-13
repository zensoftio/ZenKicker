package com.telegram.bot.model.dictionary

enum class Menu(
        private val command: String,
        private val description: String
) {

    HELP("/help", "The list of commands"),
    PLAYERS("/players", "The list of players");

    companion object {
        fun getItemMenu(command: String): Menu? = values().firstOrNull { it.command == command }
    }

    fun getCommand(): String = command

    fun getDescription(): String = description

}