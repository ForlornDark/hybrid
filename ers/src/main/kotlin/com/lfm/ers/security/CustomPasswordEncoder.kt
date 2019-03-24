package com.lfm.ers.security

import com.lfm.ers.utils.MD5Util
import org.springframework.security.authentication.AccountExpiredException
import org.springframework.security.crypto.password.PasswordEncoder


/**
 * 自定义密码验证类
 * @author Boomer
 * @date 2017/10/16 10:47
 */
class CustomPasswordEncoder : PasswordEncoder {

    override fun encode(rawPassword: CharSequence): String? {
        return MD5Util.md5Encode(rawPassword.toString())
    }

    override fun matches(rawPassword: CharSequence, encodedPassword: String): Boolean {

        val match = encodedPassword.equals(encode(rawPassword), ignoreCase = true)
        if (!match) {
            throw AccountExpiredException("用户名密码错误")
        }

        return match
    }
}