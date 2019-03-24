package com.lfm.ers.service.impl

import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import com.lfm.ers.entity.Agree
import com.lfm.ers.entity.Comment
import com.lfm.ers.entity.Question
import com.lfm.ers.entity.SysUser
import com.lfm.ers.mapper.AgreeMapper
import com.lfm.ers.mapper.CommentMapper
import com.lfm.ers.mapper.QuestionMapper
import com.lfm.ers.service.QuestionService
import com.lfm.ers.utils.FileStore
import com.lfm.ers.vo.CommentDetail
import com.lfm.ers.vo.LayPage
import com.lfm.ers.vo.QuestionDetail
import com.lfm.ers.vo.QuestionItem
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime
import java.util.*

@Service
open class QuestionServiceImpl :QuestionService{

    @Autowired
    val mapper:QuestionMapper? = null;

    @Autowired
    val commentMapper:CommentMapper? = null

    @Autowired
    val agreeMapper:AgreeMapper? = null

    override fun picUpload(file: MultipartFile): String? {
        return FileStore.upload(file.originalFilename,file.inputStream,true)
    }

    override fun saveQuestion(questionId: Int?,user: SysUser, title: String, content: String): Boolean {
        var re = false
        val userId = user?.id
        userId?:return false
        val question = Question()
        question.title = title
        question.content = content
        if(questionId == null){
            question.userId = userId
            question.commentCount = 0
            question.createTime = LocalDateTime.now()
            val i = mapper?.insertSelective(question);
            if (i != null && i > 0) re = true
        }else{
            val qu = mapper?.selectByIdAndUid(questionId,userId)
            qu?:return false
            question.id = questionId
            val i =mapper?.updateByPrimaryKeySelective(question)
            if (i != null && i > 0) re = true
        }
        return re;
    }

    override fun getItems(page: Int, limit: Int,userId:Int?): LayPage<QuestionItem> {
        PageHelper.startPage<QuestionItem>(page,limit)
        val list = mapper?.questionItemList(userId)
        val pageInfo = PageInfo(list)
        return LayPage<QuestionItem>("200","",pageInfo.total.toInt(),pageInfo.list);
    }

    override fun getQuestion(id: Int): Question? {
        return mapper?.selectByPrimaryKey(id)
    }

    override fun getQuestion(id: Int, userId: Int): Question? {
        return mapper?.selectByIdAndUid(id,userId)
    }

    override fun getDetail(id: Int): QuestionDetail?{
        return mapper?.detailById(id)
    }

    @Transactional
    override fun comment(user: SysUser, id: Int, content: String): Boolean {
        val question =  mapper?.selectByPrimaryKey(id)
        question?:return false
        val comment = Comment()
        comment.agreeCount = 0;
        comment.content = content
        comment.userId = user.id
        comment.createTime = LocalDateTime.now()
        comment.state = 0
        comment.questionId = id
        commentMapper?.insertSelective(comment)
        val question2 = Question()
        question2.id = id
        question2.commentCount =1 + (question.commentCount?:0)
        mapper?.updateByPrimaryKeySelective(question2)
        return true
    }

    override fun getCommentList(userId: Int,id: Int, page: Int, limit: Int): LayPage<CommentDetail> {
        PageHelper.startPage<CommentDetail>(page,limit)
        val list = commentMapper!!.selectDetailCommentByQuestionId(id,userId);
        val pageInfo = PageInfo(list)
        return LayPage("0","",pageInfo.total.toInt(),pageInfo.list)
    }

    @Transactional
    override fun agree(userId: Int, commentId: Int, questionId: Int): Boolean {
        val c = commentMapper?.selectByQidAndId(commentId,questionId)
        c?:return false
        val agree = agreeMapper?.selectByCommentIdAndUserId(commentId,userId)
        if (agree == null){
            val c2 = Comment()
            c2.id = c.id
            c2.agreeCount = c.agreeCount + 1;
            commentMapper?.updateByPrimaryKeySelective(c2)
            val a = Agree()
            a.commentId = commentId
            a.userId = userId
            agreeMapper?.insert(a)
            return true
        }
        return false
    }
}