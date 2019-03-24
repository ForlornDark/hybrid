package com.lfm.ers.mapper

import com.lfm.ers.entity.Comment
import com.lfm.ers.vo.CommentDetail
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Repository

@Repository
interface CommentMapper {
    fun deleteByPrimaryKey(id: Int?): Int

    fun insert(record: Comment): Int

    fun insertSelective(record: Comment): Int

    fun selectByPrimaryKey(id: Int?): Comment

    fun updateByPrimaryKeySelective(record: Comment): Int

    fun updateByPrimaryKey(record: Comment): Int

    fun selectDetailCommentByQuestionId(@Param("questionId") questionId:Int,@Param("userId") userId:Int):List<CommentDetail>

    fun selectByQidAndId(@Param("id") id: Int,@Param("questionId") questionId: Int):Comment?
}