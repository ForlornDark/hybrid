package com.lfm.ers.entity

import java.time.LocalDateTime
import java.util.Date

open class Question {
    var id: Int? = null

    var title: String? = null
        set(title) {
            field = title?.trim { it <= ' ' }
        }

    var createTime: LocalDateTime? = null

    var content: String? = null
        set(content) {
            field = content?.trim { it <= ' ' }
        }

    var state: Int? = null

    var commentCount: Int? = null

    var userId: Int? = null

    var type: Int = 0
}