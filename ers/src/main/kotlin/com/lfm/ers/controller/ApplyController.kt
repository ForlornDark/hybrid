package com.lfm.ers.controller

import com.lfm.ers.entity.SysUserDetail
import com.lfm.ers.service.ApplyService
import com.lfm.ers.service.SysUserService
import com.lfm.ers.vo.ApplyVo
import com.lfm.ers.vo.Info
import com.lfm.ers.vo.LayPage
import com.lfm.ers.vo.RepairVo
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping(value= ["/apply"])
@Api(value = "ApplyController")
class ApplyController : BaseController(){

    @Autowired
    private val userService:SysUserService ? = null

    @Autowired
    val service:ApplyService ?= null

    @GetMapping(value = ["/index"])
    fun index(request: HttpServletRequest):ModelAndView{
        return view("apply_index_user")
    }

    @GetMapping(value = ["/index2"])
    fun index2():ModelAndView{
        return view("repair_index")
    }

    @GetMapping(value = ["/edit"])
    fun edit(request: HttpServletRequest):ModelAndView{
        val user = getUser()
        val id = user.id
        var userDetail:SysUserDetail?=null
        if(id != null){
            userDetail = userService!!.getUserById(id)
        }
        val view = ModelAndView("apply_edit")
        view.addObject("info",userDetail)
        return view
    }

    @PostMapping(value = ["/save"])
    @ResponseBody
    fun save(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") expectTime: LocalDateTime, faultId: Int, address: String, note: String, request: HttpServletRequest): Info<String> {
        val user = getUser()
        return if (service!!.apply(user.id!!, expectTime, faultId, address, note))
            Info(true, "申请成功", "")
        else Info(false, "申请失败")
    }

    @PostMapping(value= ["/accept"])
    @ResponseBody
    fun accept(applyId:Int ,request: HttpServletRequest):Info<String>{
        val user = getUser()
        return if (service!!.accept(user.id!!,applyId) ) Info(true,"接受报修任务成功","")
        else Info(false,"接受任务失败")
    }

    @PostMapping(value = ["/done"])
    @ResponseBody
    fun done(applyId: Int,note:String,request: HttpServletRequest):Info<String>{
        val user = getUser()
        return if ( service!!.done(user.id!!,applyId,note)) Info(true,"已提交","")
        else Info(false,"提交失败")
    }


    @PostMapping(value = ["/evaluate"])
    @ResponseBody
    fun done(mark: Int, applyId: Int, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") repairTime: LocalDateTime,
             @RequestParam(required = false, defaultValue = "") note: String, request: HttpServletRequest): Info<String> {
        val user = getUser()
        return if (service!!.evaluate(user.id!!, applyId, mark, repairTime, note)) Info(true, "提交成功", "")
        else Info(false, "提交失败")
    }

    @PostMapping(value = ["/delete"])
    @ResponseBody
    fun delete(applyId: Int,request: HttpServletRequest):Info<String>{
        val user = getUser()
        return if ( service!!.delete(user.id!!,applyId)) Info(true,"申请撤销成功","")
        else Info(false,"申请撤销失败")
    }

    @PostMapping(value = ["/unHandle"])
    @ResponseBody
    @PreAuthorize("hasRole('admin')")
    fun unHandle(applyId: Int):Info<String>{
        return if ( service!!.unHandle(applyId)) Info(true,"申请以标记为不予处理","")
        else Info(false,"申请标记失败")
    }

    @PostMapping(value= ["/applyList"])
    @ResponseBody
    fun applyList(@RequestParam(name = "page",required = false ,defaultValue = "1")  page:Int,
                  @RequestParam(name = "limit",required = false ,defaultValue = "10") limit:Int,
                  request: HttpServletRequest):LayPage<ApplyVo>{
        val user = getUser()
        return service!!.applyList(user,page,limit)
    }
    @PostMapping(value= ["/repairList"])
    @ResponseBody
    fun repairList(@RequestParam(name = "page",required = false ,defaultValue = "1")  page:Int,
                   @RequestParam(name = "limit",required = false ,defaultValue = "10") limit:Int,
                   request: HttpServletRequest):LayPage<RepairVo>{
        val user = getUser()
        return service!!.repairList(user,page,limit)
    }

    @GetMapping(value= ["/applyDetail"])
    fun applyDetail(applyId: Int?,request: HttpServletRequest):ModelAndView{
        val user = getUser()
        val view = ModelAndView()
        if(applyId == null){
            view.viewName = "redirect:/html/404.html"
        } else{
            val apply = service!!.applyDetail(user,applyId)
            if (apply!=null){
                view.viewName = "apply_detail"
                view.addObject("detail",apply)
            }else
                view.viewName = "redirect:/html/404.html"
        }
        return view
    }

    @GetMapping(value= ["/repairDetail"])
    fun repairDetail(applyId: Int?,request: HttpServletRequest):ModelAndView{
        val user = getUser()
        val view = ModelAndView()
        if(applyId == null){
            view.viewName = "redirect:/html/404.html"
        } else{
            val repair = service!!.repairDetail(user,applyId)
            if (repair!=null){
                view.viewName = "repair_detail"
                view.addObject("detail",repair)
            }else
                view.viewName = "redirect:/html/404.html"
        }
        return view
    }

    @GetMapping("/evaluate_edit")
    fun evaluateEdit():String{
        return "evaluate"
    }
}