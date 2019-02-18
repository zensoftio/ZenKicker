package io.zensoft.kicker.service

import io.zensoft.kicker.component.IconManager
import io.zensoft.kicker.domain.model.player.CreatePlayerRequest
import io.zensoft.kicker.domain.model.player.UpdatePlayerFullNameRequest
import io.zensoft.kicker.domain.model.player.UpdatePlayerLoginRequest
import io.zensoft.kicker.domain.model.player.UpdatePlayerPasswordRequest
import io.zensoft.kicker.exception.DuplicateLoginException
import io.zensoft.kicker.exception.PasswordIncorrectException
import io.zensoft.kicker.model.Player
import io.zensoft.kicker.repository.PlayerRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.context.ApplicationEventPublisher
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

/**
 * @author Yauheni Efimenko
 */
@Service
@Transactional(readOnly = true)
class DefaultPlayerService(
        private val repository: PlayerRepository,
        private val passwordEncoder: PasswordEncoder,
        private val iconManager: IconManager,
        private val eventPublisher: ApplicationEventPublisher
) : DefaultBaseService<Player, PlayerRepository>(repository), PlayerService {

    override fun findByLogin(login: String): Player? = repository.findByLogin(login)

    override fun searchByKeyword(keyword: String): List<Player> = repository.searchByKeyword(keyword)

    override fun loadUserByUsername(login: String): UserDetails = findByLogin(login)
            ?: throw UsernameNotFoundException("User with login $login not found")

    @CacheEvict("relations", "relationsDashboard", "playersDashboard", allEntries = true)
    @Transactional
    override fun create(request: CreatePlayerRequest): Player {
        if (isExist(request.login!!)) {
            throw DuplicateLoginException("The player with such login already exist")
        }

        request.password = passwordEncoder.encode(request.password)

        val player = repository.save(Player(request.login!!, request.fullName!!, request.password!!))
        eventPublisher.publishEvent(player)
        return player
    }

    @CacheEvict("relations", "relationsDashboard", "playersDashboard", allEntries = true)
    @Transactional
    override fun updateLogin(playerId: Long, request: UpdatePlayerLoginRequest): Player {
        if (isExist(request.login!!)) {
            throw DuplicateLoginException("The player with such login already exist")
        }

        val player = get(playerId)
        player.login = request.login!!

        return repository.save(player)
    }

    @CacheEvict("relations", "relationsDashboard", "playersDashboard", allEntries = true)
    @Transactional
    override fun updateFullName(playerId: Long, request: UpdatePlayerFullNameRequest): Player {
        val player = get(playerId)
        player.fullName = request.fullName!!

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

    @CacheEvict("relations", "relationsDashboard", "playersDashboard", allEntries = true)
    @Transactional
    override fun updateIcon(playerId: Long, icon: MultipartFile): Player {
        val player = get(playerId)

        player.iconPath?.let { iconManager.delete(it) }
        player.iconPath = iconManager.upload(icon)

        return repository.save(player)
    }

    private fun isExist(login: String): Boolean = findByLogin(login)?.let { true } ?: false

}