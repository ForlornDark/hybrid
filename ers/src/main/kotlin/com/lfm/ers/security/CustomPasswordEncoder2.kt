package com.lfm.ers.security

import com.lfm.ers.mapper.SysUserMapper
import com.lfm.ers.utils.MD5Util
import com.lfm.ers.utils.PasswordUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AccountExpiredException
import org.springframework.security.crypto.password.PasswordEncoder


/**
 * 自定义密码验证类
 * @author Boomer
 * @date 2017/10/16 10:47
 */
class CustomPasswordEncoder2 : PasswordEncoder {

    override fun encode(rawPassword: CharSequence): String? {
        return MD5Util.md5Encode(rawPassword.toString())
    }

    fun encode(rawPassword: CharSequence,salt:CharSequence):String?{
        return PasswordUtil.encode(rawPassword.toString(),salt.toString())
    }

    fun matches(rawPassword: CharSequence,rawSalt : String, encodedPassword: String): Boolean {
        return PasswordUtil.check(rawPassword.toString(),rawSalt,encodedPassword)
    }
    override fun matches(rawPassword: CharSequence, encodedPassword: String): Boolean {

        val match = encodedPassword.equals(encode(rawPassword), ignoreCase = true)

        return match
    }

}