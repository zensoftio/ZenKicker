package com.telegram.bot.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.telegram.bot.config.properties.KickerProperties
import com.telegram.bot.domain.PageRequest
import com.telegram.bot.domain.PlayerDto
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.net.URLEncoder

@Service
class DefaultApiService(
        private val kickerProperties: KickerProperties
) : ApiService {

    private val restTemplate: RestTemplate = RestTemplate()


    override fun getPlayers(pageRequest: PageRequest): List<PlayerDto> {
        val uri = "${kickerProperties.host}/api/players/?offset=${pageRequest.offset}"
        val httpEntity = HttpEntity(null, login())
        val body = getBody(uri, httpEntity, HttpMethod.GET)
        val root = jacksonObjectMapper().readTree(body)
        val players = root.get("list").toMutableList()
        return players.map {
            PlayerDto(
                    it.path("id").asLong(),
                    it.path("username").asText()
            )
        }
    }

    private fun <T> getBody(uri: String, httpEntity: HttpEntity<T>, httpMethod: HttpMethod): String =
            restTemplate.exchange(uri, httpMethod, httpEntity, String::class.java).body!!

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