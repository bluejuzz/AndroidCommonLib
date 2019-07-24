package com.company.commonlibrary.base

import android.content.res.Resources
import androidx.annotation.LayoutRes
import androidx.lifecycle.Lifecycle

import android.os.Build
import android.os.Bundle

import androidx.annotation.CallSuper
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner

import android.view.WindowManager
import com.blankj.utilcode.util.ScreenUtils

import com.company.commonlibrary.util.RxLifecycleUtils
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.uber.autodispose.AutoDisposeConverter
import com.uber.autodispose.ObservableSubscribeProxy

import java.util.concurrent.TimeUnit

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * @author dlh
 * @email 18279727279@163.com
 * @date 2019/05/05 13:48
 * @des
 */

abstract class BaseActivity<P : IPresenter> : AppCompatActivity(), IActivity, LifecycleOwner {
    private var mPresenter: P? = null
    private var mPopupView: BasePopupView? = null
    private var mSubscribe: Disposable? = null

    /**
     * 获取布局ID
     *
     * @return 布局id
     */
    @get:MainThread
    @get:LayoutRes
    protected abstract val layoutId: Int

    /**
     * 获取 presenter
     *
     * @return presenter
     */
    protected abstract val presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
        setContentView(layoutId)
        mPresenter = presenter
        initLifecycleObserver(lifecycle)
        initView()
        initData()
    }

    protected fun <T> bindLifecycle(untilEvent: Lifecycle.Event?): AutoDisposeConverter<T> {
        return RxLifecycleUtils.bindLifecycle(this, untilEvent ?: Lifecycle.Event.ON_DESTROY)
    }

    @CallSuper
    @MainThread
    protected fun initLifecycleObserver(lifecycle: Lifecycle) {
        if (mPresenter != null) {
            mPresenter!!.setLifecycleOwner(this)
            lifecycle.addObserver(mPresenter!!)
        }
    }

    /**
     * 初始化布局
     */
    @MainThread
    protected abstract fun initView()

    /**
     * 初始化数据
     */
    @MainThread
    protected abstract fun initData()

    @MainThread
    override fun showLoading(tips: String) {
        if (mPopupView != null) {
            mPopupView!!.dismiss()
        }
        mPopupView = XPopup.Builder(this).asLoading(tips).show()
        if (mSubscribe != null) {
            mSubscribe!!.dispose()
            mSubscribe = null
        }
        mSubscribe = Observable.timer(TIMEOUT_TIME.toLong(), TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .`as`<ObservableSubscribeProxy<Long>>(bindLifecycle(Lifecycle.Event.ON_DESTROY))
                .subscribe {
                    if (mPopupView!!.isShow) {
                        hideLoading()
                    }
                }

    }

    @MainThread
    override fun hideLoading() {
        if (mSubscribe != null) {
            mSubscribe!!.dispose()
            mSubscribe = null
        }
        if (mPopupView != null && mPopupView!!.isShow) {
            mPopupView!!.dismiss()
        }
    }

    companion object {
        /**
         * 超时时间
         */
        private const val TIMEOUT_TIME = 10
    }
}
