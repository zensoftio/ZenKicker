package com.kicker.domain

import org.springframework.data.domain.Page

/**
 * @author Yauheni Efimenko
 */
data class PageResponse<T>(var totalCount: Long, var list: List<T>) {
    constructor(page: Page<T>) : this(page.totalElements, page.content)
}
