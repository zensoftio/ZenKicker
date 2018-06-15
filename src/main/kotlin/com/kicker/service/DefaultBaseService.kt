package com.kicker.service

import com.kicker.model.base.BaseModel
import com.kicker.repository.BaseRepository
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.Assert

/**
 * @author Yauheni Efimenko
 */
@Transactional
abstract class DefaultBaseService<T : BaseModel, R : BaseRepository<T>>(
        private val repository: R
) : BaseService<T> {

    @Transactional(readOnly = true)
    override fun get(id: Long): T? = repository.findById(id).get()

    override fun save(model: T): T {
        Assert.isNull(model.id, "The new model cannot be with id!")
        return repository.save(model)
    }

}