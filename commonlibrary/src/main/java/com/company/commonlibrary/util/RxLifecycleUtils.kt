package com.company.commonlibrary.util


import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

import com.uber.autodispose.AutoDispose
import com.uber.autodispose.AutoDisposeConverter
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider

/**
 * @author dlh
 * @email 18279727279@163.com
 * @date 2019/05/06 16:05
 * @des 生命周期工具类
 */
class RxLifecycleUtils private constructor() {
    init {
        throw IllegalStateException("Can't instance the RxLifecycleUtils")
    }

    companion object {

        fun <T> bindLifecycle(lifecycleOwner: LifecycleOwner, untilEvent: Lifecycle.Event): AutoDisposeConverter<T> {
            return AutoDispose.autoDisposable(
                    AndroidLifecycleScopeProvider.from(lifecycleOwner, untilEvent)
            )
        }
    }
}
