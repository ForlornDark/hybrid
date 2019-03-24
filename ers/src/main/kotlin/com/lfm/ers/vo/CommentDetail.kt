package com.lfm.ers.vo

import com.lfm.ers.entity.Comment

class CommentDetail :Comment() {
    var userName :String ?=null
    var photo :String ?= null
    var agreed :Boolean = false
}