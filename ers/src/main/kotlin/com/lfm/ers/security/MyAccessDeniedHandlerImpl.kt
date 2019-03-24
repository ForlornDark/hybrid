package com.lfm.ers.security

import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 访问拒绝处理器
 */
class MyAccessDeniedHandlerImpl() : AccessDeniedHandler {
    var accessDeniedUrl:String?=null
    override fun handle(request: HttpServletRequest?, response: HttpServletResponse?, accessDeniedException: AccessDeniedException?) {
        val isAjax = "XMLHttpRequest".equals(request?.getHeader("X-Requested-With"))

        //如果是ajax请求
        if (isAjax) {
            response?.characterEncoding="UTF-8"
            response?.contentType="application/json; charset=utf-8"
            response?.contentType="text/html;charset=UTF-8"
            val out = response?.writer
            out?.print("ajax请求无访问权限")
            out?.flush();
            out?.close();
            return;
        } else {
            val path = request?.contextPath;
            val basePath = request?.scheme+"://"+request?.serverName+":"+request?.serverPort+path+"/";
            response?.sendRedirect(basePath+accessDeniedUrl);
        }
    }
}