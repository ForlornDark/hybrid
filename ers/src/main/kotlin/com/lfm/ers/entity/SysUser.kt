package com.lfm.ers.entity

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime
import java.util.Date

open class SysUser {
    var id: Int? = null

    var name: String? = null

    var sex: Boolean? = null

    var phone: String? = null

    var password: String? = null

    var salt: String? = null

    var locked: Boolean? = null

    var createDate: LocalDateTime? = null

    var note: String? = null

    var roleId: Int? = null

    var departId: Int? = null

    var mail: String? = null

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    var birthday: LocalDateTime? = null

    var photo: String? = null

    var roleName :String? = null

    var fullName:String?=null

}