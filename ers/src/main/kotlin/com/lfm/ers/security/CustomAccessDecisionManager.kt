package com.lfm.ers.security

import org.springframework.security.access.ConfigAttribute
import org.springframework.security.authentication.InsufficientAuthenticationException
import org.springframework.security.core.Authentication
import org.springframework.security.access.AccessDecisionManager


class CustomAccessDecisionManager : AccessDecisionManager {
    @Throws(AccessDeniedException::class, InsufficientAuthenticationException::class)
    override fun decide(authentication: Authentication, `object`: Any,
                        configAttributes: Collection<ConfigAttribute>?) {
        if (null == configAttributes) {
            return
        }
        val cons = configAttributes.iterator()
        while (cons.hasNext()) {
            val ca = cons.next()
            val needRole = ca.attribute
            // gra 为用户所被赋予的权限，needRole为访问相应的资源应具有的权限

            for (gra in authentication.authorities) {
                if (needRole.trim { it <= ' ' } == gra.authority.trim { it <= ' ' }) {
                    return
                }
            }
        }
        throw org.springframework.security.access.AccessDeniedException("没有权限")
    }

    override fun supports(attribute: ConfigAttribute): Boolean {
        // TODO Auto-generated method stub
        return true
    }

    override fun supports(clazz: Class<*>): Boolean {
        // TODO Auto-generated method stub
        return true
    }
}