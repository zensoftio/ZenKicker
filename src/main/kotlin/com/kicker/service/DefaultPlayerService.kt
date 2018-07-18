package com.kicker.service

import com.kicker.domain.model.player.CreatePlayerRequest
import com.kicker.domain.model.player.UpdatePlayerUsernameRequest
import com.kicker.domain.model.player.UpdatePlayerPasswordRequest
import com.kicker.exception.service.DuplicateUsernameException
import com.kicker.exception.service.PasswordIncorrectException
import com.kicker.model.Player
import com.kicker.repository.PlayerRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author Yauheni Efimenko
 */
@Service
@Transactional(readOnly = true)
class DefaultPlayerService(
        private val repository: PlayerRepository,
        private val passwordEncoder: PasswordEncoder
) : DefaultBaseService<Player, PlayerRepository>(repository), PlayerService {

    override fun getByUsername(username: String): Player? = repository.findByUsername(username)

    override fun loadUserByUsername(username: String): UserDetails = getByUsername(username)
            ?: throw UsernameNotFoundException("User with username $username not found")

    @Transactional
    override fun create(request: CreatePlayerRequest): Player {
        if (isExist(request.username!!)) {
            throw DuplicateUsernameException("The player with such username already exist")
        }

        request.password = passwordEncoder.encode(request.password)

        return super.save(Player(request.username!!, request.password!!))
    }

    @Transactional
    override fun updateData(playerId: Long, usernameRequest: UpdatePlayerUsernameRequest): Player {
        val player = get(playerId)

        if (isExist(usernameRequest.username!!)) {
            throw DuplicateUsernameException("The player with such username already exist")
        }

        player.username = usernameRequest.username!!

        return super.save(player)
    }

    @Transactional
    override fun updatePassword(playerId: Long, passwordRequest: UpdatePlayerPasswordRequest): Player {
        val player = get(playerId)

        if (!passwordEncoder.matches(passwordRequest.currentPassword, player.password)) {
            throw PasswordIncorrectException("Password is incorrect")
        }

        player.password = passwordEncoder.encode(passwordRequest.newPassword)

        return super.save(player)
    }

    @Transactional
    override fun updateActivity(playerId: Long, active: Boolean): Player {
        val player = get(playerId)
        player.active = active

        return super.save(player)
    }

    private fun isExist(username: String): Boolean {
        return getByUsername(username)?.let { true } ?: false
    }

}