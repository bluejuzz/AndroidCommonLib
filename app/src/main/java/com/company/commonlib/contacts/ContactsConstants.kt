package com.company.commonlib.contacts

import android.provider.ContactsContract

/**
 * @author dinglaihong
 * @email 18279727279@163.com
 * @date 2019/7/29
 * @des
 */
class ContactsConstants {
    companion object {
        /* "_$!<Mother>!$_": "母亲", "_$!<FATHER>!$_": "父亲", "_$!<Parent>!$_": "父母", "_$!<Brother>!$_": "兄弟", "_$!<Son>!$_": "儿子", "_$!<Daughter>!$_": "女儿",
         "_$!<Child>!$_": "子女","_$!<Friend>!$_": "朋友", "_$!<Spouse>!$_": "配偶", "_$!<Partner>!$_": "伴侣","_$!<Assistant>!$_": "助理","_$!<Manager>!$_": "上司","_$!<Other>!$_": "其他",*/
        const val MOTHER: String = "_\$!<Mother>!\$_"
        const val FATHER: String = "_\$!<Father>!\$_"
        const val PARENT: String = "_\$!<Parent>!\$_"
        const val BROTHER: String = "_\$!<Brother>!\$_"
        const val SISTER: String = "_\$!<Sister>!\$_"
        const val SON: String = "_\$!<Son>!\$_"
        const val DAUGHTER: String = "_\$!<Daughter>!\$_"
        const val CHILD: String = "_\$!<Child>!\$_"
        const val FRIEND: String = "_\$!<Friend>!\$_"
        const val SPOUSE: String = "_\$!<Spouse>!\$_"
        const val PARTNER: String = "_\$!<Partner>!\$_"
        const val ASSISTANT: String = "_\$!<Assistant>!\$_"
        const val MANAGER: String = "_\$!<Manager>!\$_"
        const val OTHER: String = "_\$!<Other>!\$_"
        private val RELATIONSHIP_MAP: HashMap<String, String> = hashMapOf(
                MOTHER to "母亲",
                FATHER to "父亲",
                PARENT to "父母",
                BROTHER to "兄弟",
                SISTER to "姐妹",
                SON to "儿子",
                DAUGHTER to "女儿",
                CHILD to "子女",
                FRIEND to "朋友",
                SPOUSE to "配偶",
                PARTNER to "伴侣",
                ASSISTANT to "助理",
                MANAGER to "上司",
                OTHER to "其他"
        )

        private val RELATION_TYPE_MAP: HashMap<String, Int> = hashMapOf(
                MOTHER to ContactsContract.CommonDataKinds.Relation.TYPE_MOTHER,
                FATHER to ContactsContract.CommonDataKinds.Relation.TYPE_FATHER,
                PARENT to ContactsContract.CommonDataKinds.Relation.TYPE_PARENT,
                BROTHER to ContactsContract.CommonDataKinds.Relation.TYPE_BROTHER,
                SISTER to ContactsContract.CommonDataKinds.Relation.TYPE_SISTER,
                SON to ContactsContract.CommonDataKinds.Relation.TYPE_CHILD,
                DAUGHTER to ContactsContract.CommonDataKinds.Relation.TYPE_CHILD,
                CHILD to ContactsContract.CommonDataKinds.Relation.TYPE_CHILD,
                FRIEND to ContactsContract.CommonDataKinds.Relation.TYPE_FRIEND,
                SPOUSE to ContactsContract.CommonDataKinds.Relation.TYPE_SPOUSE,
                PARTNER to ContactsContract.CommonDataKinds.Relation.TYPE_PARTNER,
                ASSISTANT to ContactsContract.CommonDataKinds.Relation.TYPE_ASSISTANT,
                MANAGER to ContactsContract.CommonDataKinds.Relation.TYPE_MANAGER,
                OTHER to ContactsContract.CommonDataKinds.Relation.TYPE_CUSTOM
        )
        fun getRelationType(label: String?): Int {
            label?.let { it ->
                RELATION_TYPE_MAP[it]?.let {
                    return it
                }
            }
            return ContactsContract.CommonDataKinds.Relation.TYPE_CUSTOM
        }
        fun getRelationshipByKey(key: String) = RELATIONSHIP_MAP[key]
        /*"_$!<Home>!$_": "家庭","_$!<Work>!$_": "工作","iPhone": "iPhone","_$!<Mobile>!$_": "手机", "_$!<Main>!$_": "主要","_$!<HomeFAX>!$_": "住宅传真",
        "_$!<WorkFAX>!$_": "工作传真", "_$!<Pager>!$_": "传呼机", "_$!<Other>!$_": "其它","iCloud": "iCloud","_$!<HomePage>!$_": "主页","_$!<Anniversary>!$_": "纪念日",*/

        private val TAG_MAP: HashMap<String, String> = hashMapOf(
                "_\$!<Home>!\$_" to "家庭",
                "_\$!<Work>!\$_" to "工作",
                "_\$!<Mobile>!\$_" to "手机",
                "_\$!<Main>!\$_" to "主要",
                "_\$!<HomeFAX>!\$_" to "住宅传真",
                "_\$!<WorkFAX>!\$_" to "工作传真",
                "_\$!<Pager>!\$_" to "传呼机",
                "_\$!<Other>!\$_" to "其它",
                "_\$!<HomePage>!\$_" to "主页",
                "_\$!<Anniversary>!\$_" to "纪念日",
                "iCloud" to "iCloud",
                "iPhone" to "iPhone"
        )

        private val PHONE_TYPE_MAP: HashMap<String, Int> = hashMapOf(
                "_\$!<Home>!\$_" to ContactsContract.CommonDataKinds.Phone.TYPE_HOME,
                "_\$!<Work>!\$_" to ContactsContract.CommonDataKinds.Phone.TYPE_WORK,
                "_\$!<Assistant>!\$_" to ContactsContract.CommonDataKinds.Phone.TYPE_ASSISTANT,
                "_\$!<HomeFAX>!\$_" to ContactsContract.CommonDataKinds.Phone.TYPE_FAX_HOME,
                "_\$!<WorkFAX>!\$_" to ContactsContract.CommonDataKinds.Phone.TYPE_FAX_WORK,
                "_\$!<OtherFAX>!\$_" to ContactsContract.CommonDataKinds.Phone.TYPE_OTHER_FAX,
                "_\$!<Main>!\$_" to ContactsContract.CommonDataKinds.Phone.TYPE_MAIN,
                "_\$!<Mobile>!\$_" to ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE,
                "_\$!<Pager>!\$_" to ContactsContract.CommonDataKinds.Phone.TYPE_PAGER,
                "_\$!<WorkPager>!\$_" to ContactsContract.CommonDataKinds.Phone.TYPE_WORK_PAGER,
                "_\$!<Other>!\$_" to ContactsContract.CommonDataKinds.Phone.TYPE_OTHER
        )

        fun getPhoneType(label: String): Int {
            PHONE_TYPE_MAP[label]?.let {
                return it
            }
            return ContactsContract.CommonDataKinds.Phone.TYPE_OTHER
        }

        fun getInfoByKey(key: String) = TAG_MAP[key]
        val IM_PROTOCOL_MAP: HashMap<String, Int> = hashMapOf(
                "QQ" to ContactsContract.CommonDataKinds.Im.PROTOCOL_QQ,
                "Skype" to ContactsContract.CommonDataKinds.Im.PROTOCOL_SKYPE,
                "Custom" to ContactsContract.CommonDataKinds.Im.PROTOCOL_CUSTOM,
                "GOOGLE_TALK" to ContactsContract.CommonDataKinds.Im.PROTOCOL_GOOGLE_TALK,
                "ICQ" to ContactsContract.CommonDataKinds.Im.PROTOCOL_ICQ,
                "Jabber" to ContactsContract.CommonDataKinds.Im.PROTOCOL_JABBER,
                "MSN" to ContactsContract.CommonDataKinds.Im.PROTOCOL_MSN,
                "NETMEETING" to ContactsContract.CommonDataKinds.Im.PROTOCOL_NETMEETING,
                "雅虎" to ContactsContract.CommonDataKinds.Im.PROTOCOL_YAHOO,
                "AIM" to ContactsContract.CommonDataKinds.Im.PROTOCOL_AIM
        )

        fun getImProtocol(label: String?): Int {
            label?.let { it ->
                IM_PROTOCOL_MAP[it]?.let {
                    return it
                }
            }
            return ContactsContract.CommonDataKinds.Im.PROTOCOL_CUSTOM
        }

        val POSTALADDRESSE_TYPE_MAP: HashMap<String, Int> = hashMapOf(
                "_\$!<Home>!\$_" to ContactsContract.CommonDataKinds.StructuredPostal.TYPE_HOME,
                "_\$!<Work>!\$_" to ContactsContract.CommonDataKinds.StructuredPostal.TYPE_WORK,
                "_\$!<Custom>!\$_" to ContactsContract.CommonDataKinds.StructuredPostal.TYPE_CUSTOM,
                "_\$!<Other>!\$_" to ContactsContract.CommonDataKinds.StructuredPostal.TYPE_OTHER
        )

        fun getPostalAddresseType(label: String?): Int {
            label?.let { it ->
                POSTALADDRESSE_TYPE_MAP[it]?.let {
                    return it
                }
            }
            return ContactsContract.CommonDataKinds.StructuredPostal.TYPE_OTHER
        }

        val URL_TYPE_MAP: HashMap<String, Int> = hashMapOf(
                "_\$!<HomePage>!\$_" to ContactsContract.CommonDataKinds.Website.TYPE_HOMEPAGE,
                "_\$!<Work>!\$_" to ContactsContract.CommonDataKinds.Website.TYPE_WORK,
                "_\$!<Home>!\$_" to ContactsContract.CommonDataKinds.Website.TYPE_HOME,
                "_\$!<Blog>!\$_" to ContactsContract.CommonDataKinds.Website.TYPE_BLOG,
                "_\$!<Ftp>!\$_" to ContactsContract.CommonDataKinds.Website.TYPE_FTP,
                "_\$!<Profile>!\$_" to ContactsContract.CommonDataKinds.Website.TYPE_PROFILE,
                "_\$!<Custom>!\$_" to ContactsContract.CommonDataKinds.Website.TYPE_CUSTOM,
                "_\$!<Other>!\$_" to ContactsContract.CommonDataKinds.Website.TYPE_OTHER
        )

        fun getUrlType(label: String?): Int {
            label?.let { it ->
                URL_TYPE_MAP[it]?.let {
                    return it
                }
            }
            return ContactsContract.CommonDataKinds.Website.TYPE_CUSTOM
        }

        val EMAIL_TYPE_MAP: HashMap<String, Int> = hashMapOf(
                "_\$!<Work>!\$_" to ContactsContract.CommonDataKinds.Email.TYPE_WORK,
                "_\$!<Home>!\$_" to ContactsContract.CommonDataKinds.Email.TYPE_HOME,
                "_\$!<Mobile>!\$_" to ContactsContract.CommonDataKinds.Email.TYPE_MOBILE,
                "_\$!<Custom>!\$_" to ContactsContract.CommonDataKinds.Email.TYPE_CUSTOM,
                "_\$!<Other>!\$_" to ContactsContract.CommonDataKinds.Email.TYPE_OTHER
        )

        fun getEmailType(label: String?): Int {
            label?.let { it ->
                EMAIL_TYPE_MAP[it]?.let {
                    return it
                }
            }
            return ContactsContract.CommonDataKinds.Email.TYPE_CUSTOM
        }

        val EVENT_TYPE_MAP: HashMap<String, Int> = hashMapOf(
                "_\$!<Birthday>!\$_" to ContactsContract.CommonDataKinds.Event.TYPE_BIRTHDAY,
                "_\$!<Custom>!\$_" to ContactsContract.CommonDataKinds.Event.TYPE_CUSTOM,
                "_\$!<Anniversary>!\$_" to ContactsContract.CommonDataKinds.Event.TYPE_ANNIVERSARY,
                "_\$!<Other>!\$_" to ContactsContract.CommonDataKinds.Event.TYPE_OTHER
        )

        fun getEventType(label: String?): Int {
            label?.let { it ->
                EVENT_TYPE_MAP[it]?.let {
                    return it
                }
            }
            return ContactsContract.CommonDataKinds.Event.TYPE_CUSTOM
        }
    }
}