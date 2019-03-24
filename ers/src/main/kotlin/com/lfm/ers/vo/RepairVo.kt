package com.lfm.ers.vo

import java.time.LocalDateTime
import java.util.*

class RepairVo :ApplyVo(){
//    su2.full_name full_name2,su2.phone phone2,de2.`name` depart_name2 ,re.accept_time,re.repair_time,re.repair_note,re.evaluate_time,
//    re.evaluate_mark,re.evaluate_note ,ap.id,ap.state,ap.create_time,
//    ap.fault_descrip,ap.expect_time, ap.detail_address,su.full_name,su.phone,de.`name` depart_name,
//    fi.`name` info_name,et.`name` equip_name
    var fullName2:String?=null
    var phone2:String?=null
    var departName2:String?=null
    var acceptTime: LocalDateTime?=null
    var repairTime:LocalDateTime?=null
    var repairNote:String?=null
    var evaluateTime:LocalDateTime?=null
    var evaluateMark:Int?=null
    var evaluateNote:String?=null

}