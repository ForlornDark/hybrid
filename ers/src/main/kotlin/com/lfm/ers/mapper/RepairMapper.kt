package com.lfm.ers.mapper

import com.lfm.ers.entity.Repair
import com.lfm.ers.vo.RepairVo
import com.lfm.ers.vo.Value
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import java.util.*

@Repository
interface RepairMapper {
    fun deleteByPrimaryKey(id: Int?): Int

    fun insert(record: Repair): Int

    fun insertSelective(record: Repair): Int

    fun selectByPrimaryKey(id: Int?): Repair

    fun updateByPrimaryKeySelective(record: Repair): Int

    fun updateByPrimaryKey(record: Repair): Int

    fun selectByApplyId(@Param("applyId") applyId:Int):Repair?

    fun selectByAIDAndRID(@Param(value = "applyId") applyId: Int,@Param(value = "repairmanId") repairmanId:Int ):Repair?

    fun selectDetailByUID(@Param(value="userId") userId:Int?,@Param(value="type") type:Int?):List<RepairVo>

    fun selectDetailByRoleRepair(@Param(value="repairmanId") repairmanId: Int):List<RepairVo>

    fun selectDetailByRoleUser(@Param(value="userId") userId: Int):List<RepairVo>

    fun selectDetailByAID(@Param(value = "id") id: Int,@Param(value = "userId")userId: Int,@Param(value = "type")type: Int):RepairVo?


    fun countByRID(@Param(value = "start") start:String):List<Value<String,Long>>

    fun avgMarkOrder():List<Value<String, BigDecimal>>
}