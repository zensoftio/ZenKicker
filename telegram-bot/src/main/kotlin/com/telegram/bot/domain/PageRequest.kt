package com.telegram.bot.domain

data class PageRequest(
        val offset: Int = 0
) {

    fun next(): PageRequest = PageRequest(offset + 10)

    fun previous(): PageRequest {
        if (offset == 0) {
            return PageRequest()
        }
        return PageRequest(offset - 10)
    }

}