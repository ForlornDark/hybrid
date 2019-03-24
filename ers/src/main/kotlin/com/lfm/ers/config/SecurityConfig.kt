package com.lfm.ers.config

import com.lfm.ers.security.CustomAuthenticationProvider2
import com.lfm.ers.security.CustomPasswordEncoder2
import com.lfm.ers.security.CustomUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.security.reactive.PathRequest
import org.springframework.context.annotation.Configuration
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers.matchers
import org.springframework.security.authorization.AuthenticatedReactiveAuthorizationManager.authenticated
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter


@Configuration
open class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    val provider2:CustomAuthenticationProvider2 ?= null

//    override fun configure(auth: AuthenticationManagerBuilder?) {
//        auth?.let {
//            it.authenticationProvider(provider2)
//        }
//    }

    @Bean
    open fun passwordEncoder2():CustomPasswordEncoder2{
        return CustomPasswordEncoder2()
    }

    @Bean
    open fun userDetail():CustomUserDetailsService{
        return CustomUserDetailsService()
    }

    @Bean
    open fun provider(encoder2: CustomPasswordEncoder2,userDetailsService: CustomUserDetailsService):CustomAuthenticationProvider2{
        return CustomAuthenticationProvider2(userDetailsService,encoder2)
    }

    override fun configure(http: HttpSecurity?) {
        http?.let {
            it.csrf().disable().headers().frameOptions().sameOrigin().and()
                    .authorizeRequests().antMatchers("/login").permitAll().and()
                    .authorizeRequests().antMatchers("/swagger*/**").permitAll().and()
                    .authorizeRequests().antMatchers("/webjars/**").permitAll().and()
                    .authorizeRequests().antMatchers("/v2/**").permitAll()
                    .and().formLogin().loginPage("/login")
                    .successForwardUrl("/login/ok")
                    .failureForwardUrl("/login/faild")
                    .loginProcessingUrl("/user/login")
                    .usernameParameter("name")
                    .passwordParameter("password")
                    .and()
                    .logout().logoutUrl("/user/logout")
                    .logoutSuccessUrl("/login")
                    .and().authorizeRequests().antMatchers("/**").authenticated()
        }
    }

    override fun configure(web: WebSecurity?) {
        web?.let {
            it.ignoring()
                    .antMatchers("/lib/**")
                    .antMatchers("/css/**")
                    .antMatchers("/js/**")
                    .antMatchers("/html/**")
                    .antMatchers("/img/**")
                    .antMatchers("/register")
                    .antMatchers("/user/register")
        }
    }
}