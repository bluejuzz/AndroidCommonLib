package com.company.commonlib.camerax

import com.google.zxing.Result

/**
 * @author dinglaihong
 * @email 18279727279@163.com
 * @date 2019/7/6
 * @des
 */

interface ScanQRCodeCallback {
    fun scanQRCodeSuccess(result: Result)
    fun scanQRCodeFail(error: String)

}