package com.telegram.bot.domain

data class GameRegistrationRequest(
        val winner1Id: Long,
        val winner2Id: Long,
        val loser1Id: Long,
        val loser2Id: Long,
        val losersGoals: Int
)