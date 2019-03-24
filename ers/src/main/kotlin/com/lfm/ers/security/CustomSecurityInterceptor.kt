package com.lfm.ers.security

import org.springframework.security.access.SecurityMetadataSource
import org.springframework.security.access.intercept.AbstractSecurityInterceptor
import org.springframework.security.access.intercept.InterceptorStatusToken
import org.springframework.security.web.FilterInvocation
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource
import javax.servlet.*

class CustomSecurityInterceptor :Filter, AbstractSecurityInterceptor (){
    var  securityMetadataSource: FilterInvocationSecurityMetadataSource ?= null
    var token: InterceptorStatusToken?=null
    override fun destroy() {
    }

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val fi = FilterInvocation(request, response, chain);
        invoke(fi)
    }
    /**
     * @param fi 参数
     * @throws IOException      io异常
     * @throws ServletException servlet异常
     */
    fun invoke(  fi: FilterInvocation) {
        token = super.beforeInvocation(fi);
        try {
            fi.chain.doFilter(fi.request, fi.response);
        } catch (e:Exception) {
            super.afterInvocation(token, null);
        }
    }
    override fun init(filterConfig: FilterConfig?) {
    }

    override fun getSecureObjectClass(): Class<*> {
        return FilterInvocation::class.java
    }



    override fun obtainSecurityMetadataSource(): SecurityMetadataSource? {
        return this.securityMetadataSource
    }
}