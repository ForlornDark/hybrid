package com.lfm.ers.mapper

import com.lfm.ers.entity.Question
import com.lfm.ers.vo.QuestionDetail
import com.lfm.ers.vo.QuestionItem
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Repository

@Repository
interface QuestionMapper {
    fun deleteByPrimaryKey(id: Int?): Int

    fun insert(record: Question): Int

    fun insertSelective(record: Question): Int

    fun selectByPrimaryKey(id: Int?): Question?

    fun updateByPrimaryKeySelective(record: Question): Int

    fun updateByPrimaryKey(record: Question): Int

    fun questionItemList(@Param("userId") userId:Int?):List<QuestionItem>

    fun detailById(id:Int):QuestionDetail

    fun selectByIdAndUid(@Param("id") id: Int,@Param("userId") userId: Int):Question?
}