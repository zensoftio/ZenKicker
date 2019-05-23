package io.zensoft.kicker.domain

import io.zensoft.kicker.annotation.Contains
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import javax.validation.constraints.Max
import javax.validation.constraints.Min

/**
 * @author Yauheni Efimenko
 */
@Contains(value = "sortBy", map = "mapSortBy", message = "Collection is not contains value for sorting")
open class PageRequest(
        @field:Min(value = 0) var offset: Int = 0,
        @field:Min(value = 1) @field:Max(100) var limit: Int = 10,
        var sortDirection: Sort.Direction = Sort.Direction.ASC,
        var sortBy: String = ID_FIELD.first,
        private val mapSortBy: Map<String, String> = mapOf(ID_FIELD)
) : Pageable {

    companion object {
        val ID_FIELD = "id" to "id"
    }


    override fun getPageNumber(): Int = (offset / limit + 1)

    override fun hasPrevious(): Boolean = offset > 0

    override fun getSort(): Sort {
        if (sortBy == ID_FIELD.first) {
            return Sort(sortDirection, ID_FIELD.second)
        }
        return Sort(sortDirection, mapSortBy[sortBy], ID_FIELD.second)
    }

    override fun next(): Pageable = PageRequest(offset + limit, limit, sortDirection, sortBy, mapSortBy)

    override fun getPageSize(): Int = limit

    override fun getOffset(): Long = offset.toLong()

    override fun first(): Pageable = PageRequest(0, limit, sortDirection, sortBy, mapSortBy)

    private fun previous(): Pageable {
        if (hasPrevious()) {
            var newOffset = this.offset - limit
            if (newOffset < 0) newOffset = 0
            return PageRequest(newOffset, limit, sortDirection, sortBy, mapSortBy)
        }
        return first()
    }

    override fun previousOrFirst(): Pageable = previous()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PageRequest

        if (offset != other.offset) return false
        if (limit != other.limit) return false
        if (sortDirection != other.sortDirection) return false
        if (sortBy != other.sortBy) return false
        if (mapSortBy != other.mapSortBy) return false

        return true
    }

    override fun hashCode(): Int {
        var result = offset
        result = 31 * result + limit
        result = 31 * result + sortDirection.hashCode()
        result = 31 * result + sortBy.hashCode()
        result = 31 * result + mapSortBy.hashCode()
        return result
    }

}
