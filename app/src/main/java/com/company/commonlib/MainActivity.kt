package com.company.commonlib

import android.annotation.SuppressLint
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.blankj.utilcode.util.ToastUtils
import com.company.commonlib.html.HtmlTestActivity
import com.company.commonlib.network.*
import com.company.commonlibrary.base.*
import com.company.commonlibrary.bean.RequestEntity
import com.company.commonlibrary.retrofit.BaseHttpModel
import com.company.commonlibrary.retrofit.CommonException
import com.company.commonlibrary.util.NetworkChangeUtils
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author dinglaihong
 * @email 18279727279@163.com
 * @date 2019/7/6
 * @des
 */
class MainActivity : BaseActivity<IPresenter>() {

    override val layoutId: Int
        get() = R.layout.activity_main

    override val presenter: IPresenter
        get() = BasePresenter<IView, IModel>(this, BaseModel())

    @SuppressLint("AutoDispose")
    override fun initView() {
//        NetworkChangeUtils.registerListener(changeListener = NetworkChangeUtils.NetworkChangeListener())
        findViewById<View>(R.id.test_http).setOnClickListener {
            showLoading("加载中")
            val requestBody = BaseHttpModel.instance.getRequestBody(RequestEntity<Map<String, Any>>(hashMapOf("phone" to 18279727279L)))
            val post = BaseHttpModel.instance.post("https://http.aismono.net/mono-biz-app/educationclass/getCardList", requestBody)
            post.observe(this, object : HttpObserver<AismonoResponse<List<MyCardData.CardBean>>>() {
                override fun onSuccess(response: AismonoResponse<List<MyCardData.CardBean>>) {
                    val aismonoResponse = response
                }

                override fun onFailed(e: CommonException) {
                    super.onFailed(e)
                }

                override fun onFinish() {
                    hideLoading()
                }

            })

            val liveData = BaseHttpModel.instance.get("https://www.wanandroid.com/banner/json")
            liveData.observe(this, object : HttpObserver<WanResponse<List<BannerData>>>() {
                override fun onSuccess(response: WanResponse<List<BannerData>>) {
                    val wanResponse = response
                }

                override fun onFailed(e: CommonException) {
                    super.onFailed(e)
                }

                override fun onFinish() {
                    hideLoading()
                }

            })
        }
        test_camerax.setOnClickListener {
            HtmlTestActivity.starter(this)
        }
    }

    override fun initData() {

    }

    override fun onDestroy() {
        super.onDestroy()
        NetworkChangeUtils.unRegisterListener()
    }
}

private fun <T> LiveData<T>.observe(mainActivity: MainActivity, observer: Observer<AismonoResponse<List<MyCardData.CardBean>>>) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

