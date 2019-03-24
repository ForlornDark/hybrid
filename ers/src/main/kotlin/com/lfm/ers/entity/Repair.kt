package com.lfm.ers.entity

import java.time.LocalDateTime
import java.util.Date

open class Repair {
    var id: Int? = null

    var applyId: Int? = null

    @Transient
    var repairmanId: Int? = null

    var state: Int? = null

    var cost: Int? = null

    var repairNote: String? = null

    var evaluateMark: Int? = null

    var repairTime: LocalDateTime? = null

    var evaluateNote: String? = null

    var evaluateTime: LocalDateTime? = null

    var acceptTime: LocalDateTime? = null
}