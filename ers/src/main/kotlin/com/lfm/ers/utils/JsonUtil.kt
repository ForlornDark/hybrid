package com.lfm.ers.utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

class JsonUtil {
    companion object {
        fun getMapper():ObjectMapper{
            val mapper = ObjectMapper()
            mapper.setTimeZone(TimeZone.getTimeZone("GTM+8"))
            val module = JavaTimeModule()
            val dateTimePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val datePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val timePattern = DateTimeFormatter.ofPattern("HH:mm:ss")
            module.addSerializer(LocalDateTime::class.java,LocalDateTimeSerializer(dateTimePattern))
            module.addSerializer(LocalDate::class.java, LocalDateSerializer(datePattern))
            module.addSerializer(LocalTime::class.java, LocalTimeSerializer(timePattern))
            module.addDeserializer(LocalDateTime::class.java,LocalDateTimeDeserializer(dateTimePattern))
            module.addDeserializer(LocalDate::class.java,LocalDateDeserializer(datePattern))
            module.addDeserializer(LocalTime::class.java,LocalTimeDeserializer(timePattern))
            return mapper
        }
    }
}