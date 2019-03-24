package com.lfm.ers.security

import com.lfm.ers.entity.SysUser
import org.springframework.security.authentication.*
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException


/**
 * 验证提供者
 * @author Boomer
 * @date 2017/10/16 10:46
 */
class CustomAuthenticationProvider2 (private val userDetailsService: CustomUserDetailsService
                                     ,private val encoder2: CustomPasswordEncoder2) : AbstractUserDetailsAuthenticationProvider(){



    override fun retrieveUser(username: String?, authentication: UsernamePasswordAuthenticationToken?): UserDetails {
        username?:throw UsernameNotFoundException("该用户不存在")
        return userDetailsService.loadUserByUsername(username)
    }

    override fun additionalAuthenticationChecks(userDetails: UserDetails?, authentication: UsernamePasswordAuthenticationToken?) {
        if (userDetails == null || authentication == null || authentication.credentials == null){
            throw BadCredentialsException("密码错误")
        }

        val user = userDetailsService.getUserByUserName(userDetails.username)
        val rawPassword =  authentication.credentials.toString()

        val salt = user?.salt?:throw BadCredentialsException("密码错误")

        if(!encoder2.matches(rawPassword,salt,userDetails!!.password)){
            throw BadCredentialsException("密码错误")
        }
    }

}
