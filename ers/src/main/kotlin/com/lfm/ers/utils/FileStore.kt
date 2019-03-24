package com.lfm.ers.utils

import java.io.*
import java.time.Instant

class FileStore {
    companion object {
        val BUFF_SZIE:Int = 4096
        val byteArray = ByteArray(BUFF_SZIE)
        fun upload(name: String,inputStream: InputStream,temp:Boolean):String?{
            val path = Instant.now().toEpochMilli().toString(16) + getSuffix(name)
            val name2 = if (temp) Constants.temp_dir_name+'-'+path else Constants.persist_dir_name +'-'+ path
            val path2 = getPath(name2)
            path2?.let {
                val file = File(it)
                val parent = file.parentFile
                if (!parent.exists()){
                    parent.mkdirs()
                }
                writeFile(inputStream,file)
            }
            return name2
        }
        fun download(name:String,out:OutputStream){
            val path = getPath(name)
            path?.let {
                val file = File(it)
                readFile(file,out)
            }
        }
        fun delete(name: String){
            val path = getPath(name)
            path?.let {
                val file = File(it)
                if (file.exists()){
                    file.delete()
                }
            }
        }
        fun moveFromTemp(name: String){

        }

        fun writeFile(inputStream: InputStream,file: File){
            val fileOutputStream = FileOutputStream(file)
            copy(inputStream,fileOutputStream)
            inputStream.close()
            fileOutputStream.close()
        }
        fun readFile(file: File,out: OutputStream){
            if (file.exists() && file.isFile){
                val fileInputStream = FileInputStream(file)
                copy(fileInputStream,out)
                fileInputStream.close()
                out.close()
            }
        }
        fun copy(inputStream: InputStream,out: OutputStream){
            var len = 0
            while (inputStream.read(byteArray).also {  len = it} > 0){
                out.write(byteArray,0,len)
            }
        }
        /**
         * 获取文件路径
         */
        fun getPath(path:String):String?{
            val str = path.split("-")
            if (str.size != 2){
                return null
            }
            val pathBuffer = StringBuffer(Constants.upload_dir)
            pathBuffer.append('/')
            pathBuffer.append(str[0])
            val path2 = str[1]
            var i = 0
            while (i < path2.length){
                if ('.'.compareTo(path2[i]) == 0){
                    break
                }
                pathBuffer.append('/')
                pathBuffer.append(path2[i])
                i++
            }
            while (i < path2.length){
                pathBuffer.append(path2[i])
                i++
            }
            return pathBuffer.toString()
        }

        /**
         * 获取文件后缀名
         */
        fun getSuffix(name: String):String{
            if (name.isEmpty())
                return ""
            var i = name.length - 1
            var suffix = StringBuffer()
            while (i < name.length && i >= 0){
                if ('.'.compareTo(name[i]) == 0){
                    suffix.insert(0,'.')
                    break
                }
                suffix.insert(0,name[i])
                i--
            }
            return if (i <= 0) ""
            else suffix.toString()
        }
    }

}
//fun main(array: Array<String>){
//    System.out.println(FileStore.getPath("temp-esdfdfasdf.jpg"))
//
//}