package com.company.commonlib.contacts

import android.content.ContentProviderOperation
import android.content.Context
import android.net.Uri
import android.provider.ContactsContract
import android.util.Log
import java.util.*

/**
 * @author dinglaihong
 * @email 18279727279@163.com
 * @date 2019/7/29
 * @des
 */
class ContactBackupUtil(private val context: Context) {


    /**
     * 获取所有联系人
     *
     * @return 简要信息
     */
    val phones: List<ContactBean>
        get() {
            val phoneDtos = ArrayList<ContactBean>()
            val cr = context.contentResolver
            val cursor = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME), null, null, null)
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    val phoneDto = ContactBean(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)), cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)))
                    phoneDtos.add(phoneDto)
                }
                cursor.close()

            }
            return phoneDtos
        }

    /**
     * 获取联系人信息的Uri
     *获取ContentResolver
     *查询数据，返回Cursor
     *获取联系人的ID
     * 获取联系人的姓名
     *构造联系人信息
     *联系人ID
     * 查询电话类型的数据操作
     *添加Phone的信息
     *查询Email类型的数据操作
     *添加Email的信息
     *查询==地址==类型的数据操作.StructuredPostal.TYPE_WORK
     *添加Email的信息
     *查询==公司名字==类型的数据操作.Organization.COMPANY  ContactsContract.Data.CONTENT_URI
     *组织名 (公司名字)
     *职位
     * 查看所有的数据
     *有很多数据的时候，只会添加一条  例如邮箱，
     */

    val allContact: List<Map<String, Any>>
        get() {
            val contentResolver = context.contentResolver
            val cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null)
            val list = ArrayList<Map<String, Any>>()
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    val map = HashMap<String, Any>()
                    val sb = StringBuilder()
                    val contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                    val name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                    sb.append("contactId=").append(contactId).append(",Name=").append(name)
                    map["name"] = name
                    val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                    val phones = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null)
                    if (phones != null) {
                        while (phones.moveToNext()) {
                            val phoneNumber = phones.getString(phones.getColumnIndex(
                                    ContactsContract.CommonDataKinds.Phone.NUMBER))
                            sb.append(",Phone=").append(phoneNumber)
                            map["mobile"] = phoneNumber
                        }
                    }
                    phones!!.close()
                    val emails = contentResolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + contactId, null, null)
                    while (emails!!.moveToNext()) {
                        val emailAddress = emails.getString(emails.getColumnIndex(
                                ContactsContract.CommonDataKinds.Email.DATA))
                        sb.append(",Email=").append(emailAddress)
                        map["email"] = emailAddress


                    }
                    emails.close()
                    val address = contentResolver.query(ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.StructuredPostal.CONTACT_ID + " = " + contactId, null, null)
                    while (address!!.moveToNext()) {
                        val workAddress = address.getString(address.getColumnIndex(
                                ContactsContract.CommonDataKinds.StructuredPostal.DATA))
                        sb.append(",address").append(workAddress)
                        map["address"] = workAddress
                    }
                    address.close()
                    val orgWhere = ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?"
                    val orgWhereParams = arrayOf(id, ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE)
                    val orgCur = contentResolver.query(ContactsContract.Data.CONTENT_URI, null, orgWhere, orgWhereParams, null)
                    if (orgCur != null && orgCur.moveToFirst()) {
                        val company = orgCur.getString(orgCur.getColumnIndex(ContactsContract.CommonDataKinds.Organization.DATA))
                        val title = orgCur.getString(orgCur.getColumnIndex(ContactsContract.CommonDataKinds.Organization.TITLE))
                        sb.append(",company").append(company)
                        sb.append(",title").append(title)
                        map["company"] = company
                        map["title"] = title
                    }
                    orgCur!!.close()
                    list.add(map)
                    Log.i("=========orgName=====", sb.toString())
                    Log.e("=========map=====", map.toString())
                }
            }

            Log.i("=========list=====", list.toString())
            cursor!!.close()
            return list
        }

    /**
     * 添加和修改联系人
     *
     * @throws Exception
     */
    @Throws(Exception::class)
    fun writeContacts(response: ContactBackupResponse?) {
        val operations = ArrayList<ContentProviderOperation>()

        val operation = ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build()
        operations.add(operation)
        response?.apply {

            contacts?.forEach { it ->

                it.let {
                    //添加IM信息
                    addIMAddresses(it.instantMessageAddresses, operations)
                    //添加电话信息
                    addPhoneNumbers(it.phoneNumbers, operations)
                    //添加联系人信息STRUCTUREDNAME
                    addStructuredName(it, operations)
                    //添加联系人信息STRUCTUREDNAME
//                    addImageInfo(it.imageData, operations)
                    //添加地址信息
                    addPostalAddresses(it.postalAddresses, operations)
                    //添加网站信息
                    addUrlAddresses(it.urlAddresses, operations)
                    //添加邮箱信息
                    addEmailAddresses(it.emailAddresses, operations)
                    //添加昵称信息
                    addNicknameInfo(it.nickname, operations)
                    //添加Note信息
                    addNoteInfo(it.note, operations)
                    //添加Organization信息
                    addOrganizationInfo(it, operations)
                    //添加Relations信息
                    addRelations(it.contactRelations, operations)
                    //添加Birthday信息
                    addBirthday(it.birthday, it.nonGregorianBirthday, operations)
                    //添加Dates信息
                    addDates(it.dates, operations)
                    //添加Groups信息
                    addGroups(it.groups, operations)
                    //添加社交信息
                    addSocialProfiles(it.socialProfiles, operations)
                }
            }
        }


        /* //添加网络通话信息
         operation = ContentProviderOperation.newInsert(DATA_URI)
                 .withValueBackReference(RAW_CONTACT_ID, 0)
                 .withValue(MIME_TYPE, SIPADDRESS_ITEM_TYPE)
                 .withValue(SIPADDRESS_TYPE, ContactsContract.CommonDataKinds.SipAddress.TYPE_WORK)
                 .withValue(SIPADDRESS_DATA, "18279727279.com")
                 .build()
         operations.add(operation)*/

        val resolver = context.contentResolver
        //批量执行,返回执行结果集
        val results = resolver.applyBatch(ContactsContract.AUTHORITY, operations)
        for (result in results) {
            Log.i(TAG, result.uri.toString())
        }

    }

    private fun addImageInfo(imageData: String?, operations: ArrayList<ContentProviderOperation>) {
        imageData?.let {
            val operation: ContentProviderOperation = ContentProviderOperation.newInsert(DATA_URI)
                    .withValueBackReference(RAW_CONTACT_ID, 0)
                    .withValue(MIME_TYPE, ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.Photo.PHOTO, it)
                    .build()
            operations.add(operation)
        }
    }

    private fun addSocialProfiles(socialProfiles: List<ContactBackupResponse.ContactsBean.SocialProfilesBean>?, operations: ArrayList<ContentProviderOperation>) {
        socialProfiles?.forEach {
            it.label?.apply {
                val operation: ContentProviderOperation = ContentProviderOperation.newInsert(DATA_URI)
                        .withValueBackReference(RAW_CONTACT_ID, 0)
                        .withValue(MIME_TYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE)
                        .withValue(ContactsContract.CommonDataKinds.Im.TYPE, ContactsContract.CommonDataKinds.Im.TYPE_WORK)
                        .withValue(ContactsContract.CommonDataKinds.Im.PROTOCOL, ContactsContract.CommonDataKinds.Im.PROTOCOL_CUSTOM)
                        .withValue(ContactsContract.CommonDataKinds.Im.DATA, it.social?.urlString)
                        .withValue(ContactsContract.CommonDataKinds.Im.CUSTOM_PROTOCOL, it.displayLabel)
                        .withValue(ContactsContract.CommonDataKinds.Im.LABEL, it.displayLabel)
                        .build()
                operations.add(operation)
            }
        }
    }

    private fun addGroups(groups: List<ContactBackupResponse.ContactsBean.GroupsBean>?, operations: ArrayList<ContentProviderOperation>) {
        groups?.forEach {
            it.label?.apply {
                val operation: ContentProviderOperation = ContentProviderOperation.newInsert(DATA_URI)
                        .withValueBackReference(RAW_CONTACT_ID, 0)
                        .withValue(MIME_TYPE, ContactsContract.CommonDataKinds.GroupMembership.CONTENT_ITEM_TYPE)
                        .withValue(ContactsContract.CommonDataKinds.GroupMembership.GROUP_ROW_ID, it.name)
                        .build()
                operations.add(operation)
            }
        }
    }

    private fun addDates(dates: List<ContactBackupResponse.ContactsBean.DatesBean>?, operations: ArrayList<ContentProviderOperation>) {
        dates?.forEach {
            it.label?.apply {
                val operation: ContentProviderOperation = ContentProviderOperation.newInsert(DATA_URI)
                        .withValueBackReference(RAW_CONTACT_ID, 0)
                        .withValue(MIME_TYPE, ContactsContract.CommonDataKinds.Event.CONTENT_ITEM_TYPE)
                        .withValue(ContactsContract.CommonDataKinds.Event.TYPE, ContactsConstants.getEventType(this))
                        .withValue(ContactsContract.CommonDataKinds.Event.START_DATE, "" + (it.date?.year
                                ?: 0) + "/" + (it.date?.month ?: 0) + "/" + (it.date?.day ?: 0))
                        .withValue(ContactsContract.CommonDataKinds.Event.LABEL, it.displayLabel)
                        .build()
                operations.add(operation)
            }
        }
    }

    private fun addBirthday(birthday: ContactBackupResponse.ContactsBean.BirthdayBean?, nonGregorianBirthday: ContactBackupResponse.ContactsBean.NonGregorianBirthdayBean?, operations: ArrayList<ContentProviderOperation>) {
        //Event TYPE_BIRTHDAY
        birthday?.let {
            val operation: ContentProviderOperation = ContentProviderOperation.newInsert(DATA_URI)
                    .withValueBackReference(RAW_CONTACT_ID, 0)
                    .withValue(MIME_TYPE, ContactsContract.CommonDataKinds.Event.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.Event.TYPE, ContactsContract.CommonDataKinds.Event.TYPE_BIRTHDAY)
                    .withValue(ContactsContract.CommonDataKinds.Event.START_DATE, "" + it.month + "/" + it.day)
                    .build()
            operations.add(operation)
        }
        nonGregorianBirthday?.let {
            val operation: ContentProviderOperation = ContentProviderOperation.newInsert(DATA_URI)
                    .withValueBackReference(RAW_CONTACT_ID, 0)
                    .withValue(MIME_TYPE, ContactsContract.CommonDataKinds.Event.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.Event.TYPE, ContactsContract.CommonDataKinds.Event.TYPE_CUSTOM)
                    .withValue(ContactsContract.CommonDataKinds.Event.START_DATE, "" + it.year + "/" + it.month + "/" + it.day)
                    .withValue(ContactsContract.CommonDataKinds.Event.LABEL, "农历生日")
                    .build()
            operations.add(operation)
        }

    }

    private fun addRelations(contactRelations: List<ContactBackupResponse.ContactsBean.ContactRelationsBean>?, operations: ArrayList<ContentProviderOperation>) {
        contactRelations?.forEach {
            it.label?.apply {
                val operation: ContentProviderOperation = ContentProviderOperation.newInsert(DATA_URI)
                        .withValueBackReference(RAW_CONTACT_ID, 0)
                        .withValue(MIME_TYPE, ContactsContract.CommonDataKinds.Relation.CONTENT_ITEM_TYPE)
                        .withValue(ContactsContract.CommonDataKinds.Relation.TYPE, ContactsConstants.getRelationType(this))
                        .withValue(ContactsContract.CommonDataKinds.Relation.DATA, it.relation?.name)
                        .withValue(ContactsContract.CommonDataKinds.Relation.LABEL, it.displayLabel)
                        .build()
                operations.add(operation)
            }
        }
    }

    private fun addOrganizationInfo(it: ContactBackupResponse.ContactsBean, operations: ArrayList<ContentProviderOperation>) {
        val operation: ContentProviderOperation = ContentProviderOperation.newInsert(DATA_URI)
                .withValueBackReference(RAW_CONTACT_ID, 0)
                .withValue(MIME_TYPE, ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Organization.TYPE, ContactsContract.CommonDataKinds.Organization.TYPE_OTHER)
                .withValue(ContactsContract.CommonDataKinds.Organization.COMPANY, it.organizationName)
                .withValue(ContactsContract.CommonDataKinds.Organization.DEPARTMENT, it.departmentName)
                .withValue(ContactsContract.CommonDataKinds.Organization.TITLE, it.jobTitle)
                .withValue(ContactsContract.CommonDataKinds.Organization.SYMBOL, "")
                .withValue(ContactsContract.CommonDataKinds.Organization.JOB_DESCRIPTION, "")
                .withValue(ContactsContract.CommonDataKinds.Organization.OFFICE_LOCATION, "")
                .withValue(ContactsContract.CommonDataKinds.Organization.PHONETIC_NAME, it.phoneticOrganizationName)
                .build()
        operations.add(operation)
    }

    private fun addEmailAddresses(emailAddresses: List<ContactBackupResponse.ContactsBean.EmailAddressesBean>?, operations: ArrayList<ContentProviderOperation>) {
        emailAddresses?.forEach {
            it.label?.apply {
                val operation: ContentProviderOperation = ContentProviderOperation.newInsert(DATA_URI)
                        .withValueBackReference(RAW_CONTACT_ID, 0)
                        .withValue(MIME_TYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                        .withValue(ContactsContract.CommonDataKinds.Email.TYPE, ContactsConstants.getEmailType(this))
                        .withValue(ContactsContract.CommonDataKinds.Email.ADDRESS, it.email)
                        .withValue(ContactsContract.CommonDataKinds.Email.LABEL, it.displayLabel)
                        .build()
                operations.add(operation)
            }
        }
    }

    private fun addNoteInfo(note: String?, operations: ArrayList<ContentProviderOperation>) {
        note?.let {
            val operation: ContentProviderOperation = ContentProviderOperation.newInsert(DATA_URI)
                    .withValueBackReference(RAW_CONTACT_ID, 0)
                    .withValue(MIME_TYPE, ContactsContract.CommonDataKinds.Note.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.Note.NOTE, it)
                    .build()
            operations.add(operation)
        }
    }

    private fun addNicknameInfo(nickname: String?, operations: ArrayList<ContentProviderOperation>) {
        nickname?.let {
            val operation: ContentProviderOperation = ContentProviderOperation.newInsert(DATA_URI)
                    .withValueBackReference(RAW_CONTACT_ID, 0)
                    .withValue(MIME_TYPE, ContactsContract.CommonDataKinds.Nickname.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.Nickname.NAME, it)
                    .build()
            operations.add(operation)
        }
    }

    private fun addUrlAddresses(urlAddresses: List<ContactBackupResponse.ContactsBean.UrlAddressesBean>?, operations: ArrayList<ContentProviderOperation>) {
        urlAddresses?.forEach {
            it.label?.apply {
                val operation: ContentProviderOperation = ContentProviderOperation.newInsert(DATA_URI)
                        .withValueBackReference(RAW_CONTACT_ID, 0)
                        .withValue(MIME_TYPE, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE)
                        .withValue(ContactsContract.CommonDataKinds.Website.TYPE, ContactsConstants.getUrlType(this))
                        .withValue(ContactsContract.CommonDataKinds.Website.URL, it.url)
                        .withValue(ContactsContract.CommonDataKinds.Website.LABEL, it.displayLabel)
                        .build()
                operations.add(operation)
            }
        }
    }

    private fun addPostalAddresses(postalAddresses: List<ContactBackupResponse.ContactsBean.PostalAddressesBean>?, operations: ArrayList<ContentProviderOperation>) {
        postalAddresses?.forEach {
            it.address?.apply {
                //添加住宅地址信息
                val operation = ContentProviderOperation.newInsert(DATA_URI)
                        .withValueBackReference(RAW_CONTACT_ID, 0)
                        .withValue(MIME_TYPE, ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE)
                        .withValue(ContactsContract.CommonDataKinds.StructuredPostal.TYPE, ContactsConstants.getPostalAddresseType(it.label))
                        .withValue(ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY, this.country)
                        .withValue(ContactsContract.CommonDataKinds.StructuredPostal.CITY, this.city)
                        .withValue(ContactsContract.CommonDataKinds.StructuredPostal.REGION, this.subLocality)
                        .withValue(ContactsContract.CommonDataKinds.StructuredPostal.STREET, this.street)
                        .withValue(ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE, this.postalCode)
                        .withValue(ContactsContract.CommonDataKinds.StructuredPostal.FORMATTED_ADDRESS, this.country + this.state + this.city + this.subLocality + this.street)
                        .build()
                operations.add(operation)
            }
        }
    }

    private fun addStructuredName(it: ContactBackupResponse.ContactsBean, operations: ArrayList<ContentProviderOperation>) {
        val operation: ContentProviderOperation = ContentProviderOperation.newInsert(DATA_URI)
                .withValueBackReference(RAW_CONTACT_ID, 0)
                .withValue(MIME_TYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, it.familyName + it.givenName)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME, it.familyName)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.PHONETIC_FAMILY_NAME, it.phoneticFamilyName)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.MIDDLE_NAME, it.middleName)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.PHONETIC_MIDDLE_NAME, it.phoneticMiddleName)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, it.givenName)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.PHONETIC_GIVEN_NAME, it.phoneticGivenName)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.FULL_NAME_STYLE, ContactsContract.FullNameStyle.CHINESE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.PHONETIC_NAME_STYLE, ContactsContract.PhoneticNameStyle.PINYIN)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.PREFIX, it.namePrefix)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.SUFFIX, it.nameSuffix)
                .build()
        operations.add(operation)
    }

    /**
     * 添加电话信息
     */
    private fun addPhoneNumbers(it: List<ContactBackupResponse.ContactsBean.PhoneNumbersBean>?, operations: ArrayList<ContentProviderOperation>) {
        it?.forEach {
            it.label?.apply {
                val operation: ContentProviderOperation = ContentProviderOperation.newInsert(DATA_URI)
                        .withValueBackReference(RAW_CONTACT_ID, 0)
                        .withValue(MIME_TYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                        .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsConstants.getPhoneType(this))
                        .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, it.phoneNumber?.number)
                        .withValue(ContactsContract.CommonDataKinds.Phone.LABEL, it.displayLabel)
                        .build()
                operations.add(operation)
            }
        }
    }

    /**
     * 添加IM信息
     */
    private fun addIMAddresses(it: List<ContactBackupResponse.ContactsBean.InstantMessageAddressesBean>?, operations: ArrayList<ContentProviderOperation>) {
        it?.forEach {
            it.label?.apply {
                val operation: ContentProviderOperation = ContentProviderOperation.newInsert(DATA_URI)
                        .withValueBackReference(RAW_CONTACT_ID, 0)
                        .withValue(MIME_TYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE)
                        .withValue(ContactsContract.CommonDataKinds.Im.TYPE, ContactsContract.CommonDataKinds.Im.TYPE_WORK)
                        .withValue(ContactsContract.CommonDataKinds.Im.PROTOCOL, ContactsConstants.getImProtocol(this))
                        .withValue(ContactsContract.CommonDataKinds.Im.DATA, it.service?.username)
                        .withValue(ContactsContract.CommonDataKinds.Im.CUSTOM_PROTOCOL, it.displayLabel)
                        .withValue(ContactsContract.CommonDataKinds.Im.LABEL, it.displayLabel)
                        .build()
                operations.add(operation)
            }
        }
    }

    companion object {
        val TAG: String = this::class.java.simpleName
        //[content://com.android.contacts/data]
        val DATA_URI: Uri = ContactsContract.Data.CONTENT_URI

        const val RAW_CONTACT_ID = ContactsContract.Data.RAW_CONTACT_ID
        const val MIME_TYPE: String = ContactsContract.Data.MIMETYPE

    }

}
