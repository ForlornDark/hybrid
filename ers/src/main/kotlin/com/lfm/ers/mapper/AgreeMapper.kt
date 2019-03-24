package com.lfm.ers.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.lfm.ers.entity.Agree
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Repository

@Repository
interface AgreeMapper :BaseMapper<Agree>{
    fun selectByCommentIdAndUserId(@Param("commentId") commentId:Int,@Param("userId") userId:Int):Agree?
}