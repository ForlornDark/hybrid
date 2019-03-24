package com.lfm.ers.utils

import java.io.File
import java.util.*

class Constants {
    companion object {
        var upload_dir:String? = null
        var temp_dir:String? = null
        var persist_dir:String? = null
        val temp_dir_name:String="temp"
        val persist_dir_name:String="persist"
        init {
            val resource = ResourceBundle.getBundle("constants")
            upload_dir = resource.getString("upload_dir")
//            upload_dir?:throw RuntimeException("上传文件夹初始化失败...请设置")
            if (upload_dir == null){
                throw RuntimeException("上传文件夹初始化失败...请设置")
            }else{
                temp_dir = "$upload_dir/$temp_dir_name/"
                persist_dir = "$upload_dir/$persist_dir_name/"
                val tempFile = File(temp_dir)
                System.out.println(tempFile.absolutePath)
                if (!tempFile.exists()){
                    tempFile.mkdirs()
                }
                val persistFile = File(persist_dir)
                if (!persistFile.exists())
                    persistFile.mkdirs()
            }
        }
    }
}