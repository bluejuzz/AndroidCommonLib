package com.company.commonlib.contacts

import android.content.res.AssetManager
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.Utils
import com.company.commonlib.contacts.java.ContactsBackupBean
import com.company.commonlib.contacts.java.ContactsUtils
import com.company.commonlib.contacts.kotlin.ContactBackupResponse
import com.company.commonlib.contacts.kotlin.ContactBackupUtils
import com.company.commonlibrary.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.InputStream
import kotlin.concurrent.thread

/**
 * @author dinglaihong
 * @email 18279727279@163.com
 * @date 2019/7/29
 * @des
 */
class ContactsModel : BaseViewModel() {
    private lateinit var contacts: MutableLiveData<List<ContactBean>>
    private lateinit var writeContactsComplete: MutableLiveData<Boolean>

    fun readContacts(): MutableLiveData<List<ContactBean>> {
        if (!::contacts.isInitialized) contacts = MutableLiveData()

        /*thread {
            val phones: MutableList<ContactBean> = mutableListOf()
            val response = ContactsUtils.readContacts()
            response?.contacts?.forEach { contact ->
                val phoneNumbers: List<ContactsBackupBean.ContactsBean.PhoneNumbersBean>? = contact.phoneNumbers
                val name: String? = contact.displayName
                val avatar: String? = contact.imageData
                val number: String? = if (!phoneNumbers.isNullOrEmpty()) contact.phoneNumbers?.get(0)?.phoneNumber?.number else ""
                val contactBean: ContactBean = when {
                    name.isNullOrEmpty() && number.isNullOrEmpty() -> ContactBean(avatar = avatar)
                    name.isNullOrEmpty() -> ContactBean(avatar = avatar, telPhone = number)
                    number.isNullOrEmpty() -> ContactBean(avatar = avatar, name = name)
                    else -> ContactBean(avatar = avatar, name = name, telPhone = number)
                }
                phones.add(contactBean)
            }
            contacts.postValue(phones)
        }.start()*/

        GlobalScope.launch(Dispatchers.IO) {
            val deferred = GlobalScope.async {
                return@async ContactBackupUtils(Utils.getApp()).readContacts()
            }
            val response: ContactBackupResponse? = deferred.await()
            val phones: MutableList<ContactBean> = mutableListOf()
            response?.contacts?.forEach { contact ->
                val phoneNumbers: List<ContactBackupResponse.ContactsBean.PhoneNumbersBean>? = contact.phoneNumbers
                val name: String? = contact.displayName
                val avatar: String? = contact.imageData
                val number: String? = if (!phoneNumbers.isNullOrEmpty()) contact.phoneNumbers?.get(0)?.phoneNumber?.number else ""
                val contactBean: ContactBean = when {
                    name.isNullOrEmpty() && number.isNullOrEmpty() -> ContactBean(avatar = avatar)
                    name.isNullOrEmpty() -> ContactBean(avatar = avatar, telPhone = number)
                    number.isNullOrEmpty() -> ContactBean(avatar = avatar, name = name)
                    else -> ContactBean(avatar = avatar, name = name, telPhone = number)
                }
                phones.add(contactBean)
            }
            contacts.postValue(phones)
        }
        return contacts
    }

    fun writeContacts(): MutableLiveData<Boolean> {

        if (!::writeContactsComplete.isInitialized) writeContactsComplete = MutableLiveData()
        /*GlobalScope.launch(Dispatchers.IO) {
            val deferred: Deferred<Boolean> = GlobalScope.async {
                //获取assets资源管理器
                val assetManager: AssetManager? = Utils.getApp().assets
                //通过管理器打开文件并读取
                val inputStream: InputStream? = assetManager?.open("contacts_response.json")
                val response: ContactBackupResponse? = GsonUtils.fromJson(inputStream?.reader(), ContactBackupResponse::class.java)
                return@async ContactBackupUtils(Utils.getApp()).writeContacts(response)
            }
            writeContactsComplete.postValue(deferred.await())
        }*/
        thread {
            val assetManager: AssetManager? = Utils.getApp().assets
            //通过管理器打开文件并读取
            val inputStream: InputStream? = assetManager?.open("contacts_response.json")
            val response: ContactsBackupBean? = GsonUtils.fromJson(inputStream?.reader(), ContactsBackupBean::class.java)
            val b = ContactsUtils.writeContacts(response)
            writeContactsComplete.postValue(b)
        }.start()
        return this.writeContactsComplete
    }

}