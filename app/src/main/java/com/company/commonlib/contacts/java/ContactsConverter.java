package com.company.commonlib.contacts.java;

import org.jetbrains.annotations.Nullable;

import static android.provider.ContactsContract.CommonDataKinds.Email;
import static android.provider.ContactsContract.CommonDataKinds.Event;
import static android.provider.ContactsContract.CommonDataKinds.Im;
import static android.provider.ContactsContract.CommonDataKinds.Phone;
import static android.provider.ContactsContract.CommonDataKinds.Relation;
import static android.provider.ContactsContract.CommonDataKinds.StructuredPostal;
import static android.provider.ContactsContract.CommonDataKinds.Website;

/**
 * @author dinglaihong
 * @email 18279727279@163.com
 * @date 2019/8/7
 * @des
 */
@SuppressWarnings("ALL")
class ContactsConverter {
    private static final String MOTHER = "_$!<Mother>!$_";
    private static final String FATHER = "_$!<Father>!$_";
    private static final String PARENT = "_$!<Parent>!$_";
    private static final String BROTHER = "_$!<Brother>!$_";
    private static final String SISTER = "_$!<Sister>!$_";
    private static final String SON = "_$!<Son>!$_";
    private static final String DAUGHTER = "_$!<Daughter>!$_";
    private static final String CHILD = "_$!<Child>!$_";
    private static final String FRIEND = "_$!<Friend>!$_";
    private static final String SPOUSE = "_$!<Spouse>!$_";
    private static final String PARTNER = "_$!<Partner>!$_";
    private static final String ASSISTANT = "_$!<Assistant>!$_";
    private static final String MANAGER = "_$!<Manager>!$_";
    private static final String OTHER = "_$!<Other>!$_";
    private static final String HOME = "_$!<Home>!$_";
    private static final String WORK = "_$!<Work>!$_";
    private static final String MOBILE = "_$!<Mobile>!$_";
    private static final String MAIN = "_$!<Main>!$_";
    private static final String HOME_FAX = "_$!<HomeFAX>!$_";
    private static final String WORK_FAX = "_$!<WorkFAX>!$_";
    private static final String OTHER_FAX = "_$!<OtherFAX>!$_";
    private static final String PAGER = "_$!<Pager>!$_";
    private static final String WORK_PAGER = "_$!<WorkPager>!$_";
    private static final String HOMEPAGE = "_$!<HomePage>!$_";
    private static final String ANNIVERSARY = "_$!<Anniversary>!$_";
    private static final String CUSTOM = "_$!<Custom>!$_";
    private static final String BIRTHDAY = "_$!<Birthday>!$_";
    private static final String BLOG = "_$!<Blog>!$_";
    private static final String FTP = "_$!<Ftp>!$_";
    private static final String PROFILE = "_$!<Profile>!$_";
    private static final String I_CLOUD = "iCloud";
    private static final String IPHONE = "iPhone";
    private static final String IM_QQ = "QQ";
    private static final String IM_SKYPE = "Skype";
    private static final String IM_ICQ = "ICQ";
    private static final String IM_JABBER = "Jabber";
    private static final String IM_MSN = "MSN";
    private static final String IM_NET_MEETING = "NETMEETING";
    private static final String IM_YAHOO = "Yahoo";
    private static final String IM_AIM = "AIM";
    private static final String IM_GOOGLE_TALK = "GOOGLE_TALK";
    private static final String IM_CUSTOM = "Custom";
    private static final String LABEL_DEFULT = "自定义";


