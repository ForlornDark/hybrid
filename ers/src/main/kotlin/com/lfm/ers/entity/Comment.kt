package com.lfm.ers.entity

import java.time.LocalDateTime
import java.util.Date

open class Comment {
    var id: Int? = null

    var content: String? = null
        set(content) {
            field = content?.trim { it <= ' ' }
        }

    var userId: Int? = null

    var agreeCount: Int = 0

    var state: Int? = null

    var createTime: LocalDateTime? = null

    var questionId: Int? = null
}