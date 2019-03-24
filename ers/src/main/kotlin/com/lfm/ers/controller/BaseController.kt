package com.lfm.ers.controller

import com.lfm.ers.entity.SysUser
import com.lfm.ers.security.CustomUserDetailsService
import com.lfm.ers.service.SysUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.servlet.ModelAndView
import springfox.documentation.annotations.ApiIgnore

@ApiIgnore
open class BaseController {

    @Autowired
    private val userService : SysUserService?= null;

    fun getUser():SysUser{
        val details =  SecurityContextHolder.getContext().authentication.principal as UserDetails
        return userService?.getUserByName(details.username)!!
    }

    fun view(name: String):ModelAndView{
        val view = ModelAndView(name)
        view.addObject("user",getUser())
        return view
    }

}