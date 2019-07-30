package com.company.commonlib.network


import android.view.View
import androidx.fragment.app.Fragment
import com.company.commonlib.R
import com.company.commonlibrary.base.BaseFragment
import com.company.commonlibrary.base.BaseViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [HttpRequestFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HttpRequestFragment : BaseFragment<BaseViewModel>() {

    override val layoutRes: Int
        get() = R.layout.fragment_http_request


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
