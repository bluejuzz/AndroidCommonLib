package com.company.commonlib.network


import androidx.fragment.app.Fragment

import android.view.View
import com.company.commonlib.R

import com.company.commonlibrary.base.BaseFragment
import com.company.commonlibrary.base.BaseModel
import com.company.commonlibrary.base.BasePresenter
import com.company.commonlibrary.base.IModel
import com.company.commonlibrary.base.IPresenter
import com.company.commonlibrary.base.IView


/**
 * A simple [Fragment] subclass.
 * Use the [HttpRequestFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HttpRequestFragment : BaseFragment<IPresenter>() {

    override val layoutRes: Int
        get() = R.layout.fragment_http_request

    override val presenter: IPresenter
        get() = BasePresenter<IView, IModel>(this, BaseModel())

    override fun initView(view: View) {

    }

    override fun initData() {

    }

    companion object {


        fun newInstance(): HttpRequestFragment {
            return HttpRequestFragment()
        }
    }

}
