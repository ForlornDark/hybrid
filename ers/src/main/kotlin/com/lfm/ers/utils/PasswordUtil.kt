package com.lfm.ers.utils

import java.security.MessageDigest
import java.util.*

class PasswordUtil {
    companion object {
        private val hexDigits = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f")
        /**
         * 通过密码获取新密码,随机盐
         * 返回新密码,随机盐数组
         */
        fun encode(src:String):Array<String>{
            val bytes = ByteArray(16)
            Random().nextBytes(bytes)
            val salt  = byteArrayToHexString(bytes)
            val mdInst = MessageDigest.getInstance("MD5")
            mdInst.update(salt.toByteArray())
            val password =byteArrayToHexString(  mdInst.digest(src.toByteArray()))
            return arrayOf(password,salt)
        }

        /**
         * 编码
         */
        fun encode(src:String,salt: String):String?{
            val mdInst = MessageDigest.getInstance("MD5")
            mdInst.update(salt.toByteArray())
            return byteArrayToHexString(  mdInst.digest(src.toByteArray()))
        }

        /**
         * 通过盐和未加密的密码比对加密后的密码
         */
        fun check(srcPwd:String?,salt:String?,toPwd:String?):Boolean{
            if (srcPwd == null || salt == null || toPwd == null)
                return false
            val mdInst = MessageDigest.getInstance("MD5")
            val bytesSalt = salt.toByteArray()
            mdInst.update(bytesSalt)
            val newPwd =byteArrayToHexString(  mdInst.digest(srcPwd.toByteArray()))
            return toPwd.compareTo(newPwd) == 0
        }
        fun byteArrayToHexString(b: ByteArray): String {
            val resultSb = StringBuffer()
            for (i in b.indices) {
                resultSb.append(byteToHexString(b[i]))
            }
            return resultSb.toString()
        }

        private fun byteToHexString(b: Byte): String {
            var n = b.toInt()
            if (b < 0) {
                n = b + 256
            }

            val d1 = n / 16
            val d2 = n % 16
            return hexDigits[d1] + hexDigits[d2]
        }
    }
}
//fun main(args:Array<String>){
//    val re = PasswordUtil.encode("96e79218965eb72c92a549dd5a330112")
//    //pwd
//    System.out.println(re[0])
//    //salt
//    System.out.println(re[1])
////    System.out.println(PasswordUtil.check("123",re[1],re[0]))
//}