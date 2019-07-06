package com.company.commonlibrary.base


import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.annotation.CallSuper
import androidx.annotation.MainThread

import com.company.commonlibrary.util.RxLifecycleUtils
import com.uber.autodispose.AutoDisposeConverter


/**
 * @author dlh
 * @email 18279727279@163.com
 * @date 2019/05/05 13:48
 * @des
 */
class BasePresenter<V : IView, M : IModel>(var rootView: V?, var model: M?) : IPresenter {

    private var lifecycleOwner: LifecycleOwner? = null

    protected fun <T> bindLifecycle(untilEvent: Lifecycle.Event?): AutoDisposeConverter<T> {
        if (null == lifecycleOwner) {
            throw NullPointerException("lifecycleOwner == null")
        }
        return RxLifecycleUtils.bindLifecycle(lifecycleOwner!!, untilEvent
                ?: Lifecycle.Event.ON_DESTROY)
    }

    @CallSuper
    @MainThread
    override fun onLifecycleChanged(owner: LifecycleOwner, event: Lifecycle.Event) {

    }

    override fun setLifecycleOwner(lifecycleOwner: LifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner
    }

    @CallSuper
    @MainThread
    override fun onCreate(owner: LifecycleOwner) {

    }

    @CallSuper
    @MainThread
    override fun onStart(owner: LifecycleOwner) {

    }

    @CallSuper
    @MainThread
    override fun onResume(owner: LifecycleOwner) {

    }

    @CallSuper
    @MainThread
    override fun onPause(owner: LifecycleOwner) {

    }

    @CallSuper
    @MainThread
    override fun onStop(owner: LifecycleOwner) {

    }

    @CallSuper
    @MainThread
    override fun onDestroy(owner: LifecycleOwner) {
        if (model != null) {
            model!!.onDestroy()
            this.model = null
        }
        this.rootView = null
    }

    companion object {

        private val TAG = BasePresenter::class.java.simpleName
    }

}
