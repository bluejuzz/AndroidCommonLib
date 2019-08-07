package com.company.commonlib.contacts.kotlin

import android.provider.ContactsContract.CommonDataKinds.*

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
        const val LABEL_DEFULT = "自定义"
        /**
         * 获取显示标签
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
                CUSTOM, IM_CUSTOM -> displayLabel
                else -> displayLabel
            }
        }

        /**
         * 获取关系类型
         */
        fun getRelationType(label: String?): Int {
            return when (label) {
                MOTHER -> Relation.TYPE_MOTHER
                FATHER -> Relation.TYPE_FATHER
                PARENT -> Relation.TYPE_PARENT
                BROTHER -> Relation.TYPE_BROTHER
                SISTER -> Relation.TYPE_SISTER
                SON -> Relation.TYPE_CUSTOM
                DAUGHTER -> Relation.TYPE_CUSTOM
                CHILD -> Relation.TYPE_CHILD
                FRIEND -> Relation.TYPE_FRIEND
                SPOUSE -> Relation.TYPE_SPOUSE
                PARTNER -> Relation.TYPE_PARTNER
                ASSISTANT -> Relation.TYPE_ASSISTANT
                MANAGER -> Relation.TYPE_MANAGER
                OTHER -> Relation.TYPE_CUSTOM
                else -> Relation.TYPE_CUSTOM
            }
        }


        /**
         * 获取关系标签
         */
        fun getRelationLabel(type: Int): String {
            return when (type) {
                Relation.TYPE_MOTHER -> MOTHER
                Relation.TYPE_FATHER -> FATHER
                Relation.TYPE_PARENT -> PARENT
                Relation.TYPE_BROTHER -> BROTHER
                Relation.TYPE_SISTER -> SISTER
                Relation.TYPE_CUSTOM -> MOTHER
                Relation.TYPE_CHILD -> CHILD
                Relation.TYPE_FRIEND -> FRIEND
                Relation.TYPE_SPOUSE -> SPOUSE
                Relation.TYPE_PARTNER -> PARTNER
                Relation.TYPE_ASSISTANT -> ASSISTANT
                Relation.TYPE_MANAGER -> MANAGER
                else -> LABEL_DEFULT
            }
        }


        /**
         * 获取电话类型
         */
        fun getPhoneType(label: String?): Int {
            return when (label) {
                HOME -> Phone.TYPE_HOME
                WORK -> Phone.TYPE_WORK
                ASSISTANT -> Phone.TYPE_ASSISTANT
                HOME_FAX -> Phone.TYPE_FAX_HOME
                WORK_FAX -> Phone.TYPE_FAX_WORK
                OTHER_FAX -> Phone.TYPE_OTHER_FAX
                MAIN -> Phone.TYPE_MAIN
                MOBILE -> Phone.TYPE_MOBILE
                PAGER -> Phone.TYPE_PAGER
                WORK_PAGER -> Phone.TYPE_WORK_PAGER
                OTHER -> Phone.TYPE_OTHER
                CUSTOM -> Phone.TYPE_CUSTOM
                else -> Phone.TYPE_CUSTOM
            }
        }

        /**
         * 获取电话标签
         */
        fun getPhoneLabel(type: Int): String? {
            return when (type) {
                Phone.TYPE_HOME -> HOME
                Phone.TYPE_WORK -> WORK
                Phone.TYPE_ASSISTANT -> ASSISTANT
                Phone.TYPE_FAX_HOME -> HOME_FAX
                Phone.TYPE_FAX_WORK -> WORK_FAX
                Phone.TYPE_OTHER_FAX -> OTHER_FAX
                Phone.TYPE_MAIN -> MAIN
                Phone.TYPE_MOBILE -> MOBILE
                Phone.TYPE_PAGER -> PAGER
                Phone.TYPE_WORK_PAGER -> WORK_PAGER
                Phone.TYPE_OTHER -> OTHER
                Phone.TYPE_CUSTOM -> LABEL_DEFULT
                else -> LABEL_DEFULT
            }
        }


        /**
         * 获取im类型
         */
        fun getImProtocol(label: String?): Int {
            return when (label) {
                IM_QQ -> Im.PROTOCOL_QQ
                IM_SKYPE -> Im.PROTOCOL_SKYPE
                IM_CUSTOM -> Im.PROTOCOL_CUSTOM
                IM_GOOGLE_TALK -> Im.PROTOCOL_GOOGLE_TALK
                IM_ICQ -> Im.PROTOCOL_ICQ
                IM_JABBER -> Im.PROTOCOL_JABBER
                IM_MSN -> Im.PROTOCOL_MSN
                IM_NET_MEETING -> Im.PROTOCOL_NETMEETING
                IM_YAHOO -> Im.PROTOCOL_YAHOO
                IM_AIM -> Im.PROTOCOL_AIM
                else -> Im.PROTOCOL_CUSTOM
            }
        }

        /**
         * 获取im标签
         */
        fun getImLabel(type: Int): String? {
            return when (type) {
                Im.PROTOCOL_QQ -> IM_QQ
                Im.PROTOCOL_SKYPE -> IM_SKYPE
                Im.PROTOCOL_CUSTOM -> IM_CUSTOM
                Im.PROTOCOL_GOOGLE_TALK -> IM_GOOGLE_TALK
                Im.PROTOCOL_ICQ -> IM_ICQ
                Im.PROTOCOL_JABBER -> IM_JABBER
                Im.PROTOCOL_MSN -> IM_MSN
                Im.PROTOCOL_NETMEETING -> IM_NET_MEETING
                Im.PROTOCOL_YAHOO -> IM_YAHOO
                Im.PROTOCOL_AIM -> IM_AIM
                else -> LABEL_DEFULT
            }
        }


        /**
         * 获取地址类型
         */
        fun getPostalAddresseType(label: String?): Int {
            return when (label) {
                HOME -> StructuredPostal.TYPE_HOME
                WORK -> StructuredPostal.TYPE_WORK
                CUSTOM -> StructuredPostal.TYPE_CUSTOM
                OTHER -> StructuredPostal.TYPE_OTHER
                else -> StructuredPostal.TYPE_CUSTOM
            }
        }

        /**
         * 获取地址标签
         */
        fun getPostalAddressesLabel(type: Int): String {
            return when (type) {
                StructuredPostal.TYPE_WORK -> WORK
                StructuredPostal.TYPE_HOME -> HOME
                StructuredPostal.TYPE_CUSTOM -> CUSTOM
                StructuredPostal.TYPE_OTHER -> OTHER
                else -> LABEL_DEFULT
            }
        }

        /**
         * 获取网站类型
         */
        fun getUrlType(label: String?): Int {
            return when (label) {
                HOMEPAGE -> Website.TYPE_HOMEPAGE
                WORK -> Website.TYPE_WORK
                HOME -> Website.TYPE_HOME
                BLOG -> Website.TYPE_BLOG
                FTP -> Website.TYPE_FTP
                PROFILE -> Website.TYPE_PROFILE
                CUSTOM -> Website.TYPE_CUSTOM
                OTHER -> Website.TYPE_OTHER
                else -> Website.TYPE_CUSTOM
            }
        }

        fun getUrlLabel(type: Int): String {
            return when (type) {
                Website.TYPE_HOMEPAGE -> HOMEPAGE
                Website.TYPE_WORK -> WORK
                Website.TYPE_HOME -> HOME
                Website.TYPE_BLOG -> BLOG
                Website.TYPE_FTP -> FTP
                Website.TYPE_PROFILE -> PROFILE
                Website.TYPE_CUSTOM -> CUSTOM
                Website.TYPE_OTHER -> OTHER
                else -> LABEL_DEFULT
            }
        }


        /**
         * 获取邮件类型
         */
        fun getEmailType(label: String?): Int {
            return when (label) {
                WORK -> Email.TYPE_WORK
                HOME -> Email.TYPE_HOME
                MOBILE -> Email.TYPE_MOBILE
                CUSTOM -> Email.TYPE_CUSTOM
                OTHER -> Email.TYPE_OTHER
                else -> Email.TYPE_CUSTOM
            }
        }

        fun getEmailLabel(type: Int): String {
            return when (type) {
                Email.TYPE_WORK -> WORK
                Email.TYPE_HOME -> HOME
                Email.TYPE_MOBILE -> MOBILE
                Email.TYPE_CUSTOM -> CUSTOM
                Email.TYPE_OTHER -> OTHER
                else -> LABEL_DEFULT
            }

        }

        /**
         * 获取事件类型
         */
        fun getEventType(label: String?): Int {
            return when (label) {
                BIRTHDAY -> Event.TYPE_BIRTHDAY
                CUSTOM -> Event.TYPE_CUSTOM
                ANNIVERSARY -> Event.TYPE_ANNIVERSARY
                OTHER -> Event.TYPE_OTHER
                else -> Event.TYPE_CUSTOM
            }
        }

        fun getEventLabel(type: Int): String {
            return when (type) {
                Event.TYPE_BIRTHDAY -> BIRTHDAY
                Event.TYPE_ANNIVERSARY -> ANNIVERSARY
                Event.TYPE_OTHER -> OTHER
                Event.TYPE_CUSTOM -> CUSTOM
                else -> LABEL_DEFULT
            }
        }
    }
}