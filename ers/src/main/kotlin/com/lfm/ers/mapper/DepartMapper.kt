package com.lfm.ers.mapper

import com.lfm.ers.entity.Depart
import com.lfm.ers.vo.Value
import org.springframework.stereotype.Repository

@Repository
interface DepartMapper {
    fun deleteByPrimaryKey(id: Int?): Int

    fun insert(record: Depart): Int

    fun insertSelective(record: Depart): Int

    fun selectByPrimaryKey(id: Int?): Depart

    fun updateByPrimaryKeySelective(record: Depart): Int

    fun updateByPrimaryKey(record: Depart): Int
    /**
     * 获取所有结构
     */
    fun selectAll():List<Depart>

    fun countDepartUser():List<Value<String,Long>>
}