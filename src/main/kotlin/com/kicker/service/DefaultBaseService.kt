package com.kicker.service

import com.kicker.model.base.BaseModel
import com.kicker.repository.BaseRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.transaction.annotation.Transactional

/**
 * @author Yauheni Efimenko
 */
@Transactional(readOnly = true)
abstract class DefaultBaseService<T : BaseModel, R : BaseRepository<T>>(
        private val repository: R
) : BaseService<T> {

    override fun get(id: Long): T = repository.findById(id).get()

    override fun getAll(pageable: Pageable): Page<T> = repository.findAll(pageable)

}