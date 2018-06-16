package com.kicker.service

import com.kicker.model.Player
import com.kicker.repository.PlayerRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.Assert

/**
 * @author Yauheni Efimenko
 */
@Service
@Transactional(readOnly = true)
class DefaultPlayerService(
        private val repository: PlayerRepository
) : DefaultBaseService<Player, PlayerRepository>(repository), PlayerService {

    override fun getByUsername(username: String): Player? = repository.findByUsername(username)

    override fun loadUserByUsername(username: String): UserDetails = getByUsername(username)
            ?: throw UsernameNotFoundException("User with username $username not found")

    @Transactional
    override fun update(player: Player): Player {
        Assert.notNull(player.id, "The player cannot be without id!")
        return repository.save(player)
    }

}