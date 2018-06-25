package com.kicker.service

import com.kicker.domain.model.player.CreatePlayerRequest
import com.kicker.domain.model.player.UpdateDataPlayerRequest
import com.kicker.domain.model.player.UpdatePasswordPlayerRequest
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

        return repository.save(Player.of(request))
    }

    @Transactional
    override fun updateData(playerId: Long, request: UpdateDataPlayerRequest): Player {
        val player = get(playerId)

        if (isExist(request.username!!)) {
            throw DuplicateUsernameException("The player with such username already exist")
        }

        player.username = request.username!!
        player.firstName = request.firstName!!
        player.lastName = request.lastName!!

        return repository.save(player)
    }

    @Transactional
    override fun updatePassword(playerId: Long, request: UpdatePasswordPlayerRequest): Player {
        val player = get(playerId)

        if (!passwordEncoder.matches(request.currentPassword, player.password)) {
            throw PasswordIncorrectException("Password is incorrect")
        }

        player.password = passwordEncoder.encode(request.newPassword)

        return repository.save(player)
    }

    @Transactional
    override fun updateRating(playerId: Long, newRating: Double): Player {
        val player = get(playerId)
        player.currentRating = newRating

        return repository.save(player)
    }


    private fun isExist(username: String): Boolean {
        return getByUsername(username)?.let { true } ?: false
    }

}