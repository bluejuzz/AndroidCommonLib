package com.company.commonlib.contacts

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.company.commonlib.R
import com.company.commonlibrary.base.BaseActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_contacts_test.*

class ContactsTestActivity : BaseActivity<ContactsModel>() {

    override val layoutId = R.layout.activity_contacts_test

    override fun initView() {
        rcv_contacts.layoutManager = LinearLayoutManager(this)
    }

    @SuppressLint("AutoDispose", "CheckResult")
    override fun initData() {

        RxPermissions(this)
                //读写通讯录权限
                .request(android.Manifest.permission.READ_CONTACTS, android.Manifest.permission.WRITE_CONTACTS)
                .subscribe { it ->
                    if (it) {
                        mViewModel.getContacts().observe(this@ContactsTestActivity, Observer {
                            val adapter = object : BaseQuickAdapter<ContactBean, BaseViewHolder>(R.layout.item_contacts, it) {

                                override fun convert(helper: BaseViewHolder, item: ContactBean) {
                                    helper.setText(R.id.contact_name, item.name + "   " + item.telPhone)
                                }
                            }
                            rcv_contacts.adapter = adapter
                        })

                    } else {
                        ToastUtils.showShort("拒绝了联系人权限")
                    }
                }
    }

    companion object {
        fun starter(context: Context) {
            context.startActivity(Intent(context, ContactsTestActivity::class.java))
        }
    }
}
