package com.company.commonlibrary.base


import androidx.multidex.MultiDexApplication

import com.blankj.utilcode.util.Utils

/**
 * @author dlh
 * @email 18279727279@163.com
 * @date 2019/05/05 13:48
 * @des
 */
open class BaseApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
    }
}
