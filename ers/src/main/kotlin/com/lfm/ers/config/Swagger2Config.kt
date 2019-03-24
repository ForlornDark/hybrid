package com.lfm.ers.config

import com.google.common.collect.Iterators
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping
import org.springframework.web.servlet.mvc.Controller
import springfox.documentation.annotations.ApiIgnore
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*

@Configuration
@EnableSwagger2
@org.springframework.stereotype.Controller
@ApiIgnore
open class Swagger2Config {


    private fun apiInfo():ApiInfo{
        val builder = ApiInfoBuilder()
        return builder.title("ers Api文档").
                description("create by lfm").
                version("1.0.0").
                contact(Contact("刘福明","http://www.baidu.com","forlorndark@gmail.com")).
                build()
    }

    @Bean
    @ConditionalOnMissingBean
    open fun docket():Docket{
        return Docket(DocumentationType.SWAGGER_2).
                enable(true).
//                select().
//                apis(RequestHandlerSelectors.basePackage("com.lfm.ers.controller")).
//                build().
                apiInfo(apiInfo())
    }

//    @Bean("swagger2Controller")
//    open fun originUiController(): Controller {
//        return Controller { _,_-> ModelAndView("redirect:swagger-ui.html")  }
//    }
//
//    @Bean
//    @DependsOn("swagger2Controller")
//    open fun swagger2ControllerMapping():SimpleUrlHandlerMapping{
//        val mapping = SimpleUrlHandlerMapping()
//        val properties = Properties()
//        properties.setProperty("swagger","swagger2Controller")
//        properties.setProperty("swagger2","swagger2Controller")
//        mapping.setMappings(properties)
//        return mapping
//    }
    @RequestMapping(value = ["swagger","swagger2"])
    fun fix():String{
        return "redirect:swagger-ui.html"
    }
}