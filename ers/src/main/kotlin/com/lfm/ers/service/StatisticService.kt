package com.lfm.ers.service

import com.lfm.ers.vo.Value
import java.math.BigDecimal

interface StatisticService {
    /**
     * 获取申请各个状态的数量
     */
    fun getCountByApplyState():Map<String,Long>;

    /**
     * 获取维修员最近一月的排行,最多5人
     */
    fun getRepairCount():List<Value<String, Long>>

    /**
     * 获取组织下用户数量
     */
    fun getDepartUserCount():List<Value<String,Long>>

    /**
     *各部门用户数量
     */
    fun getDepartApplyCount():List<Value<String,Long>>

    /**
     * 获取平均评价的排序，前5
     */
    fun getAvgMarkOrder():List<Value<String, BigDecimal>>

    /**
     * 获取不同设备类型的申请数量
     */
    fun getEquipTypeApplyCount():List<Value<String,Long>>
}