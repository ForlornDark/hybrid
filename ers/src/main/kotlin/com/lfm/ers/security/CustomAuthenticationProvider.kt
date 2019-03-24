package com.lfm.ers.security

import org.springframework.security.authentication.AccountExpiredException
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.authentication.LockedException
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException


/**
 * 验证提供者
 * @author Boomer
 * @date 2017/10/16 10:46
 */
class CustomAuthenticationProvider :DaoAuthenticationProvider() {
    override fun setUserDetailsService(userDetailsService: UserDetailsService) {
        super.setUserDetailsService(userDetailsService)
    }

    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication {
        //千万要调用父类的方法，否则永远不会跳入用户合法处理逻辑
        try {
            return super.authenticate(authentication)
        } catch (e: LockedException) {
            throw e
        } catch (e: AccountExpiredException) {
            throw e
        }

    }
}