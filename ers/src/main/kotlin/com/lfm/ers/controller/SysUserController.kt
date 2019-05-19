package com.lfm.ers.controller

import com.lfm.ers.entity.SysUser
import com.lfm.ers.entity.SysUserDetail
import com.lfm.ers.security.CustomUserDetailsService
import com.lfm.ers.service.SysUserService
import com.lfm.ers.vo.Info
import com.lfm.ers.vo.LayPage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.ModelAndView
import javax.security.auth.Subject
import javax.servlet.http.HttpServletRequest

@Controller("SysUser")
@RequestMapping(value= ["/user"])
class SysUserController:BaseController(){

    @Autowired
    val service:SysUserService?=null

    @GetMapping(value= ["/users"])
    @PreAuthorize("hasRole('admin')")
    fun users():String{
        return "users";
    }

//    /**
//     * 登录
//     */
//    @PostMapping(value="/login")
//    @PreAuthorize("hasRole('test')")
//    @ResponseBody
//    fun login(name:String,password:String,request:HttpServletRequest):Info<String>{
////        if (name!=null) userDetailsService!!.loadUserByUsername(name);
//        if (name.length < 4 || name.length > 15)
//            return Info(false,"登录失败")
//        if (password.length != 32)
//            return Info(false,"登录失败")
//        val re = api!!.login(name,password,request)
//        return if (re) Info(true,"登录成功") else Info(false,"登录失败")
//    }

    /**
     * 注册
     */
    @PostMapping(value= ["/register"])
    @ResponseBody
    fun register(username:String,password: String,password2: String,type:Int):Info<String>{
        if(password.compareTo(password2) != 0){
            return Info(false,"密码不一致")
        }
         return if(service!!.checkUserName(username)){
            when(service!!.register(username,password,type)){
                true -> Info(true,"注册成功")
                false -> Info(false,"注册失败")
            }
        }else
            Info(false,"用户名已存在")
    }

    @ResponseBody
    @PostMapping(value = ["/list"])
    fun users(@RequestParam(required = false,defaultValue = "1") page:Int,
              @RequestParam(required = false,defaultValue = "10") limit:Int):LayPage<SysUserDetail>?{
        return service?.getPageUser(page,limit)
    }
//    @GetMapping(value = "/logout")
//    fun logout(request: HttpServletRequest):String{
//        api!!.logout(request)
//        return "redirect:/login"
//    }
    @PostMapping(value= ["/delete"])
    @ResponseBody
    @PreAuthorize("hasRole('admin')")
    fun del(@RequestParam(value = "list") list:List<Int>):Info<String>{
        val r:Int? = service?.delUser(list)
        val re :Info<String>  = if (r != null && r > 0)
            Info(true,"删除成功","")
        else
            Info(false,"操作失败","")
        return re
    }

    @GetMapping(value = ["/edit"])
    @PreAuthorize("hasRole('admin')")
    fun edit():String{
        return "user_edit"
    }


    @PostMapping(value= ["/saveUser"])
    @ResponseBody
    @PreAuthorize("hasRole('admin')")
    fun saveUser(id:Int?,name: String,phone:String,roleId:Int,sex:Boolean):Info<String>{
        return if(id == null){
            if(service!!.checkUserName(name)){
                if(service!!.save(null,name,phone,roleId,sex))
                    Info(true,"添加成功","")
                else Info(false,"添加失败","")
            }else
                Info(false,"用户名已被注册","")
        }else{
            if(service!!.save(id,name,phone,roleId,sex))
                Info(true,"修改成功","")
            else Info(false,"修改失败","")
        }
    }

    @GetMapping(value = ["/personal"])
    fun personal(request: HttpServletRequest):ModelAndView{
        val user = getUser()
        val id = user.id
        var detail:SysUserDetail? = null
        if(id != null)
            detail = service!!.getUserById(id)
        val view = ModelAndView("personal")
        view.addObject("info",detail)
        return view
    }
    @ResponseBody
    @PostMapping("/saveDetail")
    fun saveInfo(userInfo: SysUser,request: HttpServletRequest):Info<String>{
        val user = getUser()
        userInfo.id = user.id
        service!!.savePersonal(userInfo)
        return Info(true,"保存成功")
    }

    @PostMapping(value = ["/checkUserName"])
    fun checkUserName(username: String):Info<String>{
        return if (service!!.checkUserName(username)) Info(true) else Info(false, "用户名已存在")
    }
    @GetMapping(value= ["/password"])
    fun password():String{
        return "password"
    }

    @PostMapping(value = ["/editPwd"])
    @ResponseBody
    fun editPwd(oldPwd: String,pwd1: String,pwd2: String,request: HttpServletRequest):Info<String>{
        if (pwd1.compareTo(pwd2) != 0){
            return Info(false,"确认密码不一致")
        }
        val user = getUser()
        user?.let {
            user.name?.let {
                return service!!.editPwd(it,oldPwd,pwd1,request)
            }
            return Info(false,"未知错误")
        }
        return Info(false,"请重新登录")

    }

    @PostMapping(value = ["/photo"])
    @ResponseBody
    fun photo(photo:MultipartFile,request: HttpServletRequest):Info<String>{
        val user = getUser()
        var info = Info<String>()
        if (photo.contentType.compareTo("image/jpeg") == 0 || photo.contentType.compareTo("image/png") == 0){
            user?.let {
                info = service!!.photoUpload(it,photo)
            }
        }
        return info
    }
}
