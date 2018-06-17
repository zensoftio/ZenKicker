package com.kicker.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.kicker.annotation.Contains
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import javax.validation.constraints.Max
import javax.validation.constraints.Min

/**
 * @author Yauheni Efimenko
 */
@Contains(value = "sortBy", map = "maySortBy", message = "Collection is not contains value for sorting")
open class PageRequest(
        @field:Min(value = 0) private var offset: Int = 0,
        @field:Min(value = 1) @field:Max(100) private var limit: Int = 10,
        var sortDirection: Sort.Direction = Sort.Direction.ASC,
        var sortBy: String = "id",
        @field:JsonIgnore
        val maySortBy: Map<String, String> = mapOf("id" to "id")
) : Pageable {

    @JsonIgnore
    override fun getPageNumber(): Int = (offset / limit + 1)

    override fun hasPrevious(): Boolean = offset > 0

    @JsonIgnore
    override fun getSort(): Sort = Sort(sortDirection, maySortBy[sortBy])

    override fun next(): Pageable = PageRequest(offset + limit, limit, sortDirection, sortBy, maySortBy)

    @JsonIgnore
    override fun getPageSize(): Int = limit

    override fun getOffset(): Long = offset.toLong()

    override fun first(): Pageable = PageRequest(0, limit, sortDirection, sortBy, maySortBy)

    private fun previous(): Pageable {
        if (hasPrevious()) {
            var newOffset = this.offset - limit
            if (newOffset < 0) newOffset = 0
            return PageRequest(newOffset, limit, sortDirection, sortBy, maySortBy)
        }
        return first()
    }

    override fun previousOrFirst(): Pageable = previous()

}