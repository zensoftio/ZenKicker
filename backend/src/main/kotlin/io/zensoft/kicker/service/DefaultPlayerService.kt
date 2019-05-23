package io.zensoft.kicker.service

import io.zensoft.kicker.component.IconManager
import io.zensoft.kicker.domain.PageRequest
import io.zensoft.kicker.domain.model.player.CreatePlayerRequest
import io.zensoft.kicker.domain.model.player.UpdatePlayerEmailRequest
import io.zensoft.kicker.domain.model.player.UpdatePlayerFullNameRequest
import io.zensoft.kicker.domain.model.player.UpdatePlayerPasswordRequest
import io.zensoft.kicker.exception.DuplicateEmailException
import io.zensoft.kicker.exception.PasswordIncorrectException
import io.zensoft.kicker.model.Player
import io.zensoft.kicker.repository.PlayerRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.domain.Page
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
) : DefaultBaseService<Player>(repository), PlayerService {

    override fun getAll(): List<Player> = repository.findAll()

    override fun getAll(pageRequest: PageRequest): Page<Player> = repository.findAll(pageRequest)

    override fun findByEmail(email: String): Player? = repository.findByEmail(email)

    override fun searchByKeyword(keyword: String): List<Player> = repository.searchByKeyword(keyword)

    override fun loadUserByUsername(email: String): UserDetails = findByEmail(email)
            ?: throw UsernameNotFoundException("User with email $email not found")

    @CacheEvict("relations", "relationsDashboard", "playersDashboard", allEntries = true)
    @Transactional
    override fun create(request: CreatePlayerRequest): Player {
        if (isExist(request.email!!)) {
            throw DuplicateEmailException("The player with such email already exist")
        }

        request.password = passwordEncoder.encode(request.password)

        val player = repository.save(Player(request.email!!, request.fullName!!, request.password!!))
        eventPublisher.publishEvent(player)
        return player
    }

    @CacheEvict("relations", "relationsDashboard", "playersDashboard", allEntries = true)
    @Transactional
    override fun updateEmail(playerId: Long, request: UpdatePlayerEmailRequest): Player {
        if (isExist(request.email!!)) {
            throw DuplicateEmailException("The player with such email already exist")
        }

        val player = get(playerId)
        player.email = request.email!!

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

    private fun isExist(email: String): Boolean = findByEmail(email)?.let { true } ?: false

}