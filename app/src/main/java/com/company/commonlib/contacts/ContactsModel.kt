package com.company.commonlib.contacts

import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.Utils
import com.company.commonlibrary.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * @author dinglaihong
 * @email 18279727279@163.com
 * @date 2019/7/29
 * @des
 */
class ContactsModel : BaseViewModel() {
    private lateinit var contacts: MutableLiveData<List<ContactBean>>
    private lateinit var writeContactsComplete: MutableLiveData<Boolean>

    fun getContacts(): MutableLiveData<List<ContactBean>> {
        if (!::contacts.isInitialized) {
            contacts = MutableLiveData()
        }
        loadUsers()
        return contacts
    }

    fun writeContacts(): MutableLiveData<Boolean> {

        if (!::writeContactsComplete.isInitialized) writeContactsComplete = MutableLiveData()
        GlobalScope.launch(Dispatchers.IO) {
            val deferred = GlobalScope.async {
                //获取assets资源管理器
                val assetManager = Utils.getApp().assets
                //通过管理器打开文件并读取
                val inputStream = assetManager.open("contacts_response.json")
                val response = GsonUtils.fromJson(inputStream.reader(), ContactBackupResponse::class.java)
                return@async ContactBackupUtils(Utils.getApp()).writeContacts(response)
            }
            writeContactsComplete.postValue(deferred.await())
        }
        return this.writeContactsComplete
    }

    private fun loadUsers() {
        val phones: MutableList<ContactBean> = mutableListOf()
        GlobalScope.launch(Dispatchers.IO) {
            val deferred = GlobalScope.async {
                return@async ContactBackupUtils(Utils.getApp()).readContacts()
            }
            val response: ContactBackupResponse? = deferred.await()
            response?.contacts?.forEach { contact ->
                val phoneNumbers: List<ContactBackupResponse.ContactsBean.PhoneNumbersBean>? = contact.phoneNumbers
                val name: String = ((if (contact.displayName.isNullOrEmpty()) "（无姓名）" else {
                    contact.displayName
                }) as String)
                val avatar: String? = contact.imageData
                var number = ""
                if (!with(phoneNumbers) { isNullOrEmpty() }) {
                    val phoneNumbersBean: ContactBackupResponse.ContactsBean.PhoneNumbersBean? = contact.phoneNumbers?.get(0)

                    phoneNumbersBean?.let {
                        number = it.phoneNumber?.number.toString()
                    }
                }
                val contactBean: ContactBean = when {
                    name.isEmpty() -> ContactBean(avatar = avatar, telPhone = number)
                    number.isEmpty() -> ContactBean(avatar = avatar, name = name)
                    else -> ContactBean(avatar = avatar, name = name, telPhone = number)
                }
                phones.add(contactBean)
            }
            contacts.postValue(phones)
        }
    }
}