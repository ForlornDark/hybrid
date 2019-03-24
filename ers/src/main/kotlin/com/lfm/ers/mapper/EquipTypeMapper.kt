package com.lfm.ers.mapper

import com.lfm.ers.entity.EquipType
import org.springframework.stereotype.Repository

@Repository
interface EquipTypeMapper {
    fun deleteByPrimaryKey(id: Int?): Int

    fun insert(record: EquipType): Int

    fun insertSelective(record: EquipType): Int

    fun selectByPrimaryKey(id: Int?): EquipType

    fun updateByPrimaryKeySelective(record: EquipType): Int

    fun updateByPrimaryKey(record: EquipType): Int

    fun selectList():List<EquipType>
}