    @Nullable
    static String getDisplayLabel(@Nullable String label, @Nullable String displayLabel) {
        String display;
        switch (label != null ? label : "") {
            case IM_JABBER:
                display = "Jabber";
                break;
            case WORK_FAX:
                display = "工作传真";
                break;
            case SISTER:
                display = "姐妹";
                break;
            case PARENT:
                display = "父母";
                break;
            case I_CLOUD:
                display = "iCloud";
                break;
            case PAGER:
                display = "传呼机";
                break;
            case IPHONE:
                display = "iPhone";
                break;
            case ASSISTANT:
                display = "助理";
                break;
            case HOMEPAGE:
                display = "主页";
                break;
            case IM_NET_MEETING:
                display = "NETMEETING";
                break;
            case HOME:
                display = "家庭";
                break;
            case SON:
                display = "儿子";
                break;
            case DAUGHTER:
                display = "女儿";
                break;
            case IM_QQ:
                display = "QQ";
                break;
            case IM_AIM:
                display = "AIM";
                break;
            case IM_ICQ:
                display = "ICQ";
                break;
            case IM_MSN:
                display = "MSN";
                break;
            case IM_SKYPE:
                display = "Skype";
                break;
            case IM_YAHOO:
                display = "Yahoo";
                break;
            case MAIN:
                display = "主要";
                break;
            case OTHER:
                display = "其他";
                break;
            case MANAGER:
                display = "上司";
                break;
            case WORK:
                display = "工作";
                break;
            case MOBILE:
                display = "手机";
                break;
            case ANNIVERSARY:
                display = "纪念日";
                break;
            case MOTHER:
                display = "母亲";
                break;
            case PARTNER:
                display = "伴侣";
                break;
            case FRIEND:
                display = "朋友";
                break;
            case SPOUSE:
                display = "配偶";
                break;
            case BROTHER:
                display = "兄弟";
                break;
            case IM_GOOGLE_TALK:
                display = "GOOGLE_TALK";
                break;
            case FATHER:
                display = "父亲";
                break;
            case CHILD:
                display = "子女";
                break;
            case HOME_FAX:
                display = "住宅传真";
                break;
            case CUSTOM:
            case IM_CUSTOM:
            default:
                display = displayLabel;
                break;
        }
        return display;
    }

    static int getRelationType(String label) {
        byte var10000;
        switch (label != null ? label : "") {
            case SISTER:
                var10000 = Relation.TYPE_SISTER;
                break;
            case PARENT:
                var10000 = Relation.TYPE_PARENT;
                break;
            case ASSISTANT:
                var10000 = Relation.TYPE_ASSISTANT;
                break;
            case MANAGER:
                var10000 = Relation.TYPE_MANAGER;
                break;
            case MOTHER:
                var10000 = Relation.TYPE_MOTHER;
                break;
            case PARTNER:
                var10000 = Relation.TYPE_PARTNER;
                break;
            case FRIEND:
                var10000 = Relation.TYPE_FRIEND;
                break;
            case SPOUSE:
                var10000 = Relation.TYPE_SPOUSE;
                break;
            case BROTHER:
                var10000 = Relation.TYPE_BROTHER;
                break;
            case FATHER:
                var10000 = Relation.TYPE_FATHER;
                break;
            case CHILD:
                var10000 = Relation.TYPE_CHILD;
                break;
            case SON:
            case DAUGHTER:
            case OTHER:
            default:
                var10000 = Relation.TYPE_CUSTOM;
                break;
        }
        return var10000;
    }

    static String getRelationLabel(int type) {
        String var10000;
        switch (type) {
            case Relation.TYPE_ASSISTANT:
                var10000 = ASSISTANT;
                break;
            case Relation.TYPE_BROTHER:
                var10000 = BROTHER;
                break;
            case Relation.TYPE_CHILD:
                var10000 = CHILD;
                break;
            case Relation.TYPE_FATHER:
                var10000 = FATHER;
                break;
            case Relation.TYPE_FRIEND:
                var10000 = FRIEND;
                break;
            case Relation.TYPE_MANAGER:
                var10000 = MANAGER;
                break;
            case Relation.TYPE_MOTHER:
                var10000 = MOTHER;
                break;
            case Relation.TYPE_PARENT:
                var10000 = PARENT;
                break;
            case Relation.TYPE_PARTNER:
                var10000 = PARTNER;
                break;
            case Relation.TYPE_SISTER:
                var10000 = SISTER;
                break;
            case Relation.TYPE_SPOUSE:
                var10000 = SPOUSE;
                break;
            default:
                var10000 = LABEL_DEFULT;
                break;
        }

        return var10000;
    }

    static int getPhoneType(String label) {
        byte var10000 = Phone.TYPE_CUSTOM;
        if (label != null) {
            switch (label) {
                case WORK_FAX:
                    var10000 = Phone.TYPE_FAX_WORK;
                    break;
                case WORK_PAGER:
                    var10000 = Phone.TYPE_WORK_PAGER;
                    break;
                case PAGER:
                    var10000 = Phone.TYPE_PAGER;
                    break;
                case ASSISTANT:
                    var10000 = Phone.TYPE_ASSISTANT;
                    break;
                case HOME:
                    var10000 = Phone.TYPE_HOME;
                    break;
                case MAIN:
                    var10000 = Phone.TYPE_MAIN;
                    break;
                case OTHER:
                    var10000 = Phone.TYPE_OTHER;
                    break;
                case WORK:
                    var10000 = Phone.TYPE_WORK;
                    break;
                case OTHER_FAX:
                    var10000 = Phone.TYPE_OTHER_FAX;
                    break;
                case MOBILE:
                    var10000 = Phone.TYPE_MOBILE;
                    break;
                case HOME_FAX:
                    var10000 = Phone.TYPE_FAX_HOME;
                    break;
                case CUSTOM:
                default:
                    var10000 = Phone.TYPE_CUSTOM;

            }
        }
        return var10000;
    }

