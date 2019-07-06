package com.company.commonlib

import com.company.commonlibrary.base.BaseApplication
import com.company.commonlibrary.retrofit.RetrofitClient

/**
 * @author dinglaihong
 * @email 18279727279@163.com
 * @date 2019/7/6
 * @des
 */
class MainApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        RetrofitClient.init("https://www.baidu.com/")
    }

}