package com.lfm.ers.vo

data class Info<T>(val success:Boolean = false,var msg:String = "", var data:T? = null)