package io.zensoft.kicker.domain

import org.springframework.data.domain.Page

/**
 * @author Yauheni Efimenko
 */
open class PageResponse<T>(val totalCount: Long, val list: List<T>) {
    constructor(page: Page<T>) : this(page.totalElements, page.content)
}
