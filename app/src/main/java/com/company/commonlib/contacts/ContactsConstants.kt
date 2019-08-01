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
         "_$!<Child>!$_": "子女","_$!<Friend>!$_": "朋友", "_$!<Spouse>!$_": "配偶", "_$!<Partner>!$_": "伴侣","_$!<Assistant>!$_": "助理","_$!<Manager>!$_": "上司","_$!<Other>!$_": "其他",
         _$!<Home>!$_": "家庭","_$!<Work>!$_": "工作","iPhone": "iPhone","_$!<Mobile>!$_": "手机", "_$!<Main>!$_": "主要","_$!<HomeFAX>!$_": "住宅传真",
        "_$!<WorkFAX>!$_": "工作传真", "_$!<Pager>!$_": "传呼机", "_$!<Other>!$_": "其它","iCloud": "iCloud","_$!<HomePage>!$_": "主页","_$!<Anniversary>!$_": "纪念日",*/
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
        const val HOME: String = "_\$!<Home>!\$_"
        const val WORK: String = "_\$!<Work>!\$_"
        const val MOBILE: String = "_\$!<Mobile>!\$_"
        const val MAIN: String = "_\$!<Main>!\$_"
        const val HOME_FAX: String = "_\$!<HomeFAX>!\$_"
        const val WORK_FAX: String = "_\$!<WorkFAX>!\$_"
        const val OTHER_FAX: String = "_\$!<OtherFAX>!\$_"
        const val PAGER: String = "_\$!<Pager>!\$_"
        const val WORK_PAGER: String = "_\$!<WorkPager>!\$_"
        const val HOMEPAGE: String = "_\$!<HomePage>!\$_"
        const val ANNIVERSARY: String = "_\$!<Anniversary>!\$_"
        const val CUSTOM: String = "_\$!<Custom>!\$_"
        const val BIRTHDAY: String = "_\$!<Birthday>!\$_"
        const val BLOG = "_\$!<Blog>!\$_"
        const val FTP = "_\$!<Ftp>!\$_"
        const val PROFILE = "_\$!<Profile>!\$_"
        const val I_CLOUD: String = "iCloud"
        const val IPHONE: String = "iPhone"
        const val IM_QQ: String = "QQ"
        const val IM_SKYPE: String = "Skype"
        const val IM_ICQ: String = "ICQ"
        const val IM_JABBER: String = "Jabber"
        const val IM_MSN: String = "MSN"
        const val IM_NET_MEETING: String = "NETMEETING"
        const val IM_YAHOO: String = "Yahoo"
        const val IM_AIM: String = "AIM"
        const val IM_GOOGLE_TALK: String = "GOOGLE_TALK"
        const val IM_CUSTOM: String = "Custom"

        /**
         * 获取地址类型
         */
        fun getDisplayLabel(label: String?, displayLabel: String?): String? {
            return when (label) {
                MOTHER -> "母亲"
                FATHER -> "父亲"
                PARENT -> "父母"
                BROTHER -> "兄弟"
                SISTER -> "姐妹"
                SON -> "儿子"
                DAUGHTER -> "女儿"
                CHILD -> "子女"
                FRIEND -> "朋友"
                SPOUSE -> "配偶"
                PARTNER -> "伴侣"
                ASSISTANT -> "助理"
                MANAGER -> "上司"
                OTHER -> "其他"
                HOME -> "家庭"
                WORK -> "工作"
                MOBILE -> "手机"
                MAIN -> "主要"
                HOME_FAX -> "住宅传真"
                WORK_FAX -> "工作传真"
                PAGER -> "传呼机"
                HOMEPAGE -> "主页"
                ANNIVERSARY -> "纪念日"
                CUSTOM -> displayLabel
                I_CLOUD -> "iCloud"
                IPHONE -> "iPhone"
                IM_QQ -> "QQ"
                IM_SKYPE -> "Skype"
                IM_ICQ -> "ICQ"
                IM_JABBER -> "Jabber"
                IM_MSN -> "MSN"
                IM_NET_MEETING -> "NETMEETING"
                IM_YAHOO -> "Yahoo"
                IM_AIM -> "AIM"
                IM_GOOGLE_TALK -> "GOOGLE_TALK"
                IM_CUSTOM -> displayLabel
                else -> displayLabel
            }
        }

        /**
         * 获取关系类型
         */
        fun getRelationType(label: String?): Int {
            return when (label) {
                MOTHER -> ContactsContract.CommonDataKinds.Relation.TYPE_MOTHER
                FATHER -> ContactsContract.CommonDataKinds.Relation.TYPE_FATHER
                PARENT -> ContactsContract.CommonDataKinds.Relation.TYPE_PARENT
                BROTHER -> ContactsContract.CommonDataKinds.Relation.TYPE_BROTHER
                SISTER -> ContactsContract.CommonDataKinds.Relation.TYPE_SISTER
                SON -> ContactsContract.CommonDataKinds.Relation.TYPE_CUSTOM
                DAUGHTER -> ContactsContract.CommonDataKinds.Relation.TYPE_CUSTOM
                CHILD -> ContactsContract.CommonDataKinds.Relation.TYPE_CHILD
                FRIEND -> ContactsContract.CommonDataKinds.Relation.TYPE_FRIEND
                SPOUSE -> ContactsContract.CommonDataKinds.Relation.TYPE_SPOUSE
                PARTNER -> ContactsContract.CommonDataKinds.Relation.TYPE_PARTNER
                ASSISTANT -> ContactsContract.CommonDataKinds.Relation.TYPE_ASSISTANT
                MANAGER -> ContactsContract.CommonDataKinds.Relation.TYPE_MANAGER
                OTHER -> ContactsContract.CommonDataKinds.Relation.TYPE_CUSTOM
                else -> ContactsContract.CommonDataKinds.Relation.TYPE_CUSTOM
            }
        }

        fun getRelationLabel(type: Int): String {
            return when (type) {
                ContactsContract.CommonDataKinds.Relation.TYPE_MOTHER -> MOTHER
                ContactsContract.CommonDataKinds.Relation.TYPE_FATHER -> FATHER
                ContactsContract.CommonDataKinds.Relation.TYPE_PARENT -> PARENT
                ContactsContract.CommonDataKinds.Relation.TYPE_BROTHER -> BROTHER
                ContactsContract.CommonDataKinds.Relation.TYPE_SISTER -> SISTER
                ContactsContract.CommonDataKinds.Relation.TYPE_CUSTOM -> MOTHER
                ContactsContract.CommonDataKinds.Relation.TYPE_CHILD -> CHILD
                ContactsContract.CommonDataKinds.Relation.TYPE_FRIEND -> FRIEND
                ContactsContract.CommonDataKinds.Relation.TYPE_SPOUSE -> SPOUSE
                ContactsContract.CommonDataKinds.Relation.TYPE_PARTNER -> PARTNER
                ContactsContract.CommonDataKinds.Relation.TYPE_ASSISTANT -> ASSISTANT
                ContactsContract.CommonDataKinds.Relation.TYPE_MANAGER -> MANAGER
                else -> "自定义"
            }
        }


        /**
         * 获取电话类型
         */
        fun getPhoneType(label: String?): Int {
            return when (label) {
                HOME -> ContactsContract.CommonDataKinds.Phone.TYPE_HOME
                WORK -> ContactsContract.CommonDataKinds.Phone.TYPE_WORK
                ASSISTANT -> ContactsContract.CommonDataKinds.Phone.TYPE_ASSISTANT
                HOME_FAX -> ContactsContract.CommonDataKinds.Phone.TYPE_FAX_HOME
                WORK_FAX -> ContactsContract.CommonDataKinds.Phone.TYPE_FAX_WORK
                OTHER_FAX -> ContactsContract.CommonDataKinds.Phone.TYPE_OTHER_FAX
                MAIN -> ContactsContract.CommonDataKinds.Phone.TYPE_MAIN
                MOBILE -> ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE
                PAGER -> ContactsContract.CommonDataKinds.Phone.TYPE_PAGER
                WORK_PAGER -> ContactsContract.CommonDataKinds.Phone.TYPE_WORK_PAGER
                OTHER -> ContactsContract.CommonDataKinds.Phone.TYPE_OTHER
                CUSTOM -> ContactsContract.CommonDataKinds.Phone.TYPE_CUSTOM
                else -> ContactsContract.CommonDataKinds.Phone.TYPE_CUSTOM
            }
        }

        /**
         * 获取电话类型
         */
        fun getPhoneLabel(type: Int): String? {
            return when (type) {
                ContactsContract.CommonDataKinds.Phone.TYPE_HOME -> HOME
                ContactsContract.CommonDataKinds.Phone.TYPE_WORK -> WORK
                ContactsContract.CommonDataKinds.Phone.TYPE_ASSISTANT -> ASSISTANT
                ContactsContract.CommonDataKinds.Phone.TYPE_FAX_HOME -> HOME_FAX
                ContactsContract.CommonDataKinds.Phone.TYPE_FAX_WORK -> WORK_FAX
                ContactsContract.CommonDataKinds.Phone.TYPE_OTHER_FAX -> OTHER_FAX
                ContactsContract.CommonDataKinds.Phone.TYPE_MAIN -> MAIN
                ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE -> MOBILE
                ContactsContract.CommonDataKinds.Phone.TYPE_PAGER -> PAGER
                ContactsContract.CommonDataKinds.Phone.TYPE_WORK_PAGER -> WORK_PAGER
                ContactsContract.CommonDataKinds.Phone.TYPE_OTHER -> OTHER
                ContactsContract.CommonDataKinds.Phone.TYPE_CUSTOM -> null
                else -> null
            }
        }


        /**
         * 获取im类型
         */
        fun getImProtocol(label: String?): Int {
            return when (label) {
                IM_QQ -> ContactsContract.CommonDataKinds.Im.PROTOCOL_QQ
                IM_SKYPE -> ContactsContract.CommonDataKinds.Im.PROTOCOL_SKYPE
                IM_CUSTOM -> ContactsContract.CommonDataKinds.Im.PROTOCOL_CUSTOM
                IM_GOOGLE_TALK -> ContactsContract.CommonDataKinds.Im.PROTOCOL_GOOGLE_TALK
                IM_ICQ -> ContactsContract.CommonDataKinds.Im.PROTOCOL_ICQ
                IM_JABBER -> ContactsContract.CommonDataKinds.Im.PROTOCOL_JABBER
                IM_MSN -> ContactsContract.CommonDataKinds.Im.PROTOCOL_MSN
                IM_NET_MEETING -> ContactsContract.CommonDataKinds.Im.PROTOCOL_NETMEETING
                IM_YAHOO -> ContactsContract.CommonDataKinds.Im.PROTOCOL_YAHOO
                IM_AIM -> ContactsContract.CommonDataKinds.Im.PROTOCOL_AIM
                else -> ContactsContract.CommonDataKinds.Im.PROTOCOL_CUSTOM
            }
        }

        fun getImLabel(type: Int): String? {
            return when (type) {
                ContactsContract.CommonDataKinds.Im.PROTOCOL_QQ -> IM_QQ
                ContactsContract.CommonDataKinds.Im.PROTOCOL_SKYPE -> IM_SKYPE
                ContactsContract.CommonDataKinds.Im.PROTOCOL_CUSTOM -> IM_CUSTOM
                ContactsContract.CommonDataKinds.Im.PROTOCOL_GOOGLE_TALK -> IM_GOOGLE_TALK
                ContactsContract.CommonDataKinds.Im.PROTOCOL_ICQ -> IM_ICQ
                ContactsContract.CommonDataKinds.Im.PROTOCOL_JABBER -> IM_JABBER
                ContactsContract.CommonDataKinds.Im.PROTOCOL_MSN -> IM_MSN
                ContactsContract.CommonDataKinds.Im.PROTOCOL_NETMEETING -> IM_NET_MEETING
                ContactsContract.CommonDataKinds.Im.PROTOCOL_YAHOO -> IM_YAHOO
                ContactsContract.CommonDataKinds.Im.PROTOCOL_AIM -> IM_AIM
                else -> "自定义"
            }
        }


        /**
         * 获取地址类型
         */
        fun getPostalAddresseType(label: String?): Int {
            return when (label) {
                HOME -> ContactsContract.CommonDataKinds.StructuredPostal.TYPE_HOME
                WORK -> ContactsContract.CommonDataKinds.StructuredPostal.TYPE_WORK
                CUSTOM -> ContactsContract.CommonDataKinds.StructuredPostal.TYPE_CUSTOM
                OTHER -> ContactsContract.CommonDataKinds.StructuredPostal.TYPE_OTHER
                else -> ContactsContract.CommonDataKinds.StructuredPostal.TYPE_CUSTOM
            }
        }

        fun getPostalAddressesLabel(type: Int): String {
            return when (type) {
                ContactsContract.CommonDataKinds.StructuredPostal.TYPE_WORK -> WORK
                ContactsContract.CommonDataKinds.StructuredPostal.TYPE_HOME -> HOME
                ContactsContract.CommonDataKinds.StructuredPostal.TYPE_CUSTOM -> CUSTOM
                ContactsContract.CommonDataKinds.StructuredPostal.TYPE_OTHER -> OTHER
                else -> "自定义"
            }
        }

        /**
         * 获取网站类型
         */
        fun getUrlType(label: String?): Int {
            return when (label) {
                HOMEPAGE -> ContactsContract.CommonDataKinds.Website.TYPE_HOMEPAGE
                WORK -> ContactsContract.CommonDataKinds.Website.TYPE_WORK
                HOME -> ContactsContract.CommonDataKinds.Website.TYPE_HOME
                BLOG -> ContactsContract.CommonDataKinds.Website.TYPE_BLOG
                FTP -> ContactsContract.CommonDataKinds.Website.TYPE_FTP
                PROFILE -> ContactsContract.CommonDataKinds.Website.TYPE_PROFILE
                CUSTOM -> ContactsContract.CommonDataKinds.Website.TYPE_CUSTOM
                OTHER -> ContactsContract.CommonDataKinds.Website.TYPE_OTHER
                else -> ContactsContract.CommonDataKinds.Website.TYPE_CUSTOM
            }
        }

        fun getUrlLabel(type: Int): String {
            return when (type) {
                ContactsContract.CommonDataKinds.Website.TYPE_HOMEPAGE -> HOMEPAGE
                ContactsContract.CommonDataKinds.Website.TYPE_WORK -> WORK
                ContactsContract.CommonDataKinds.Website.TYPE_HOME -> HOME
                ContactsContract.CommonDataKinds.Website.TYPE_BLOG -> BLOG
                ContactsContract.CommonDataKinds.Website.TYPE_FTP -> FTP
                ContactsContract.CommonDataKinds.Website.TYPE_PROFILE -> PROFILE
                ContactsContract.CommonDataKinds.Website.TYPE_CUSTOM -> CUSTOM
                ContactsContract.CommonDataKinds.Website.TYPE_OTHER -> OTHER
                else -> "自定义"
            }
        }


        /**
         * 获取邮件类型
         */
        fun getEmailType(label: String?): Int {
            return when (label) {
                WORK -> ContactsContract.CommonDataKinds.Email.TYPE_WORK
                HOME -> ContactsContract.CommonDataKinds.Email.TYPE_HOME
                MOBILE -> ContactsContract.CommonDataKinds.Email.TYPE_MOBILE
                CUSTOM -> ContactsContract.CommonDataKinds.Email.TYPE_CUSTOM
                OTHER -> ContactsContract.CommonDataKinds.Email.TYPE_OTHER
                else -> ContactsContract.CommonDataKinds.Email.TYPE_CUSTOM
            }
        }

        fun getEmailLabel(type: Int): String {
            return when (type) {
                ContactsContract.CommonDataKinds.Email.TYPE_WORK -> WORK
                ContactsContract.CommonDataKinds.Email.TYPE_HOME -> HOME
                ContactsContract.CommonDataKinds.Email.TYPE_MOBILE -> MOBILE
                ContactsContract.CommonDataKinds.Email.TYPE_CUSTOM -> CUSTOM
                ContactsContract.CommonDataKinds.Email.TYPE_OTHER -> OTHER
                else -> "自定义"
            }

        }

        /**
         * 获取事件类型
         */
        fun getEventType(label: String?): Int {
            return when (label) {
                BIRTHDAY -> ContactsContract.CommonDataKinds.Event.TYPE_BIRTHDAY
                CUSTOM -> ContactsContract.CommonDataKinds.Event.TYPE_CUSTOM
                ANNIVERSARY -> ContactsContract.CommonDataKinds.Event.TYPE_ANNIVERSARY
                OTHER -> ContactsContract.CommonDataKinds.Event.TYPE_OTHER
                else -> ContactsContract.CommonDataKinds.Event.TYPE_OTHER
            }
        }
    }
}