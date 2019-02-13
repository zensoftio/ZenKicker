package io.zensoft.kicker.service

import io.zensoft.kicker.component.IconManager
import io.zensoft.kicker.domain.PageRequest
import io.zensoft.kicker.domain.model.player.CreatePlayerRequest
import io.zensoft.kicker.domain.model.player.UpdatePlayerPasswordRequest
import io.zensoft.kicker.domain.model.player.UpdatePlayerUsernameRequest
import io.zensoft.kicker.exception.service.DuplicateUsernameException
import io.zensoft.kicker.exception.service.NotFoundPlayerException
import io.zensoft.kicker.exception.service.PasswordIncorrectException
import io.zensoft.kicker.model.Player
import io.zensoft.kicker.repository.PlayerRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.util.*

/**
 * @author Yauheni Efimenko
 */
@Service
@Transactional(readOnly = true)
class DefaultPlayerService(
        private val repository: PlayerRepository,
        private val passwordEncoder: PasswordEncoder,
        private val iconManager: IconManager
) : DefaultBaseService<Player, PlayerRepository>(repository), PlayerService {

    override fun get(id: Long): Player {
        return try {
            super.get(id)
        } catch (e: NoSuchElementException) {
            throw NotFoundPlayerException("Player with such id: $id not found")
        }
    }

    override fun getByUsername(username: String): Player? = repository.findByUsername(username)

    override fun searchByKeyword(keyword: String): List<Player> = repository.searchByKeyword(keyword)

    @Cacheable("players")
    override fun getAll(pageRequest: PageRequest): Page<Player> = super.getAll(pageRequest)

    override fun loadUserByUsername(username: String): UserDetails = getByUsername(username)
            ?: throw UsernameNotFoundException("User with username $username not found")

    @CacheEvict("players", "relations", "relationsDashboard", "playersDashboard", "statsPlayers",
            "statsActivePlayers", "games", "playerGames", allEntries = true)
    @Transactional
    override fun create(request: CreatePlayerRequest): Player {
        if (isExist(request.username!!)) {
            throw DuplicateUsernameException("The player with such username already exist")
        }

        request.password = passwordEncoder.encode(request.password)

        return repository.save(Player(request.username!!, request.password!!))
    }

    @CacheEvict("players", "relations", "relationsDashboard", "playersDashboard", "statsPlayers",
            "statsActivePlayers", "games", "playerGames", allEntries = true)
    @Transactional
    override fun updateUsername(playerId: Long, request: UpdatePlayerUsernameRequest): Player {
        if (isExist(request.username!!)) {
            throw DuplicateUsernameException("The player with such username already exist")
        }

        val player = get(playerId)
        player.username = request.username!!

        return repository.save(player)
    }

    @Transactional
    override fun updatePassword(playerId: Long, request: UpdatePlayerPasswordRequest): Player {
        val player = get(playerId)

        if (!passwordEncoder.matches(request.currentPassword, player.password)) {
            throw PasswordIncorrectException("Password is incorrect")
        }

        player.password = passwordEncoder.encode(request.newPassword)

        return repository.save(player)
    }

    @CacheEvict("players", "relations", "relationsDashboard", "playersDashboard", "statsPlayers",
            "statsActivePlayers", "games", "playerGames", allEntries = true)
    @Transactional
    override fun updateIcon(playerId: Long, icon: MultipartFile): Player {
        val player = get(playerId)

        player.iconPath?.let { iconManager.delete(it) }
        val iconPath = iconManager.upload(icon)

        player.iconPath = iconPath
        return repository.save(player)
    }

    private fun isExist(username: String): Boolean = getByUsername(username)?.let { true } ?: false

}