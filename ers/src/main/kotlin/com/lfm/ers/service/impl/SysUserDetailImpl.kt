package com.lfm.ers.service.impl

import com.lfm.ers.entity.SysUser
import com.lfm.ers.mapper.SysUserMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.transaction.annotation.Transactional

open class SysUserDetailImpl:UserDetailsService{
    @Autowired
    private var sysUserMapper:SysUserMapper?=null

    @Transactional(readOnly = true)
    override fun loadUserByUsername(username: String?): UserDetails {
        val sysUser = sysUserMapper?.selectByUserName(username)
        sysUser?:throw UsernameNotFoundException("用户名未找到")
        return User(sysUser?.name,sysUser?.password, AuthorityUtils.createAuthorityList(sysUser?.roleName))
    }
}