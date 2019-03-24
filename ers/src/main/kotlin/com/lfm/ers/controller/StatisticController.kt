package com.lfm.ers.controller

import com.lfm.ers.service.StatisticService
import com.lfm.ers.vo.Value
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import java.math.BigDecimal

@Controller
@RequestMapping("/statistic")
class StatisticController{

    @Autowired
    private val statisticService:StatisticService? = null

    @GetMapping("/departUserCount")
    @ResponseBody
    @PreAuthorize("hasRole('admin')")
    fun departUserCount() :List<Value<String,Long>>{
        return statisticService!!.getDepartUserCount()
    }

    @GetMapping("/departApplyCount")
    @ResponseBody
    fun departApplyCount() :List<Value<String,Long>>{
        return statisticService!!.getDepartApplyCount()
    }

    @GetMapping("/equipTypeCount")
    @ResponseBody
    fun equipTypeCount():List<Value<String,Long>> {
        return statisticService!!.getEquipTypeApplyCount()
    }

    @GetMapping("/repairCount")
    @ResponseBody
    fun repairCount() :List<Value<String,Long>>{
        return statisticService!!.getRepairCount();
    }

    @GetMapping("/markOrder")
    @ResponseBody
    fun markOrder():List<Value<String, BigDecimal>> {
        return statisticService!!.getAvgMarkOrder()
    }
}
