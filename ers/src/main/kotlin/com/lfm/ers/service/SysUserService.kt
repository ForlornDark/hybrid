package com.lfm.ers.service

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.extension.service.IService
import com.lfm.ers.entity.Apply
import com.lfm.ers.entity.SysUser
import com.lfm.ers.entity.SysUserDetail
import com.lfm.ers.vo.Info
import com.lfm.ers.vo.LayPage
import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpServletRequest

interface SysUserService :IService<SysUser>{
    /**
     * 获取分页用户
     */
    fun getPageUser(page:Int,limit:Int):LayPage<SysUserDetail>

    /**
     * 删除用户
     */
    fun delUser(list: List<Int>):Int?

    fun save(userId:Int?,username: String,phone:String,roleId:Int,sex:Boolean):Boolean

    /**
     * 登录操作
     */
    fun login(name:String,password:String,request: HttpServletRequest):Boolean

    fun logout(request: HttpServletRequest):Boolean
    /**
     * 注册
     */
    fun register(username:String,password: String,type:Int):Boolean

    /**
     * 检查用户名唯一s
     */
    fun checkUserName(username: String):Boolean

    fun editPwd(username: String,oldPwd:String,newPwd:String,request: HttpServletRequest):Info<String>

    fun getUserById(id:Int):SysUserDetail?

    fun savePersonal(user: SysUser)

    fun photoUpload(user: SysUser,file: MultipartFile):Info<String>

    fun getUserByName(username: String):SysUser?
}