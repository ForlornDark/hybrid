package com.lfm.ers.controller

import com.lfm.ers.service.StatisticService
import com.lfm.ers.utils.FileStore
import com.lfm.ers.vo.Info
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import java.time.*
import java.time.format.DateTimeFormatter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
class StaticController :BaseController(){
    val start = LocalDate.parse("2018-03-31", DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay().atZone(ZoneId.systemDefault()).toEpochSecond()

    @Autowired
    private val statisticService:StatisticService? = null

    @GetMapping(value = ["/index"])
    fun index(request: HttpServletRequest):ModelAndView{
       return view("index")
    }
    @GetMapping(value = ["/"])
    fun welcome(request: HttpServletRequest):String{
//        val session = request.getSession(false)
//        return if (session.getAttribute("SPRING_SECURITY_CONTEXT") == null)
//          "redirect:/login"
//        else "redirect:/index"
        return "redirect:/index"
    }
    @GetMapping(value= ["/login"])
    fun login():String{
        return "login2";
    }
    @PostMapping(value= ["/login/ok"])
    @ResponseBody
    fun loginOk(request: HttpServletRequest): Info<String> {
        return Info(true,"登录成功","");
    }
    @PostMapping(value= ["/login/faild"])
    @ResponseBody
    fun loginFaild(request: HttpServletRequest): Info<String> {
        return Info(false,"用户名或密码错误","");
    }
    @GetMapping(value = ["/register"])
    fun register():String{
        return "register2"
    }
    @GetMapping(value= ["/about"])
    fun about():String{
        return "about"
    }
    @GetMapping(value= ["/home"])
    fun home():ModelAndView{
        val view = ModelAndView("home")
        val day = (Instant.now().epochSecond -start)/86400

        view.addObject("day",day);
        view.addObject("applyCount",statisticService!!.getCountByApplyState())
        return view
    }
    @GetMapping(value = ["/source/{name:.*}"])
    fun source( @PathVariable(name = "name",required = true) name:String,request: HttpServletRequest,response: HttpServletResponse){
        FileStore.download(name,response.outputStream)
    }
}