package com.lfm.ers.controller

import com.lfm.ers.service.QuestionService
import com.lfm.ers.vo.CommentDetail
import com.lfm.ers.vo.Info
import com.lfm.ers.vo.LayPage
import com.lfm.ers.vo.QuestionItem
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("/question")
class QuestionController :BaseController(){

    @Autowired
    private var service:QuestionService? = null

    @GetMapping("/edit")
    fun edit(@RequestParam(name = "question", required = false) question: Int?):ModelAndView{
        val view = ModelAndView("question_edit")
        question?.let {
            val que = service?.getQuestion(it)
            view.addObject("item",que)
        }
        return  view
    }

    @GetMapping("/index")
    fun index():String{
        return  "question_index"
    }

    @GetMapping("/mine_index")
    fun mineIndex():String{
        return  "question_mine"
    }

    @PostMapping("/save")
    @ResponseBody
    fun save(question: Int?,title:String,content:String):Info<String>{
        val user = getUser()
        val re = service?.saveQuestion(question,user,title,content)
        return  if (re!=null && re){
            Info(true,"保存成功")
        }else
            Info(false,"保存失败")
    }

    @PostMapping(value = ["/picUpload"])
    @ResponseBody
    fun photo(photo: MultipartFile, request: HttpServletRequest): Info<String> {
        var info = Info<String>()
        var name:String? = null
        if (photo.contentType.compareTo("image/jpeg") == 0 || photo.contentType.compareTo("image/png") == 0){
            name = service?.picUpload(photo)
            name?.let {info = Info(true,"",it) }
        }
        return info
    }

    @GetMapping(value = ["/items"])
    @ResponseBody
    fun items(@RequestParam(required = false ,defaultValue = "1") page:Int,
              @RequestParam(required = false ,defaultValue = "15")limit:Int):LayPage<QuestionItem>?{
        return service?.getItems(page,limit,null)
    }

    @GetMapping(value = ["/detail_index/{id}"])
    fun detailIndex(@PathVariable(name = "id",required = true) id:String):ModelAndView{
        val view = ModelAndView("question_detail")
        val detail = service?.getDetail(id.toInt())
        detail?.let {
            it.photo = fixPhoto(it.photo)
        }
        view.addObject("item",detail)
       return view
    }

    @PostMapping("/comment")
    @ResponseBody
    fun comment(question:Int,content: String):Info<String>{
        var re = service?.comment(getUser(),question,content)
        re = re?:false
        return if (re)
            Info(true)
        else
            Info(false,"回复失败")
    }

    @GetMapping("/comment_list")
    @ResponseBody
    fun commentList(question: Int,@RequestParam(required = false ,defaultValue = "1") page:Int,
                    @RequestParam(required = false ,defaultValue = "10")limit:Int):LayPage<CommentDetail>?{
        val userId = getUser().id
        userId?:return null
        return service?.getCommentList(userId,question,page,limit);
    }

    private fun fixPhoto(photo:String?):String{
        return if (photo == null){
             "/img/hole.png"
        }else{
            "/source/$photo"
        }
    }

    @PostMapping("/agree")
    @ResponseBody
    fun agree(comment:Int,question: Int):Info<String>{
        val userId = getUser().id
        var re = false
        userId?.let {
            re = (service?.agree(it,comment,question))?:false
        }
        return if (re) Info(true) else Info()
    }

    @GetMapping("/mine")
    @ResponseBody
    fun mine(@RequestParam(required = false ,defaultValue = "1") page:Int,
             @RequestParam(required = false ,defaultValue = "15")limit:Int):LayPage<QuestionItem>?{
        return service?.getItems(page,limit,getUser()?.id)
    }
}