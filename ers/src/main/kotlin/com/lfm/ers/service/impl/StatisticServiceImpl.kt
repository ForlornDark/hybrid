package com.lfm.ers.service.impl

import com.lfm.ers.mapper.ApplyMapper
import com.lfm.ers.mapper.DepartMapper
import com.lfm.ers.mapper.RepairMapper
import com.lfm.ers.service.StatisticService
import com.lfm.ers.vo.Value
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class StatisticServiceImpl :StatisticService{

    @Autowired
    private val applyMapper:ApplyMapper?= null

    @Autowired
    private val repairMapper:RepairMapper?= null

    @Autowired
    private val departMapper:DepartMapper?= null

    override fun getCountByApplyState(): Map<String, Long> {
        val re = hashMapOf("state0" to 0L, "state1" to 0L,"state2" to 0L)
        val list = applyMapper!!.countByState()
        for (i in list){
            re[i.name!!] = i.value!!
        }
        return re
    }


    override fun getRepairCount(): List<Value<String, Long>> {
        //计算当前日期的前一个月
        val start = LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

        return repairMapper!!.countByRID(start)
    }

    override fun getDepartUserCount(): List<Value<String, Long>> {
        return departMapper!!.countDepartUser()
    }

    override fun getDepartApplyCount(): List<Value<String, Long>> {
        return applyMapper!!.countDepartApply()
    }

    override fun getAvgMarkOrder(): List<Value<String, BigDecimal>> {
        val array = repairMapper!!.avgMarkOrder()
        for (i in array){
            i.value = i.value?.setScale(1,RoundingMode.HALF_UP)
        }
        return array
    }

    override fun getEquipTypeApplyCount(): List<Value<String, Long>> {
        return applyMapper!!.countEquipTypeApply()
    }
}