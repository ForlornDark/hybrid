package com.lfm.ers.service

import com.github.pagehelper.Page
import com.lfm.ers.entity.*
import com.lfm.ers.vo.ApplyVo
import com.lfm.ers.vo.LayPage
import com.lfm.ers.vo.RepairVo
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
interface ApplyService {

    fun apply(userId:Int,expectTime : LocalDateTime, faultId:Int, address:String, note:String):Boolean

    fun accept(userId: Int,applyId:Int):Boolean

    fun done(userId: Int,applyId: Int,note: String):Boolean

    fun evaluate(userId: Int,applyId: Int,mark:Int,repairTime:LocalDateTime,note: String):Boolean

    fun delete(userId: Int,applyId: Int):Boolean

    fun unHandle(applyId: Int):Boolean

    fun applyList(user: SysUser,page:Int,limit:Int):LayPage<ApplyVo>

    fun repairList(user: SysUser,page:Int,limit:Int):LayPage<RepairVo>

    fun applyDetail(user: SysUser,applyId: Int):ApplyVo?

    fun repairDetail(user: SysUser,applyId: Int):RepairVo?

    companion object {
        //已完成
        val STATE_REPAIRED = 3
        //不予处理
        val STATE_UNHANDLE = 2
        //已接收
        val STATE_ACCPTED = 1
        //未接收
        val STATE_DEFAULT = 0

    }
}