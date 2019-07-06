package com.company.commonlibrary.base

import androidx.lifecycle.Lifecycle

import android.os.Bundle

import androidx.annotation.CallSuper
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

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

abstract class BaseFragment<P : IPresenter> : Fragment(), IFragment {
    protected var mRootView: View? = null

    protected var mPresenter: P? = null
    private var mPopupView: BasePopupView? = null
    private var mSubscribe: Disposable? = null

    protected abstract val layoutRes: Int

    protected abstract val presenter: P

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = LayoutInflater.from(context).inflate(layoutRes, container, false)
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLifecycleObserver(lifecycle)
        mPresenter = presenter
        initView(view)
        initData()
    }

    override fun onDestroy() {
        this.mRootView = null
        super.onDestroy()
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

    protected abstract fun initView(view: View)

    protected abstract fun initData()

    @MainThread
    override fun showLoading(tips: String) {
        if (mPopupView != null) {
            mPopupView!!.dismiss()
        }
        mPopupView = XPopup.Builder(context).asLoading(tips).show()
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

    @MainThread
    override fun showMessage(msg: String) {

    }

    companion object {

        private const val TIMEOUT_TIME = 10
    }
}