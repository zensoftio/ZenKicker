package com.telegram.bot.model.dictionary

enum class Menu(
        private val command: String,
        private val description: String
) {

    HELP("/help", "The list of commands"),
    PLAYERS("/players", "The list of players"),
    PLAYER_STATS("/stats", "The stats of player. Example: /stats {player_id}"),
    GAME_REGISTRATION("/registration", "Registration of game. Example: /registration {winner1_id} " +
        "{winner2_id} {loser1_id} {loser2_id} {goals_against}");

    companion object {
        fun getItemMenu(command: String): Menu? = values().firstOrNull { it.command == command }
    }

    fun getCommand(): String = command

    fun getDescription(): String = description

}