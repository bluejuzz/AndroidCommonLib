package com.company.commonlib

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.company.commonlib.html.HtmlTestActivity
import com.company.commonlib.network.AismonoResponse
import com.company.commonlib.network.BannerData
import com.company.commonlib.network.MyCardData
import com.company.commonlib.network.WanResponse
import com.company.commonlibrary.base.*
import com.company.commonlibrary.bean.RequestEntity
import com.company.commonlibrary.retrofit.BaseHttpModel
import com.company.commonlibrary.retrofit.HttpObserver
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

    override fun initView() {
//        NetworkChangeUtils.registerListener(changeListener = NetworkChangeUtils.NetworkChangeListener())
        findViewById<View>(R.id.test_http).setOnClickListener {
            showLoading("加载中")
            val requestBody = BaseHttpModel.instance.getRequestBody(RequestEntity<Map<String, Any>>(hashMapOf("phone" to 18279727279L)))
            val post = BaseHttpModel.instance.post("https://http.aismono.net/mono-biz-app/educationclass/getCardList", requestBody)
            post.observe(this, object : HttpObserver<AismonoResponse<List<MyCardData.CardBean>>>() {
                override fun onSuccess(response: AismonoResponse<List<MyCardData.CardBean>>) {
                }

                override fun onFinish() {
                    hideLoading()
                }

            })

            val liveData = BaseHttpModel.instance.get("https://www.wanandroid.com/banner/json")
            liveData.observe(this, object : HttpObserver<WanResponse<List<BannerData>>>() {
                override fun onSuccess(response: WanResponse<List<BannerData>>) {
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

