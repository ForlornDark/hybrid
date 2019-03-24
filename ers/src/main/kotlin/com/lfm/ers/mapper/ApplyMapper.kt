package com.lfm.ers.mapper

import com.lfm.ers.entity.Apply
import com.lfm.ers.vo.ApplyVo
import com.lfm.ers.vo.Value
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Repository

@Repository
interface ApplyMapper {
    fun deleteByPrimaryKey(id: Int?): Int

    fun insert(record: Apply): Int

    fun insertSelective(record: Apply): Int

    fun selectByPrimaryKey(id: Int?): Apply?

    fun updateByPrimaryKeySelective(record: Apply): Int

    fun updateByPrimaryKey(record: Apply): Int

    fun selectByAIDAndUID(@Param("id") id: Int,@Param("userId") userId:Int):Apply?

    fun selectDetailByUID(@Param("userId") userId: Int?):List<ApplyVo>

    fun selectDetailByAID(@Param(value = "id") id: Int,@Param(value = "userId") userId: Int,@Param(value = "type")type:Int):ApplyVo?

    fun countByState():List<Value<String,Long>>

    fun countDepartApply():List<Value<String,Long>>

    fun countEquipTypeApply():List<Value<String,Long>>
}