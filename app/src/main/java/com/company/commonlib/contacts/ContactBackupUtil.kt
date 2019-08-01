package com.company.commonlib.contacts

import android.content.ContentProviderOperation
import android.content.Context
import android.net.Uri
import android.provider.ContactsContract
import android.util.Log
import java.util.*
import kotlin.concurrent.thread
import android.provider.ContactsContract.CommonDataKinds.*


/**
 * @author dinglaihong
 * @email 18279727279@163.com
 * @date 2019/7/29
 * @des
 */
class ContactBackupUtil(private val context: Context) {

    private var operations: ArrayList<ContentProviderOperation> = ArrayList()
    // 有了它才能给真正的实现批量添加
    private var rawContactInsertIndex: Int = 0

    /**
     * 获取所有联系人
     *
     * @return 简要信息
     */
    val phones: List<ContactBean>
        get() {
            val phoneDtos = ArrayList<ContactBean>()
            val cr = context.contentResolver
            val cursor = cr.query(Phone.CONTENT_URI, arrayOf(Phone.NUMBER, Phone.DISPLAY_NAME), null, null, null)
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    val phoneDto = ContactBean(cursor.getString(cursor.getColumnIndex(Phone.NUMBER)), cursor.getString(cursor.getColumnIndex(Phone.DISPLAY_NAME)))
                    phoneDtos.add(phoneDto)
                }
                cursor.close()

            }
            return phoneDtos
        }
    /**
     *
     */
    val readContacts: ContactBackupResponse?
        get() {
            val contentResolver = context.contentResolver
            //读取通讯录的全部的联系人
            //需要先在raw_contact表中遍历id，并根据id到data表中获取数据
            val response = ContactBackupResponse()
            response.name = ""
            response.identifier = ""
            val contacts: MutableList<ContactBackupResponse.ContactsBean> = mutableListOf()
            //获取联系人信息的Uri
            val uri = ContactsContract.Contacts.CONTENT_URI
            //获取ContentResolver
            //查询数据，返回Cursor
            val cursor = contentResolver.query(uri, null, null, null, null) ?: return null
            while (cursor.moveToNext()) {
                val contactsBean = ContactBackupResponse.ContactsBean()
                //获取联系人的ID
                val contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                //获取联系人的姓名
                val name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                //构造联系人信息


                //查询电话类型的数据操作
                val phones = contentResolver.query(Phone.CONTENT_URI, null,
                        Phone.CONTACT_ID + " = " + contactId, null, null)
                phones?.apply {
                    val phoneNumbers: MutableList<ContactBackupResponse.ContactsBean.PhoneNumbersBean> = mutableListOf()
                    while (this.moveToNext()) {
                        val numbersBean = ContactBackupResponse.ContactsBean.PhoneNumbersBean()
                        //类型
                        val phoneType = this.getInt(this.getColumnIndex(Phone.TYPE))
                        //标签
                        val label = this.getString(this.getColumnIndex(Phone.LABEL))
                        numbersBean.label = ContactsConstants.getPhoneLabel(phoneType)
                        numbersBean.displayLabel = ContactsConstants.getDisplayLabel(numbersBean.label, label)
                        //号码
                        val phoneNumber = this.getString(this.getColumnIndex(Phone.NUMBER))
                        val bean = ContactBackupResponse.ContactsBean.PhoneNumbersBean.PhoneNumberBean()
                        bean.number = phoneNumber
                        numbersBean.phoneNumber = bean
                        //添加Phone的信息
                        phoneNumbers.add(numbersBean)
                    }
                    contactsBean.phoneNumbers = phoneNumbers
                    this.close()
                }


                //查询Email类型的数据操作
                val emails = contentResolver.query(Email.CONTENT_URI, null,
                        Email.CONTACT_ID + " = " + contactId, null, null)
                emails?.apply {
                    val emailAddresses: MutableList<ContactBackupResponse.ContactsBean.EmailAddressesBean> = mutableListOf()
                    while (this.moveToNext()) {
                        val emailAddressesBean = ContactBackupResponse.ContactsBean.EmailAddressesBean()
                        val label = this.getString(this.getColumnIndex(Email.LABEL))
                        val type = this.getInt(this.getColumnIndex(Email.TYPE))
                        emailAddressesBean.label = ContactsConstants.getEmailLabel(type)
                        emailAddressesBean.displayLabel = ContactsConstants.getDisplayLabel(emailAddressesBean.label, label)
                        val emailAddress = this.getString(this.getColumnIndex(Email.DATA))
                        emailAddressesBean.email = emailAddress
                        //添加Email的信息
                        emailAddresses.add(emailAddressesBean)
                    }
                    contactsBean.emailAddresses = emailAddresses
                    this.close()
                }

                //查询im类型的数据操作
                val imWhere = ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?"
                val imWhereParams = arrayOf(contactId, Im.CONTENT_ITEM_TYPE)
                val ims = contentResolver.query(ContactsContract.Data.CONTENT_URI, null, imWhere, imWhereParams, null)
                ims?.apply {
                    val imAddresses: MutableList<ContactBackupResponse.ContactsBean.InstantMessageAddressesBean> = mutableListOf()
                    while (this.moveToNext()) {
                        val imAddressesBean = ContactBackupResponse.ContactsBean.InstantMessageAddressesBean()
                        val label = this.getString(this.getColumnIndex(Im.LABEL))
                        val type = this.getInt(this.getColumnIndex(Im.PROTOCOL))
                        imAddressesBean.label = ContactsConstants.getImLabel(type)
                        imAddressesBean.displayLabel = ContactsConstants.getDisplayLabel(imAddressesBean.label, label)
                        val data = this.getString(this.getColumnIndex(Im.DATA))
                        val serviceBean = ContactBackupResponse.ContactsBean.InstantMessageAddressesBean.ServiceBean()
                        serviceBean.service = imAddressesBean.displayLabel
                        serviceBean.username = data
                        imAddressesBean.service = serviceBean
                        imAddresses.add(imAddressesBean)
                    }
                    contactsBean.instantMessageAddresses = imAddresses
                    this.close()
                }

                //查询Relation类型的数据操作
                val relationWhere = ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?"
                val relationWhereParams = arrayOf(contactId, Relation.CONTENT_ITEM_TYPE)
                val relationCur = contentResolver.query(ContactsContract.Data.CONTENT_URI, null, relationWhere, relationWhereParams, null)
                relationCur?.apply {
                    val relationsBeans: MutableList<ContactBackupResponse.ContactsBean.ContactRelationsBean> = mutableListOf()
                    while (this.moveToNext()) {
                        val contactRelationsBean = ContactBackupResponse.ContactsBean.ContactRelationsBean()
                        val label: String = this.getString(this.getColumnIndex(Relation.LABEL))
                        val type: Int = this.getInt(this.getColumnIndex(Relation.TYPE))
                        contactRelationsBean.label = ContactsConstants.getRelationLabel(type)
                        contactRelationsBean.displayLabel = ContactsConstants.getDisplayLabel(contactRelationsBean.label, label)
                        val data: String = this.getString(this.getColumnIndex(Relation.DATA))
                        val relationBean = ContactBackupResponse.ContactsBean.ContactRelationsBean.RelationBean()
                        relationBean.name = data
                        contactRelationsBean.relation = relationBean
                        relationsBeans.add(contactRelationsBean)
                    }
                    contactsBean.contactRelations = relationsBeans
                    this.close()
                }

                //查询website类型的数据操作
                val urlWhere = ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?"
                val urlWhereParams = arrayOf(contactId, Website.CONTENT_ITEM_TYPE)
                val urlCur = contentResolver.query(ContactsContract.Data.CONTENT_URI, null, urlWhere, urlWhereParams, null)
                urlCur?.apply {
                    val urlAddresses: MutableList<ContactBackupResponse.ContactsBean.UrlAddressesBean> = mutableListOf()
                    while (this.moveToNext()) {
                        val urlAddresseBean = ContactBackupResponse.ContactsBean.UrlAddressesBean()
                        val label: String = this.getString(this.getColumnIndex(Website.LABEL))
                        val type: Int = this.getInt(this.getColumnIndex(Website.TYPE))
                        urlAddresseBean.label = ContactsConstants.getUrlLabel(type)
                        urlAddresseBean.displayLabel = ContactsConstants.getDisplayLabel(urlAddresseBean.label, label)
                        val url: String = this.getString(this.getColumnIndex(Website.URL))
                        urlAddresseBean.url = url
                        urlAddresses.add(urlAddresseBean)
                    }
                    contactsBean.urlAddresses = urlAddresses
                    this.close()
                }

                //查询event类型的数据操作
                val eventWhere = ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?"
                val eventParams = arrayOf(contactId, Event.CONTENT_ITEM_TYPE)
                val eventCur = contentResolver.query(ContactsContract.Data.CONTENT_URI, null, eventWhere, eventParams, null)
                eventCur?.apply {
                    val dates: MutableList<ContactBackupResponse.ContactsBean.DatesBean> = mutableListOf()
                    while (this.moveToNext()) {
                        val date = ContactBackupResponse.ContactsBean.DatesBean()
//                        val label: String = this.getString(this.getColumnIndex(Event.LABEL))
                        val type: Int = this.getInt(this.getColumnIndex(Event.TYPE))
                        val startDate: String = this.getString(this.getColumnIndex(Event.START_DATE))
                        val ymd = startDate.split("-")
                        when (type) {
                            //生日
                            Event.TYPE_BIRTHDAY -> {
                                val birthdayBean = ContactBackupResponse.ContactsBean.BirthdayBean()
                                birthdayBean.month = ymd[0].toInt()
                                birthdayBean.day = ymd[1].toInt()
                                birthdayBean.caledarIdentifier = 0
                                contactsBean.birthday = birthdayBean
                            }
                            //农历生日
                            Event.TYPE_CUSTOM -> {
                                val birthdayBean = ContactBackupResponse.ContactsBean.NonGregorianBirthdayBean()
                                birthdayBean.year = ymd[0].toInt()
                                birthdayBean.month = ymd[1].toInt()
                                birthdayBean.day = ymd[2].toInt()
                                birthdayBean.caledarIdentifier = 2
                                contactsBean.nonGregorianBirthday = birthdayBean
                            }
                            //纪念日,其它
                            Event.TYPE_ANNIVERSARY, Event.TYPE_OTHER -> {
                                val dateBean = ContactBackupResponse.ContactsBean.DatesBean.DateBean()
                                date.label = ContactsConstants.ANNIVERSARY
                                date.displayLabel = ContactsConstants.getDisplayLabel(date.label, "")
                                dateBean.year = ymd[0].toInt()
                                dateBean.month = ymd[1].toInt()
                                dateBean.day = ymd[2].toInt()
                                dateBean.caledarIdentifier = 0
                                date.date = dateBean
                                dates.add(date)
                            }
                        }
                    }
                    contactsBean.dates = dates
                    this.close()
                }
                //查询==地址==类型的数据操作
                val addressCur = contentResolver.query(StructuredPostal.CONTENT_URI, null,
                        StructuredPostal.CONTACT_ID + " = " + contactId, null, null)
                addressCur?.apply {
                    val postalAddresses: MutableList<ContactBackupResponse.ContactsBean.PostalAddressesBean> = mutableListOf()
                    while (this.moveToNext()) {
                        val postalAddresseBean = ContactBackupResponse.ContactsBean.PostalAddressesBean()
                        val label: String = this.getString(this.getColumnIndex(StructuredPostal.LABEL))
                        val type = this.getInt(this.getColumnIndex(StructuredPostal.TYPE))
                        postalAddresseBean.label = ContactsConstants.getPostalAddressesLabel(type)
                        postalAddresseBean.displayLabel = ContactsConstants.getDisplayLabel(postalAddresseBean.label, label)
                        val address = this.getString(this.getColumnIndex(StructuredPostal.DATA))
                        val city: String = this.getString(this.getColumnIndex(StructuredPostal.CITY))
                        val country: String = this.getString(this.getColumnIndex(StructuredPostal.COUNTRY))
                        val street: String = this.getString(this.getColumnIndex(StructuredPostal.STREET))
                        val postcode: String = this.getString(this.getColumnIndex(StructuredPostal.POSTCODE))
                        val region: String = this.getString(this.getColumnIndex(StructuredPostal.REGION))
                        val bean = ContactBackupResponse.ContactsBean.PostalAddressesBean.AddressBean()
                        bean.city = city
                        bean.country = country
                        bean.postalCode = postcode
                        bean.state = city
                        bean.street = street
                        bean.subLocality = region
                        postalAddresseBean.address = bean
                        //添加Email的信息
                        postalAddresses.add(postalAddresseBean)
                    }
                    contactsBean.postalAddresses = postalAddresses
                    this.close()
                }

                //查询==公司名字==类型的数据操作.Organization.COMPANY  ContactsContract.Data.CONTENT_URI
                val orgWhere = ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?"
                val orgWhereParams = arrayOf(contactId, Organization.CONTENT_ITEM_TYPE)
                val orgCur = contentResolver.query(ContactsContract.Data.CONTENT_URI, null, orgWhere, orgWhereParams, null)
                orgCur?.apply {
                    if (this.moveToFirst()) {
                        //组织名 (公司名字)
                        val company = this.getString(this.getColumnIndex(Organization.DATA))
                        contactsBean.organizationName = company
                        //组织名 (拼音)
                        val phoneticOrganizationName = this.getString(this.getColumnIndex(Organization.PHONETIC_NAME))
                        contactsBean.phoneticOrganizationName = phoneticOrganizationName
                        //组织名 (部門)
                        val department = this.getString(this.getColumnIndex(Organization.DEPARTMENT))
                        contactsBean.departmentName = department
                        //职位
                        val title = this.getString(this.getColumnIndex(Organization.TITLE))
                        contactsBean.jobTitle = title
                    }
                    this.close()
                }

                //nickName
                val nickNameWhere = ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?"
                val nickNameWhereParams = arrayOf(contactId, Nickname.CONTENT_ITEM_TYPE)
                val nickNameCur = contentResolver.query(ContactsContract.Data.CONTENT_URI, null, nickNameWhere, nickNameWhereParams, null)
                nickNameCur?.apply {
                    if (this.moveToFirst()) {
                        //组织名 (公司名字)
                        val nickname = this.getString(this.getColumnIndex(Nickname.NAME))
                        contactsBean.nickname = nickname
                    }
                    this.close()
                }

                //NOTE
                val noteWhere = ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?"
                val noteWhereParams = arrayOf(contactId, Note.CONTENT_ITEM_TYPE)
                val noteCur = contentResolver.query(ContactsContract.Data.CONTENT_URI, null, noteWhere, noteWhereParams, null)
                noteCur?.apply {
                    if (this.moveToFirst()) {
                        //组织名 (公司名字)
                        val note = this.getString(this.getColumnIndex(Note.NOTE))
                        contactsBean.note = note
                    }
                    noteCur.close()
                }

                //联系人姓名信息
                val nameWhere = ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?"
                val nameWhereParams = arrayOf(contactId, StructuredName.CONTENT_ITEM_TYPE)
                val nameCur = contentResolver.query(ContactsContract.Data.CONTENT_URI, null, nameWhere, nameWhereParams, null)
                nameCur?.apply {
                    if (nameCur.moveToFirst()) {
                        val displayName = this.getString(this.getColumnIndex(StructuredName.DISPLAY_NAME))
                        val familyName = this.getString(this.getColumnIndex(StructuredName.FAMILY_NAME))
                        val phoneticFamilyName = this.getString(this.getColumnIndex(StructuredName.PHONETIC_FAMILY_NAME))
                        val midName = this.getString(this.getColumnIndex(StructuredName.MIDDLE_NAME))
                        val phoneticMidName = this.getString(this.getColumnIndex(StructuredName.PHONETIC_MIDDLE_NAME))
                        val givenName = this.getString(this.getColumnIndex(StructuredName.GIVEN_NAME))
                        val phoneticGivenName = this.getString(this.getColumnIndex(StructuredName.PHONETIC_GIVEN_NAME))
                        val prefix = this.getString(this.getColumnIndex(StructuredName.PREFIX))
                        val suffix = this.getString(this.getColumnIndex(StructuredName.SUFFIX))
                        contactsBean.familyName = familyName
                        contactsBean.phoneticFamilyName = phoneticFamilyName
                        contactsBean.middleName = midName
                        contactsBean.phoneticMiddleName = phoneticMidName
                        contactsBean.givenName = givenName
                        contactsBean.phoneticGivenName = phoneticGivenName
                        contactsBean.nameSuffix = suffix
                        contactsBean.namePrefix = prefix
                    }
                    this.close()
                }
                //添加一条联系人信息
                contacts.add(contactsBean)
            }
            cursor.close()
            response.contacts = contacts
            return response
        }

    /**
     * 添加和修改联系人
     *
     * @throws Exception
     */
    fun writeContacts(response: ContactBackupResponse?): Boolean {
        response?.apply {
            contacts?.forEach { it ->
                rawContactInsertIndex = operations.size
                val operation = ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                        .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                        .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
//                            .withYieldAllowed(true)
                        .build()
                operations.add(operation)
                //添加IM信息
                addIMAddresses(it.instantMessageAddresses)
                //添加电话信息
                addPhoneNumbers(it.phoneNumbers)
                //添加联系人信息STRUCTUREDNAME
                addStructuredName(it)
                //添加联系人信息STRUCTUREDNAME
//                    addImageInfo(it.imageData, operations)
                //添加地址信息
                addPostalAddresses(it.postalAddresses)
                //添加网站信息
                addUrlAddresses(it.urlAddresses)
                //添加邮箱信息
                addEmailAddresses(it.emailAddresses)
                //添加昵称信息
                addNicknameInfo(it.nickname)
                //添加Note信息
                addNoteInfo(it.note)
                //添加Organization信息
                addOrganizationInfo(it)
                //添加Relations信息
                addRelations(it.contactRelations)
                //添加Birthday信息
                addBirthday(it.birthday, it.nonGregorianBirthday)
                //添加Dates信息
                addDates(it.dates)
                //添加Groups信息
                addGroups(it.groups)
                //添加社交信息
                addSocialProfiles(it.socialProfiles)
                val resolver = context.contentResolver
                //批量执行,返回执行结果集
                try {
                    val results = resolver.applyBatch(ContactsContract.AUTHORITY, operations)
                    for (result in results) {
                        Log.i(TAG, result.uri.toString())
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    operations.clear()
                }
            }
            return true
        }
        return true
    }

    private fun addImageInfo(imageData: String?) {
        imageData?.apply {
            val operation: ContentProviderOperation = ContentProviderOperation.newInsert(DATA_URI)
                    .withValueBackReference(RAW_CONTACT_ID, rawContactInsertIndex)
                    .withValue(MIME_TYPE, Photo.CONTENT_ITEM_TYPE)
                    .withValue(Photo.PHOTO, this)
//                    .withYieldAllowed(true)
                    .build()
            operations.add(operation)
        }
    }

    private fun addSocialProfiles(socialProfiles: List<ContactBackupResponse.ContactsBean.SocialProfilesBean>?) {
        socialProfiles?.forEach {
            it.label?.apply {
                val operation: ContentProviderOperation = ContentProviderOperation.newInsert(DATA_URI)
                        .withValueBackReference(RAW_CONTACT_ID, rawContactInsertIndex)
                        .withValue(MIME_TYPE, Im.CONTENT_ITEM_TYPE)
                        .withValue(Im.TYPE, Im.TYPE_WORK)
                        .withValue(Im.PROTOCOL, Im.PROTOCOL_CUSTOM)
                        .withValue(Im.DATA, it.social?.urlString)
                        .withValue(Im.CUSTOM_PROTOCOL, it.displayLabel)
                        .withValue(Im.LABEL, it.displayLabel)
//                        .withYieldAllowed(true)
                        .build()
                operations.add(operation)
            }
        }
    }

    private fun addGroups(groups: List<ContactBackupResponse.ContactsBean.GroupsBean>?) {
        groups?.forEach {
            it.label?.apply {
                val operation: ContentProviderOperation = ContentProviderOperation.newInsert(DATA_URI)
                        .withValueBackReference(RAW_CONTACT_ID, rawContactInsertIndex)
                        .withValue(MIME_TYPE, GroupMembership.CONTENT_ITEM_TYPE)
                        .withValue(GroupMembership.GROUP_ROW_ID, it.name)
//                        .withYieldAllowed(true)
                        .build()
                operations.add(operation)
            }
        }
    }

    private fun addDates(dates: List<ContactBackupResponse.ContactsBean.DatesBean>?) {
        dates?.forEach {
            it.label?.apply {
                val operation: ContentProviderOperation = ContentProviderOperation.newInsert(DATA_URI)
                        .withValueBackReference(RAW_CONTACT_ID, rawContactInsertIndex)
                        .withValue(MIME_TYPE, Event.CONTENT_ITEM_TYPE)
                        .withValue(Event.TYPE, ContactsConstants.getEventType(this))
                        .withValue(Event.START_DATE, "" + (it.date?.year
                                ?: 0) + "-" + (it.date?.month ?: 0) + "-" + (it.date?.day ?: 0))
                        .withValue(Event.LABEL, ContactsConstants.getDisplayLabel(this, it.displayLabel))
//                        .withYieldAllowed(true)
                        .build()
                operations.add(operation)
            }
        }
    }

    private fun addBirthday(birthday: ContactBackupResponse.ContactsBean.BirthdayBean?, nonGregorianBirthday: ContactBackupResponse.ContactsBean.NonGregorianBirthdayBean?) {
        //Event TYPE_BIRTHDAY
        birthday?.apply {
            val operation: ContentProviderOperation = ContentProviderOperation.newInsert(DATA_URI)
                    .withValueBackReference(RAW_CONTACT_ID, rawContactInsertIndex)
                    .withValue(MIME_TYPE, Event.CONTENT_ITEM_TYPE)
                    .withValue(Event.TYPE, Event.TYPE_BIRTHDAY)
                    .withValue(Event.START_DATE, "" + this.month + "-" + this.day)
//                    .withYieldAllowed(true)
                    .build()
            operations.add(operation)
        }
        nonGregorianBirthday?.apply {
            val operation: ContentProviderOperation = ContentProviderOperation.newInsert(DATA_URI)
                    .withValueBackReference(RAW_CONTACT_ID, rawContactInsertIndex)
                    .withValue(MIME_TYPE, Event.CONTENT_ITEM_TYPE)
                    .withValue(Event.TYPE, Event.TYPE_CUSTOM)
                    .withValue(Event.START_DATE, "" + this.year + "-" + this.month + "-" + this.day)
                    .withValue(Event.LABEL, "农历生日")
//                    .withYieldAllowed(true)
                    .build()
            operations.add(operation)
        }

    }

    private fun addRelations(contactRelations: List<ContactBackupResponse.ContactsBean.ContactRelationsBean>?) {
        contactRelations?.forEach {
            it.label?.apply {
                val operation: ContentProviderOperation = ContentProviderOperation.newInsert(DATA_URI)
                        .withValueBackReference(RAW_CONTACT_ID, rawContactInsertIndex)
                        .withValue(MIME_TYPE, Relation.CONTENT_ITEM_TYPE)
                        .withValue(Relation.TYPE, ContactsConstants.getRelationType(this))
                        .withValue(Relation.DATA, it.relation?.name)
                        .withValue(Relation.LABEL, ContactsConstants.getDisplayLabel(this, it.displayLabel))
//                        .withYieldAllowed(true)
                        .build()
                operations.add(operation)
            }
        }
    }

    private fun addOrganizationInfo(it: ContactBackupResponse.ContactsBean) {
        val operation: ContentProviderOperation = ContentProviderOperation.newInsert(DATA_URI)
                .withValueBackReference(RAW_CONTACT_ID, rawContactInsertIndex)
                .withValue(MIME_TYPE, Organization.CONTENT_ITEM_TYPE)
                .withValue(Organization.TYPE, Organization.TYPE_OTHER)
                .withValue(Organization.COMPANY, it.organizationName)
                .withValue(Organization.DEPARTMENT, it.departmentName)
                .withValue(Organization.TITLE, it.jobTitle)
                .withValue(Organization.SYMBOL, "")
                .withValue(Organization.JOB_DESCRIPTION, "")
                .withValue(Organization.OFFICE_LOCATION, "")
                .withValue(Organization.PHONETIC_NAME, it.phoneticOrganizationName)
//                .withYieldAllowed(true)
                .build()
        operations.add(operation)
    }

    private fun addEmailAddresses(emailAddresses: List<ContactBackupResponse.ContactsBean.EmailAddressesBean>?) {
        emailAddresses?.forEach {
            it.label?.apply {
                val operation: ContentProviderOperation = ContentProviderOperation.newInsert(DATA_URI)
                        .withValueBackReference(RAW_CONTACT_ID, rawContactInsertIndex)
                        .withValue(MIME_TYPE, Email.CONTENT_ITEM_TYPE)
                        .withValue(Email.TYPE, ContactsConstants.getEmailType(this))
                        .withValue(Email.ADDRESS, it.email)
                        .withValue(Email.LABEL, ContactsConstants.getDisplayLabel(this, it.displayLabel))
//                        .withYieldAllowed(true)
                        .build()
                operations.add(operation)
            }
        }
    }

    private fun addNoteInfo(note: String?) {
        note?.apply {
            val operation: ContentProviderOperation = ContentProviderOperation.newInsert(DATA_URI)
                    .withValueBackReference(RAW_CONTACT_ID, rawContactInsertIndex)
                    .withValue(MIME_TYPE, Note.CONTENT_ITEM_TYPE)
                    .withValue(Note.NOTE, this)
//                    .withYieldAllowed(true)
                    .build()
            operations.add(operation)
        }
    }

    private fun addNicknameInfo(nickname: String?) {
        nickname?.apply {
            val operation: ContentProviderOperation = ContentProviderOperation.newInsert(DATA_URI)
                    .withValueBackReference(RAW_CONTACT_ID, rawContactInsertIndex)
                    .withValue(MIME_TYPE, Nickname.CONTENT_ITEM_TYPE)
                    .withValue(Nickname.NAME, this)
//                    .withYieldAllowed(true)
                    .build()
            operations.add(operation)
        }
    }

    private fun addUrlAddresses(urlAddresses: List<ContactBackupResponse.ContactsBean.UrlAddressesBean>?) {
        urlAddresses?.forEach {
            it.label?.apply {
                val operation: ContentProviderOperation = ContentProviderOperation.newInsert(DATA_URI)
                        .withValueBackReference(RAW_CONTACT_ID, rawContactInsertIndex)
                        .withValue(MIME_TYPE, Website.CONTENT_ITEM_TYPE)
                        .withValue(Website.TYPE, ContactsConstants.getUrlType(this))
                        .withValue(Website.URL, it.url)
                        .withValue(Website.LABEL, ContactsConstants.getDisplayLabel(this, it.displayLabel))
                        .withYieldAllowed(true)
                        .build()
                operations.add(operation)
            }
        }
    }

    private fun addPostalAddresses(postalAddresses: List<ContactBackupResponse.ContactsBean.PostalAddressesBean>?) {
        postalAddresses?.forEach {
            it.address?.apply {
                //添加住宅地址信息
                val operation = ContentProviderOperation.newInsert(DATA_URI)
                        .withValueBackReference(RAW_CONTACT_ID, rawContactInsertIndex)
                        .withValue(MIME_TYPE, StructuredPostal.CONTENT_ITEM_TYPE)
                        .withValue(StructuredPostal.TYPE, ContactsConstants.getPostalAddresseType(it.label))
                        .withValue(StructuredPostal.COUNTRY, this.country)
                        .withValue(StructuredPostal.CITY, this.city)
                        .withValue(StructuredPostal.REGION, this.subLocality)
                        .withValue(StructuredPostal.STREET, this.street)
                        .withValue(StructuredPostal.POSTCODE, this.postalCode)
                        .withValue(StructuredPostal.FORMATTED_ADDRESS, this.country + this.state + this.city + this.subLocality + this.street)
                        .withValue(StructuredPostal.LABEL, ContactsConstants.getDisplayLabel(it.label, it.displayLabel))
//                        .withYieldAllowed(true)
                        .build()
                operations.add(operation)
            }
        }
    }

    private fun addStructuredName(it: ContactBackupResponse.ContactsBean) {
        val operation: ContentProviderOperation = ContentProviderOperation.newInsert(DATA_URI)
                .withValueBackReference(RAW_CONTACT_ID, rawContactInsertIndex)
                .withValue(MIME_TYPE, StructuredName.CONTENT_ITEM_TYPE)
                .withValue(StructuredName.DISPLAY_NAME, it.familyName + it.givenName)
                .withValue(StructuredName.FAMILY_NAME, it.familyName)
                .withValue(StructuredName.PHONETIC_FAMILY_NAME, it.phoneticFamilyName)
                .withValue(StructuredName.MIDDLE_NAME, it.middleName)
                .withValue(StructuredName.PHONETIC_MIDDLE_NAME, it.phoneticMiddleName)
                .withValue(StructuredName.GIVEN_NAME, it.givenName)
                .withValue(StructuredName.PHONETIC_GIVEN_NAME, it.phoneticGivenName)
                .withValue(StructuredName.FULL_NAME_STYLE, ContactsContract.FullNameStyle.CHINESE)
                .withValue(StructuredName.PHONETIC_NAME_STYLE, ContactsContract.PhoneticNameStyle.PINYIN)
                .withValue(StructuredName.PREFIX, it.namePrefix)
                .withValue(StructuredName.SUFFIX, it.nameSuffix)
//                .withYieldAllowed(true)
                .build()
        operations.add(operation)
    }

    /**
     * 添加电话信息
     */
    private fun addPhoneNumbers(it: List<ContactBackupResponse.ContactsBean.PhoneNumbersBean>?) {
        it?.forEach {
            it.label?.apply {
                val operation: ContentProviderOperation = ContentProviderOperation.newInsert(DATA_URI)
                        .withValueBackReference(RAW_CONTACT_ID, rawContactInsertIndex)
                        .withValue(MIME_TYPE, Phone.CONTENT_ITEM_TYPE)
                        .withValue(Phone.TYPE, ContactsConstants.getPhoneType(this))
                        .withValue(Phone.NUMBER, it.phoneNumber?.number)
                        .withValue(Phone.LABEL, ContactsConstants.getDisplayLabel(this, it.displayLabel))
//                        .withYieldAllowed(true)
                        .build()
                operations.add(operation)
            }
        }
    }

    /**
     * 添加IM信息
     */
    private fun addIMAddresses(it: List<ContactBackupResponse.ContactsBean.InstantMessageAddressesBean>?) {
        it?.forEach {
            it.label?.apply {
                val operation: ContentProviderOperation = ContentProviderOperation.newInsert(DATA_URI)
                        .withValueBackReference(RAW_CONTACT_ID, rawContactInsertIndex)
                        .withValue(MIME_TYPE, Im.CONTENT_ITEM_TYPE)
                        .withValue(Im.TYPE, Im.TYPE_WORK)
                        .withValue(Im.PROTOCOL, ContactsConstants.getImProtocol(this))
                        .withValue(Im.DATA, it.service?.username)
                        .withValue(Im.CUSTOM_PROTOCOL, it.displayLabel)
                        .withValue(Im.LABEL, ContactsConstants.getDisplayLabel(this, it.displayLabel))
//                        .withYieldAllowed(true)
                        .build()
                operations.add(operation)
            }
        }
    }

    companion object {
        val TAG: String = ContactBackupUtil::class.java.simpleName
        //[content://com.android.contacts/data]
        val DATA_URI: Uri = ContactsContract.Data.CONTENT_URI

        const val RAW_CONTACT_ID = ContactsContract.Data.RAW_CONTACT_ID
        const val MIME_TYPE: String = ContactsContract.Data.MIMETYPE

    }

}
