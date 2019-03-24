package com.lfm.ers.entity

import java.time.LocalDateTime
import java.util.Date

class Depart {
    var id: Int? = null
    var name: String? = null
    var createTime: LocalDateTime? = null
    var updateTime: LocalDateTime? = null
    var note: String? = null
    var code: String? = null
    var parentId: Int? = null
    var statistic: Byte = 0
}