package com.lfm.ers.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.lfm.ers.entity.Apply
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.format.Formatter
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*


@Configuration
open class JacksonConfig {

//    @Bean
//    open fun javaTimeModule():JavaTimeModule{
//        val module = JavaTimeModule()
//        val dateTimePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
//        val datePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd")
//        val timePattern = DateTimeFormatter.ofPattern("HH:mm:ss")
//        module.addSerializer(LocalDateTime::class.java, LocalDateTimeSerializer(dateTimePattern))
//        module.addSerializer(LocalDate::class.java, LocalDateSerializer(datePattern))
//        module.addSerializer(LocalTime::class.java, LocalTimeSerializer(timePattern))
//        module.addDeserializer(LocalDateTime::class.java, LocalDateTimeDeserializer(dateTimePattern))
//        module.addDeserializer(LocalDate::class.java, LocalDateDeserializer(datePattern))
//        module.addDeserializer(LocalTime::class.java, LocalTimeDeserializer(timePattern))
//        return module
//    }
//    @Bean
//    open fun objectMapper(builder: Jackson2ObjectMapperBuilder):ObjectMapper{
//        val objectMapper = builder.createXmlMapper(false).build<ObjectMapper>()
//        val objectMapper = ObjectMapper()
//        val module = JavaTimeModule()
//        val dateTimePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
//        val datePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd")
//        val timePattern = DateTimeFormatter.ofPattern("HH:mm:ss")
//        module.addSerializer(LocalDateTime::class.java, LocalDateTimeSerializer(dateTimePattern))
//        module.addSerializer(LocalDate::class.java, LocalDateSerializer(datePattern))
//        module.addSerializer(LocalTime::class.java, LocalTimeSerializer(timePattern))
//        module.addDeserializer(LocalDateTime::class.java, LocalDateTimeDeserializer(dateTimePattern))
//        module.addDeserializer(LocalDate::class.java, LocalDateDeserializer(datePattern))
//        module.addDeserializer(LocalTime::class.java, LocalTimeDeserializer(timePattern))
//        objectMapper.registerModule(module)
//        return objectMapper
//    }



}