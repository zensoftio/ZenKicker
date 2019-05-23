package io.zensoft.kicker.service

import io.zensoft.kicker.model.base.BaseModel
import io.zensoft.kicker.repository.BaseRepository
import org.springframework.transaction.annotation.Transactional

/**
 * @author Yauheni Efimenko
 */
@Transactional(readOnly = true)
abstract class DefaultBaseService<T : BaseModel>(
        private val repository: BaseRepository<T>
) : BaseService<T> {

    override fun get(id: Long): T = repository.findById(id).get()

    @Transactional
    override fun add(entity: T): T = repository.save(entity)

}
