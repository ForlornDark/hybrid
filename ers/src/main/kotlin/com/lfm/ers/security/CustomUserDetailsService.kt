package com.lfm.ers.security

import com.lfm.ers.entity.SysUser
import com.lfm.ers.mapper.SysUserMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils

/**
 * 用户合法处理逻辑
 * @author Boomer
 * @date 2017/10/16 10:48
 */
//@Service
class CustomUserDetailsService : UserDetailsService {

    @Autowired
    private var userMapper: SysUserMapper? = null

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userMapper?.selectByUserName(username)
        user?:throw UsernameNotFoundException("该用户不存在用户")
        return User(user?.name,user?.password, listOf(SimpleGrantedAuthority(user?.roleName)))
    }

    fun getUserByUserName(username: String):SysUser?{
        return userMapper?.selectByUserName(username)
    }

}