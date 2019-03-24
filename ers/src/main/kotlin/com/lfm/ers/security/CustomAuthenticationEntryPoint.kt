package com.lfm.ers.security

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.DefaultRedirectStrategy
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint

class CustomAuthenticationEntryPoint(loginFormUrl: String?) : LoginUrlAuthenticationEntryPoint(loginFormUrl) {
    private val redirectStrategy = DefaultRedirectStrategy()

    override fun commence(request: javax.servlet.http.HttpServletRequest?, response: javax.servlet.http.HttpServletResponse?, authException: AuthenticationException?) {
        val requestType = request?.getHeader("X-Requested-With")
        val uri = request?.requestURI
        var redirectUrl:String?=null
        if (uri?.indexOf("ajax") == -1 && requestType == null) {

            if (this.isUseForward) {
                if (this.isForceHttps && "http".equals(request.scheme)) {
                    redirectUrl = buildHttpsRedirectUrlForRequest(request);
                }

                if (redirectUrl == null) {
                    val loginForm  = determineUrlToUseForThisRequest(request, response, authException);
                    val dispatcher = request.getRequestDispatcher(loginForm);
                    dispatcher.forward(request, response);
                    return;
                }
            } else {
                // redirect to login page. Use https if forceHttps true
                redirectUrl = buildRedirectUrlToLoginPage(request, response, authException);
            }

            redirectStrategy.sendRedirect(request, response, redirectUrl);
        } else {
            // ajax请求，返回json，替代redirect到login page
            //构造ajax返回实体类
            response?.characterEncoding="UTF-8";
            response?.contentType="application/json; charset=utf-8"
//            response?.setContentType("text/html;charset=UTF-8");
            val out = response?.writer
            out?.print("ajax请求无访问权限");
            out?.flush();
            out?.close();
            return;
        }
    }
}