package com.lfm.ers.vo

import java.time.LocalDateTime
import java.util.*

class QuestionItem {
    var id:Int? = null

    var photo:String?= null

    var title:String?=null

    var userName:String?=null

    var createTime: LocalDateTime?=null

    var showTime: String?=null

    var commentCount: Int = 0

    var state : Int = 0;

    var type: Int = 0
}