    static String getPhoneLabel(int type) {
        String var10000;
        switch (type) {
            case Phone.TYPE_HOME:
                var10000 = HOME;
                break;
            case Phone.TYPE_MOBILE:
                var10000 = MOBILE;
                break;
            case Phone.TYPE_WORK:
                var10000 = WORK;
                break;
            case Phone.TYPE_FAX_WORK:
                var10000 = WORK_FAX;
                break;
            case Phone.TYPE_FAX_HOME:
                var10000 = HOME_FAX;
                break;
            case Phone.TYPE_PAGER:
                var10000 = PAGER;
                break;
            case Phone.TYPE_OTHER:
                var10000 = OTHER;
                break;
            case Phone.TYPE_MAIN:
                var10000 = MAIN;
                break;
            case Phone.TYPE_OTHER_FAX:
                var10000 = OTHER_FAX;
                break;
            case Phone.TYPE_WORK_PAGER:
                var10000 = WORK_PAGER;
                break;
            case Phone.TYPE_ASSISTANT:
                var10000 = ASSISTANT;
                break;
            case Phone.TYPE_CUSTOM:
            default:
                var10000 = LABEL_DEFULT;
                break;
        }

        return var10000;
    }

    static int getImProtocol(String label) {
        byte var10000 = Im.PROTOCOL_CUSTOM;
        if (label != null) {
            switch (label) {
                case IM_JABBER:
                    var10000 = Im.PROTOCOL_JABBER;
                    break;
                case IM_NET_MEETING:
                    var10000 = Im.PROTOCOL_NETMEETING;
                    break;
                case IM_QQ:
                    var10000 = Im.PROTOCOL_QQ;
                    break;
                case IM_AIM:
                    var10000 = Im.PROTOCOL_AIM;
                    break;
                case IM_ICQ:
                    var10000 = Im.PROTOCOL_ICQ;
                    break;
                case IM_MSN:
                    var10000 = Im.PROTOCOL_MSN;
                    break;
                case IM_SKYPE:
                    var10000 = Im.PROTOCOL_SKYPE;
                    break;
                case IM_YAHOO:
                    var10000 = Im.PROTOCOL_YAHOO;
                    break;
                case IM_GOOGLE_TALK:
                    var10000 = Im.PROTOCOL_GOOGLE_TALK;
                    break;
                case CUSTOM:
                default:
                    var10000 = Im.PROTOCOL_CUSTOM;
                    break;
            }
        }
        return var10000;
    }

    static String getImLabel(int type) {
        String var10000;
        switch (type) {
            case Im.PROTOCOL_AIM:
                var10000 = IM_AIM;
                break;
            case Im.PROTOCOL_MSN:
                var10000 = IM_MSN;
                break;
            case Im.PROTOCOL_YAHOO:
                var10000 = IM_YAHOO;
                break;
            case Im.PROTOCOL_SKYPE:
                var10000 = IM_SKYPE;
                break;
            case Im.PROTOCOL_QQ:
                var10000 = IM_QQ;
                break;
            case Im.PROTOCOL_GOOGLE_TALK:
                var10000 = IM_GOOGLE_TALK;
                break;
            case Im.PROTOCOL_ICQ:
                var10000 = IM_ICQ;
                break;
            case Im.PROTOCOL_JABBER:
                var10000 = IM_JABBER;
                break;
            case Im.PROTOCOL_NETMEETING:
                var10000 = IM_NET_MEETING;
                break;
            case Im.PROTOCOL_CUSTOM:
            default:
                var10000 = IM_CUSTOM;
        }

        return var10000;
    }

    static int getPostalAddresseType(String label) {
        byte var10000;
        switch (label != null ? label : "") {
            case HOME:
                var10000 = StructuredPostal.TYPE_HOME;
                break;
            case OTHER:
                var10000 = StructuredPostal.TYPE_OTHER;
                break;
            case WORK:
                var10000 = StructuredPostal.TYPE_WORK;
                break;
            case CUSTOM:
            default:
                var10000 = StructuredPostal.TYPE_CUSTOM;
                break;
        }
        return var10000;
    }

