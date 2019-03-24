package com.lfm.ers.service

import com.lfm.ers.entity.Question
import com.lfm.ers.entity.SysUser
import com.lfm.ers.vo.CommentDetail
import com.lfm.ers.vo.LayPage
import com.lfm.ers.vo.QuestionDetail
import com.lfm.ers.vo.QuestionItem
import org.springframework.web.multipart.MultipartFile

interface QuestionService {
    fun picUpload(file:MultipartFile):String?;

    fun saveQuestion(questionId: Int?,user: SysUser,title:String,content:String):Boolean;


    fun getItems(page:Int,limit:Int,userId:Int?):LayPage<QuestionItem>;

    fun getQuestion(id: Int):Question?

    fun getQuestion(id: Int,userId: Int):Question?

    fun getDetail(id:Int):QuestionDetail?

    fun comment(user: SysUser,id:Int,content: String):Boolean

    fun getCommentList(userId: Int,id: Int,page: Int,limit: Int):LayPage<CommentDetail>?

    fun agree(userId: Int,commentId:Int,questionId:Int):Boolean;
}