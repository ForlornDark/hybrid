package com.lfm.ers.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import com.lfm.ers.entity.SysUser
import com.lfm.ers.entity.SysUserDetail
import com.lfm.ers.mapper.SysUserMapper
import com.lfm.ers.service.SysUserService
import com.lfm.ers.utils.FileStore
import com.lfm.ers.utils.PasswordUtil
import com.lfm.ers.utils.ROLE
import com.lfm.ers.vo.Info
import com.lfm.ers.vo.LayPage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpServletRequest

@Service(value = "SysUserService")
class SysUserServiceImpl :ServiceImpl<SysUserMapper,SysUser>(),SysUserService{
    @Autowired
    val mapper:SysUserMapper?=null
    override fun getPageUser(page: Int, limit: Int):LayPage<SysUserDetail>{
        PageHelper.startPage<SysUserDetail>(page,limit)
        val list = mapper?.selectAll()
        val pageInfo = PageInfo<SysUserDetail>(list)
        return LayPage("0","",pageInfo.total.toInt(),pageInfo.list)

    }

    override fun delUser(list: List<Int>): Int? {
        return mapper?.delById(list)
    }

    override fun save(userId: Int?, username: String, phone: String, roleId: Int, sex: Boolean): Boolean {
        val user = SysUser()
        user.phone = phone
        user.roleId = roleId
        user.sex = sex
        return if(userId != null){
            user.id = userId
            mapper!!.updateById(user) > 0
        }else{
            user.name = username
            val ps = PasswordUtil.encode("123456")
            user.password = ps[0]
            user.salt = ps[1]
            mapper!!.insert(user) > 0
        }
    }



    override fun login(name: String, password: String, request: HttpServletRequest): Boolean {
        val user = mapper?.selectByUserName(name)
        var re = false
        if(user!=null){
            if(PasswordUtil.check(password,user.salt,user.password)){
                re = true
                request.getSession(true).setAttribute("user",user)
            }
        }
        return re
    }

    override fun logout(request: HttpServletRequest): Boolean {
        request.getSession(false).removeAttribute("user")
        return true
    }

    override fun register(username: String, password: String, type: Int): Boolean {
        val re = PasswordUtil.encode(password);
        val user = SysUser()
        if (type == 0){
            user.roleId = ROLE.USER.id
        }else if (type == 1){
            user.roleId = ROLE.REPAIRMAN.id
        }
        user.name = username
        user.password = re[0]
        user.salt = re[1]
        return  mapper!!.insert(user) > 0
    }

    override fun checkUserName(username: String): Boolean {
        return mapper!!.selectByUserName(username) == null
    }

    override fun editPwd(username: String, oldPwd: String, newPwd: String, request: HttpServletRequest): Info<String> {
        val user = mapper!!.selectByUserName(username)
        var info:Info<String>
        if(PasswordUtil.check(oldPwd,user.salt,user.password)){
            if(PasswordUtil.check(newPwd,user.salt,user.password)){
                info = Info(false,"新旧密码一致")
            }else{
                val ps = PasswordUtil.encode(newPwd)
                val u = SysUser()
                u.id = user.id
                u.password = ps[0]
                u.salt = ps[1]
                mapper!!.updateById(u)
                request.logout()
                info = Info(true,"修改成功")
            }
        }else
            info =  Info(false,"密码错误")
        return info
    }

    override fun getUserById(id: Int): SysUserDetail? {
        return mapper!!.selectDetailById(id)
    }

    override fun savePersonal(user: SysUser) {
        mapper!!.updatePersonalDetail(user)
    }

    override fun photoUpload(user: SysUser, file: MultipartFile): Info<String> {
        if (file.originalFilename == null){
            return Info(false,"")
        }
        val url  = FileStore.upload(file.originalFilename,file.inputStream,false)
        if (url != null){
            user.id?.let {
                val user1 = mapper!!.selectById(it)
                user1.photo?.let { FileStore.delete(it) }
                val user  = SysUser()
                user.id = it
                user.photo = url
                mapper?.updateById(user)
                return Info(true,"",url)
            }
        }
        return Info(false,"")
    }

    override fun getUserByName(username: String): SysUser? {
        return mapper!!.selectByUserName(username)
    }
}

