package com.lfm.ers.vo

import com.lfm.ers.service.ApplyService
import java.time.LocalDateTime
import java.util.*

open class ApplyVo {
//ap.id,ap.state,ap.create_time,ap.fault_descrip,ap.expect_time, ap.detail_address,
//    su.full_name,su.phone,fi.`name` info_name,et.`name` equip_name ,de.`name` depart_name
    //申请Id
    var id:Int? = null
    //申请态
    var state:Int = ApplyService.STATE_DEFAULT
    //创建时间
    var createTime: LocalDateTime? = null
    //损坏描述
    var faultDescrip:String?=null
    //期望维修时间
    var expectTime:LocalDateTime?=null
    //详细地址
    var detailAddress:String?=null
    //真实姓名
    var fullName:String?=null
    //电话
    var phone:String?=null
    //设备损坏类型名称
    var infoName :String? = null
    //设置名称
    var equipName :String? = null
    //组织名
    var departName :String? =null
}