package com.lfm.ers.service.impl

import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import com.lfm.ers.entity.*
import com.lfm.ers.mapper.ApplyMapper
import com.lfm.ers.mapper.RepairMapper
import com.lfm.ers.service.ApplyService
import com.lfm.ers.utils.ROLE
import com.lfm.ers.vo.ApplyVo
import com.lfm.ers.vo.LayPage
import com.lfm.ers.vo.RepairVo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.*
@Service
open class ApplyServiceImpl : ApplyService {

    @Autowired
    val mapper:ApplyMapper? = null

    @Autowired
    val repairMapper:RepairMapper? = null

    @Transactional
    override fun apply(userId:Int, expectTime: LocalDateTime, faultId: Int, address: String, note: String) :Boolean{
        val apply = Apply()
        apply.userId = userId
        apply.expectTime = expectTime
        apply.fautId = faultId
        apply.createTime = LocalDateTime.now()
        apply.detailAddress = address
        apply.faultDescrip = note
        apply.state = ApplyService.STATE_DEFAULT
        return mapper!!.insertSelective(apply) > 0
    }

    @Transactional
    override fun accept(userId: Int,applyId: Int): Boolean {
        //检查是否存在，与状态
        val a = mapper!!.selectByPrimaryKey(applyId)
        if(a == null || ApplyService.STATE_DEFAULT.compareTo(a.state!!) != 0)
            return false
        //修改状态
        val apply = Apply()
        apply.id = applyId
        apply.state = ApplyService.STATE_ACCPTED
        mapper!!.updateByPrimaryKeySelective(apply)

        val repair = Repair()
        repair.repairmanId = userId
        repair.applyId = applyId
        repair.acceptTime = LocalDateTime.now()
        return repairMapper!!.insertSelective(repair) > 0
    }

    @Transactional
    override fun done(userId: Int, applyId: Int, note: String): Boolean {
        //检查是否拥有
        val repair  = repairMapper!!.selectByAIDAndRID(applyId,userId)
        return if (repair != null ){
            val a = mapper!!.selectByPrimaryKey(applyId)
            if(a?.state == ApplyService.STATE_ACCPTED){
                //更新状态，已维修
                val apply = Apply()
                apply.id = applyId
                apply.state = ApplyService.STATE_REPAIRED
                mapper!!.updateByPrimaryKeySelective(apply)

                //更新维修完毕信息
                val record = Repair()
                record.id = repair.id
                record.repairNote = note
                repairMapper!!.updateByPrimaryKeySelective(record) > 0
            }else
                false
        }else
            false
    }


    @Transactional
    override fun evaluate(userId: Int, applyId: Int, mark: Int, repairTime: LocalDateTime, note: String): Boolean {
        //检查是否拥有
        val apply  = mapper!!.selectByAIDAndUID(applyId,userId)
        val repair = repairMapper!!.selectByApplyId(applyId)
        //更新信息
        return if (apply != null && repair != null && apply?.state == ApplyService.STATE_REPAIRED){
            val record = Repair()
            record.id = repair.id
            record.evaluateMark = mark
            record.evaluateNote = note
            record.evaluateTime = LocalDateTime.now()
            record.repairTime = repairTime
            repairMapper!!.updateByPrimaryKeySelective(record) > 0
        }else false
    }

    @Transactional
    override fun delete(userId: Int, applyId: Int): Boolean {
        val apply = Apply()
        apply.id = applyId
        apply.userId = userId
        val a = mapper!!.selectByAIDAndUID(applyId,userId)
        return if(a != null && ApplyService.STATE_DEFAULT.compareTo(a.state!!) != 0) mapper!!.deleteByPrimaryKey(applyId) > 0
        else false
    }

    @Transactional
    override fun unHandle(applyId: Int): Boolean {
        val apply = Apply()
        apply.id = applyId
        apply.state = ApplyService.STATE_UNHANDLE
        return mapper!!.updateByPrimaryKeySelective(apply) > 0
    }


    @Transactional(readOnly = true)
    override fun applyList(user: SysUser, page: Int, limit: Int): LayPage<ApplyVo> {
        //开始分页
        PageHelper.startPage<ApplyVo>(page, limit)
        val list = if (ROLE.USER.roleName.compareTo(user.roleName!!) == 0) {
                mapper!!.selectDetailByUID(user.id!!)
            } else {
                mapper!!.selectDetailByUID(null)
        }
        val pageInfo = PageInfo<ApplyVo>(list)
        return LayPage("0","",pageInfo.total.toInt(),pageInfo.list)
    }

    @Transactional(readOnly = true)
    override fun repairList(user: SysUser, page: Int, limit: Int): LayPage<RepairVo> {
        PageHelper.startPage<RepairVo>(page, limit)
        val list = if (ROLE.USER.roleName.compareTo(user.roleName!!) == 0) {
            repairMapper!!.selectDetailByUID(user.id!!,ROLE.USER.id)
        } else if(ROLE.REPAIRMAN.roleName.compareTo(user.roleName!!) == 0){
            repairMapper!!.selectDetailByUID(user.id!!,ROLE.REPAIRMAN.id)
        }else{
            repairMapper!!.selectDetailByUID(null,null)
        }
        val pageInfo = PageInfo<RepairVo>(list)
        return LayPage("0","",pageInfo.total.toInt(),pageInfo.list)
    }

    @Transactional(readOnly = true)
    override fun repairDetail(user: SysUser, applyId: Int): RepairVo? {
       return repairMapper!!.selectDetailByAID(applyId,user.id!!,user.roleId!!)
    }

    @Transactional(readOnly = true)
    override fun applyDetail(user: SysUser, applyId: Int): ApplyVo? {
        return mapper!!.selectDetailByAID(applyId,user.id!!,user.roleId!!)
    }
}