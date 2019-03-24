package com.lfm.ers.vo

data class LayPage<T>(val code:String,
                      val msg:String,
                      val count:Int,
                      val data:List<T> )