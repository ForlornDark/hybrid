package com.lfm.ers.utils
import java.security.MessageDigest

/**
 * Created by zj on 2017/3/11 0011.
 */
class MD5Util {
    companion object {
        private val hexDigits = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f")

        private fun byteArrayToHexString(b: ByteArray): String {
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

        fun md5Encode(origin: String): String? {
            val hexDigits = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F')

            try {
                val e = origin.toByteArray(charset("UTF-8"))
                val mdInst = MessageDigest.getInstance("MD5")
                mdInst.update(e)
                val md = mdInst.digest()
                val j = md.size
                val str = CharArray(j * 2)
                var k = 0

                for (i in 0 until j) {
                    val byte0 = md[i]
                    str[k++] = hexDigits[byte0.toInt().ushr(4) and 15]
                    str[k++] = hexDigits[byte0.toInt() and 15 ]

                }

                return String(str)
            } catch (var10: Exception) {
                var10.printStackTrace()
                return null
            }

        }
    }
}
//fun main(args:Array<String>){
//    System.out.print(MD5Util.md5Encode("123"))
//}