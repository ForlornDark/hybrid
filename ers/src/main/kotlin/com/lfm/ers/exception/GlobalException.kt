package com.lfm.ers.exception

import org.springframework.web.servlet.HandlerExceptionResolver
import org.springframework.web.servlet.ModelAndView
import java.lang.Exception
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class GlobalException : HandlerExceptionResolver {
    override fun resolveException(request: HttpServletRequest?, response: HttpServletResponse?, handler: Any?, ex: Exception?): ModelAndView {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}