    static String getPostalAddressesLabel(int type) {
        String var10000;
        switch (type) {
            case StructuredPostal.TYPE_CUSTOM:
                var10000 = CUSTOM;
                break;
            case StructuredPostal.TYPE_HOME:
                var10000 = HOME;
                break;
            case StructuredPostal.TYPE_WORK:
                var10000 = WORK;
                break;
            case StructuredPostal.TYPE_OTHER:
                var10000 = OTHER;
                break;
            default:
                var10000 = LABEL_DEFULT;
        }

        return var10000;
    }

    static int getUrlType(String label) {
        byte var10000;
        switch (label != null ? label : "") {
            case HOMEPAGE:
                var10000 = Website.TYPE_HOMEPAGE;
                break;
            case BLOG:
                var10000 = Website.TYPE_BLOG;
                break;
            case HOME:
                var10000 = Website.TYPE_HOME;
                break;
            case OTHER:
                var10000 = Website.TYPE_OTHER;
                break;
            case WORK:
                var10000 = Website.TYPE_WORK;
                break;
            case PROFILE:
                var10000 = Website.TYPE_PROFILE;
                break;
            case FTP:
                var10000 = Website.TYPE_FTP;
                break;
            case CUSTOM:
            default:
                var10000 = Website.TYPE_CUSTOM;
                break;
        }
        return var10000;
    }

    static String getUrlLabel(int type) {
        String var10000;
        switch (type) {
            case Website.TYPE_CUSTOM:
                var10000 = CUSTOM;
                break;
            case Website.TYPE_HOMEPAGE:
                var10000 = HOMEPAGE;
                break;
            case Website.TYPE_BLOG:
                var10000 = BLOG;
                break;
            case Website.TYPE_PROFILE:
                var10000 = PROFILE;
                break;
            case Website.TYPE_HOME:
                var10000 = HOME;
                break;
            case Website.TYPE_WORK:
                var10000 = WORK;
                break;
            case Website.TYPE_FTP:
                var10000 = FTP;
                break;
            case Website.TYPE_OTHER:
                var10000 = OTHER;
                break;
            default:
                var10000 = LABEL_DEFULT;
        }

        return var10000;
    }

    static int getEmailType(String label) {
        byte var10000;
        switch (label != null ? label : "") {
            case HOME:
                var10000 = Email.TYPE_HOME;
                break;
            case OTHER:
                var10000 = Email.TYPE_OTHER;
                break;
            case WORK:
                var10000 = Email.TYPE_WORK;
                break;
            case MOBILE:
                var10000 = Email.TYPE_MOBILE;
                break;
            case CUSTOM:
            default:
                var10000 = Email.TYPE_CUSTOM;
                break;
        }
        return var10000;
    }

    static String getEmailLabel(int type) {
        String var10000;
        switch (type) {
            case Email.TYPE_CUSTOM:
                var10000 = CUSTOM;
                break;
            case Email.TYPE_HOME:
                var10000 = HOME;
                break;
            case Email.TYPE_WORK:
                var10000 = WORK;
                break;
            case Email.TYPE_OTHER:
                var10000 = OTHER;
                break;
            case Email.TYPE_MOBILE:
                var10000 = MOBILE;
                break;
            default:
                var10000 = LABEL_DEFULT;
        }

        return var10000;
    }

    static int getEventType(String label) {
        byte var10000;
        switch (label != null ? label : "") {
            case BIRTHDAY:
                var10000 = Event.TYPE_BIRTHDAY;
                break;
            case OTHER:
                var10000 = Event.TYPE_OTHER;
                break;
            case ANNIVERSARY:
                var10000 = Event.TYPE_ANNIVERSARY;
                break;
            case CUSTOM:
            default:
                var10000 = Event.TYPE_CUSTOM;
        }
        return var10000;
    }

    static String getEventLabel(int type) {
        String var10000;
        switch (type) {
            case Event.TYPE_CUSTOM:
                var10000 = CUSTOM;
                break;
            case Event.TYPE_ANNIVERSARY:
                var10000 = ANNIVERSARY;
                break;
            case Event.TYPE_OTHER:
                var10000 = OTHER;
                break;
            case Event.TYPE_BIRTHDAY:
                var10000 = BIRTHDAY;
                break;
            default:
                var10000 = LABEL_DEFULT;
        }
        return var10000;
    }
}
