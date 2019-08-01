package com.company.commonlibrary.base

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProviders
import com.company.commonlibrary.util.RxLifecycleUtils
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.uber.autodispose.AutoDisposeConverter
import com.uber.autodispose.ObservableSubscribeProxy
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.reflect.ParameterizedType
import java.util.concurrent.TimeUnit

/**
 * @author dlh
 * @email 18279727279@163.com
 * @date 2019/05/05 13:48
 * @des
 */

abstract class BaseActivity<M : BaseViewModel> : AppCompatActivity(), IActivity {
    protected lateinit var mViewModel: M
    private var mPopupView: BasePopupView? = null
    private var mSubscribe: Disposable? = null
    private var viewModelClass: Class<M>? = null
    /**
     * 获取布局ID
     *
     * @return 布局id
     */
    @get:MainThread
    @get:LayoutRes
    protected abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
        setContentView(layoutId)
        viewModelClass = getT1(this, 0)
        if (viewModelClass != null) {
            mViewModel = ViewModelProviders.of(this).get(viewModelClass!!)
        }
        initView()
        initData()
    }

    protected fun <T> bindLifecycle(untilEvent: Lifecycle.Event?): AutoDisposeConverter<T> {
        return RxLifecycleUtils.bindLifecycle(this, untilEvent ?: Lifecycle.Event.ON_DESTROY)
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
        if (mPopupView != null) {
            mPopupView!!.dismiss()
        }
    }

    private fun getT1(o: Any, i: Int): Class<M>? {
        try {
            val genType = o.javaClass.genericSuperclass
            val params = (genType as ParameterizedType).actualTypeArguments
            return params[i] as Class<M>
        } catch (e: ClassCastException) {
        }
        return null
    }

    companion object {
        /**
         * 超时时间
         */
        private const val TIMEOUT_TIME = 10
    }
}
