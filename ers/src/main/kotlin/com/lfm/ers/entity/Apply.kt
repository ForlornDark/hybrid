package com.lfm.ers.entity

import java.time.LocalDateTime
import java.util.*

open class Apply  {
    var id: Int? = null

    @Transient
    var userId: Int? = null

    var fautId: Int? = null

    var detailAddress: String? = null

    var faultDescrip: String? = null

    var createTime: LocalDateTime? = null

    var expectTime: LocalDateTime? = null

    var state: Int? = null
}