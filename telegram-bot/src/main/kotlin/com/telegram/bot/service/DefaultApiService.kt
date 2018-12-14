package com.telegram.bot.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.telegram.bot.config.properties.KickerProperties
import com.telegram.bot.domain.GameRegistrationRequest
import com.telegram.bot.domain.PageRequest
import com.telegram.bot.domain.PlayerDto
import com.telegram.bot.domain.PlayerStatsDto
import com.telegram.bot.exception.BadRequest
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.net.URLEncoder

@Service
class DefaultApiService(
        private val kickerProperties: KickerProperties
) : ApiService {

    private val restTemplate: RestTemplate = RestTemplate()


    override fun getPlayer(playerId: Long): PlayerDto {
        val uri = "${kickerProperties.host}/api/players/$playerId"
        val httpEntity = HttpEntity(null, login())
        val body = execute(uri, httpEntity, HttpMethod.GET)
        val response = jacksonObjectMapper().readTree(body)
        return PlayerDto(
                response.path("id").asLong(),
                response.path("username").asText()
        )
    }

    override fun getPlayers(pageRequest: PageRequest): List<PlayerDto> {
        val uri = "${kickerProperties.host}/api/players/?offset=${pageRequest.offset}"
        val httpEntity = HttpEntity(null, login())
        val body = execute(uri, httpEntity, HttpMethod.GET)
        val root = jacksonObjectMapper().readTree(body)
        val players = root.get("list").toMutableList()
        return players.map {
            PlayerDto(
                    it.path("id").asLong(),
                    it.path("username").asText()
            )
        }
    }

    override fun getPlayerStats(playerId: Long): PlayerStatsDto {
        val uri = "${kickerProperties.host}/api/players/$playerId/stats"
        val httpEntity = HttpEntity(null, login())
        val body = execute(uri, httpEntity, HttpMethod.GET)
        val response = jacksonObjectMapper().readTree(body)
        return PlayerStatsDto(
                response.path("rating").asInt(),
                response.path("countGames").asInt(),
                response.path("rated").asInt(),
                response.path("countWins").asInt(),
                response.path("countLosses").asInt(),
                response.path("winningPercentage").asDouble(),
                response.path("goalsFor").asInt(),
                response.path("goalsAgainst").asInt(),
                response.path("currentLossesStreak").asInt(),
                response.path("longestLossesStreak").asInt(),
                response.path("currentWinningStreak").asInt(),
                response.path("longestWinningStreak").asInt(),
                response.path("active").asBoolean()
        )

    }

    override fun gameRegistration(request: GameRegistrationRequest) {
        val uri = "${kickerProperties.host}/api/games/registration"
        val httpEntity = HttpEntity(request, login())
        execute(uri, httpEntity, HttpMethod.POST)
    }

    private fun <T> execute(uri: String, httpEntity: HttpEntity<T>, httpMethod: HttpMethod): String {
        val response: ResponseEntity<String>
        try {
            response = restTemplate.exchange(uri, httpMethod, httpEntity, String::class.java)
        } catch (e: Exception) {
            throw BadRequest("Bad request!!!")
        }

        return response.body!!
    }


    private fun login(): HttpHeaders {
        val username = URLEncoder.encode(kickerProperties.username!!, Charsets.UTF_8.name())
        val password = URLEncoder.encode(kickerProperties.password!!, Charsets.UTF_8.name())
        val loginUrl = "${kickerProperties.host!!}/login?username=$username&password=$password"

        val response = restTemplate.postForEntity(loginUrl, null, String::class.java)
        val setCookie = response.headers.getFirst(HttpHeaders.SET_COOKIE)

        val httpHeaders = HttpHeaders()
        httpHeaders.add(HttpHeaders.COOKIE, setCookie)

        return httpHeaders
    }

}