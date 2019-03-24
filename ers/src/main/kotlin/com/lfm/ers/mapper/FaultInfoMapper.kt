package com.lfm.ers.mapper

import com.lfm.ers.entity.EquipType
import com.lfm.ers.entity.FaultInfo
import org.springframework.stereotype.Repository

@Repository
interface FaultInfoMapper {
    fun deleteByPrimaryKey(id: Int?): Int

    fun insert(record: FaultInfo): Int

    fun insertSelective(record: FaultInfo): Int

    fun selectByPrimaryKey(id: Int?): FaultInfo

    fun updateByPrimaryKeySelective(record: FaultInfo): Int

    fun updateByPrimaryKey(record: FaultInfo): Int

    fun selectByEquipId(equipId:Int):List<FaultInfo>
}