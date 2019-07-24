package com.company.commonlibrary.bean

/**
 * @author dlh
 * @email 18279727279@163.com
 * @date 2019/05/05 13:49
 * @des
 */
class RequestEntity<T>(body: T) {
    var token = ""
    var ver = "1.0"
    var sys = "android"
    var sysVer = "1.0"
    var channel = ""
    var body: T? = body

}
