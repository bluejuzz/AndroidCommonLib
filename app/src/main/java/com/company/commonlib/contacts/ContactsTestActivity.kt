package com.company.commonlib.contacts

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.util.Base64
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ImageUtils
import com.blankj.utilcode.util.ToastUtils
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.company.commonlib.R
import com.company.commonlibrary.base.BaseActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_contacts_test.*
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

class ContactsTestActivity : BaseActivity<ContactsModel>() {
    private var adapter: BaseQuickAdapter<ContactBean, BaseViewHolder> = object : BaseQuickAdapter<ContactBean, BaseViewHolder>(R.layout.item_contacts, null) {

        override fun convert(helper: BaseViewHolder, item: ContactBean) {
            helper.setText(R.id.contact_name, item.name + " : " + item.telPhone)
            val imageView = helper.getView<ImageView>(R.id.contact_avatar)
            try {
                val decode = Base64.decode(item.avatar, Base64.DEFAULT)
                Glide.with(this@ContactsTestActivity).load(decode).error(R.mipmap.avatar_def).into(imageView)
            } catch (e: Exception) {
                Glide.with(this@ContactsTestActivity).load(R.mipmap.avatar_def).error(R.mipmap.avatar_def).into(imageView)
            }
        }
    }
    override val layoutId = R.layout.activity_contacts_test

    override fun initView() {
        contact_w.setOnClickListener {
            showLoading("正在写入联系人")
            mViewModel.writeContacts().observe(this@ContactsTestActivity, Observer {
                hideLoading()
            })
        }
        contact_r.setOnClickListener {
            showLoading("正在读取联系人")
            mViewModel.readContacts().observe(this@ContactsTestActivity, Observer {
                hideLoading()
                adapter.setNewData(it)
            })
        }
        rcv_contacts.layoutManager = LinearLayoutManager(this)
        rcv_contacts.adapter = adapter
    }

    @SuppressLint("AutoDispose", "CheckResult")
    override fun initData() {

        RxPermissions(this)
                //读写通讯录权限
                .request(android.Manifest.permission.READ_CONTACTS, android.Manifest.permission.WRITE_CONTACTS)
                .safeSubscribe(object : Subscriber<Boolean>, io.reactivex.Observer<Boolean> {
                    override fun onNext(t: Boolean) {
                        if (t) {

                        } else {
                            ToastUtils.showShort("拒绝了联系人权限")
                            finish()
                        }
                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onSubscribe(s: Subscription) {

                    }

                    override fun onError(t: Throwable) {
                        t.printStackTrace()
                    }

                    override fun onComplete() {

                    }
                })

    }

    companion object {
        fun starter(context: Context) {
            context.startActivity(Intent(context, ContactsTestActivity::class.java))
        }
    }
}
