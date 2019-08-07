package com.company.commonlib.contacts.java;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


/**
 * @author dinglaihong
 * @email 18279727279@163.com
 * @date 2019/8/7
 * @des
 */
public class ContactsConverter {
    @NotNull
    public static final String MOTHER = "_$!<Mother>!$_";
    @NotNull
    public static final String FATHER = "_$!<Father>!$_";
    @NotNull
    public static final String PARENT = "_$!<Parent>!$_";
    @NotNull
    public static final String BROTHER = "_$!<Brother>!$_";
    @NotNull
    public static final String SISTER = "_$!<Sister>!$_";
    @NotNull
    public static final String SON = "_$!<Son>!$_";
    @NotNull
    public static final String DAUGHTER = "_$!<Daughter>!$_";
    @NotNull
    public static final String CHILD = "_$!<Child>!$_";
    @NotNull
    public static final String FRIEND = "_$!<Friend>!$_";
    @NotNull
    public static final String SPOUSE = "_$!<Spouse>!$_";
    @NotNull
    public static final String PARTNER = "_$!<Partner>!$_";
    @NotNull
    public static final String ASSISTANT = "_$!<Assistant>!$_";
    @NotNull
    public static final String MANAGER = "_$!<Manager>!$_";
    @NotNull
    public static final String OTHER = "_$!<Other>!$_";
    @NotNull
    public static final String HOME = "_$!<Home>!$_";
    @NotNull
    public static final String WORK = "_$!<Work>!$_";
    @NotNull
    public static final String MOBILE = "_$!<Mobile>!$_";
    @NotNull
    public static final String MAIN = "_$!<Main>!$_";
    @NotNull
    public static final String HOME_FAX = "_$!<HomeFAX>!$_";
    @NotNull
    public static final String WORK_FAX = "_$!<WorkFAX>!$_";
    @NotNull
    public static final String OTHER_FAX = "_$!<OtherFAX>!$_";
    @NotNull
    public static final String PAGER = "_$!<Pager>!$_";
    @NotNull
    public static final String WORK_PAGER = "_$!<WorkPager>!$_";
    @NotNull
    public static final String HOMEPAGE = "_$!<HomePage>!$_";
    @NotNull
    public static final String ANNIVERSARY = "_$!<Anniversary>!$_";
    @NotNull
    public static final String CUSTOM = "_$!<Custom>!$_";
    @NotNull
    public static final String BIRTHDAY = "_$!<Birthday>!$_";
    @NotNull
    public static final String BLOG = "_$!<Blog>!$_";
    @NotNull
    public static final String FTP = "_$!<Ftp>!$_";
    @NotNull
    public static final String PROFILE = "_$!<Profile>!$_";
    @NotNull
    public static final String I_CLOUD = "iCloud";
    @NotNull
    public static final String IPHONE = "iPhone";
    @NotNull
    public static final String IM_QQ = "QQ";
    @NotNull
    public static final String IM_SKYPE = "Skype";
    @NotNull
    public static final String IM_ICQ = "ICQ";
    @NotNull
    public static final String IM_JABBER = "Jabber";
    @NotNull
    public static final String IM_MSN = "MSN";
    @NotNull
    public static final String IM_NET_MEETING = "NETMEETING";
    @NotNull
    public static final String IM_YAHOO = "Yahoo";
    @NotNull
    public static final String IM_AIM = "AIM";
    @NotNull
    public static final String IM_GOOGLE_TALK = "GOOGLE_TALK";
    @NotNull
    public static final String IM_CUSTOM = "Custom";
    @NotNull
    public static final String LABEL_DEFULT = "自定义";


    @Nullable
    public static String getDisplayLabel(@Nullable String label, @Nullable String displayLabel) {
        String var10000;
        label267:
        {
            if (label != null) {
                switch (label.hashCode()) {
                    case -2083811644:
                        if (label.equals("Jabber")) {
                            var10000 = "Jabber";
                            return var10000;
                        }
                        break;
                    case -1977658070:
                        if (label.equals("_$!<WorkFAX>!$_")) {
                            var10000 = "工作传真";
                            return var10000;
                        }
                        break;
                    case -1739405694:
                        if (label.equals("_$!<Sister>!$_")) {
                            var10000 = "姐妹";
                            return var10000;
                        }
                        break;
                    case -1486085624:
                        if (label.equals("_$!<Parent>!$_")) {
                            var10000 = "父母";
                            return var10000;
                        }
                        break;
                    case -1223702708:
                        if (label.equals("iCloud")) {
                            var10000 = "iCloud";
                            return var10000;
                        }
                        break;
                    case -1218486495:
                        if (label.equals("_$!<Pager>!$_")) {
                            var10000 = "传呼机";
                            return var10000;
                        }
                        break;
                    case -1211816315:
                        if (label.equals("iPhone")) {
                            var10000 = "iPhone";
                            return var10000;
                        }
                        break;
                    case -1074447172:
                        if (label.equals("_$!<Assistant>!$_")) {
                            var10000 = "助理";
                            return var10000;
                        }
                        break;
                    case -826844308:
                        if (label.equals("_$!<HomePage>!$_")) {
                            var10000 = "主页";
                            return var10000;
                        }
                        break;
                    case -576261634:
                        if (label.equals("NETMEETING")) {
                            var10000 = "NETMEETING";
                            return var10000;
                        }
                        break;
                    case -278160451:
                        if (label.equals("_$!<Home>!$_")) {
                            var10000 = "家庭";
                            return var10000;
                        }
                        break;
                    case -83559056:
                        if (label.equals("_$!<Son>!$_")) {
                            var10000 = "儿子";
                            return var10000;
                        }
                        break;
                    case -37519002:
                        if (label.equals("_$!<Daughter>!$_")) {
                            var10000 = "女儿";
                            return var10000;
                        }
                        break;
                    case 2592:
                        if (label.equals("QQ")) {
                            var10000 = "QQ";
                            return var10000;
                        }
                        break;
                    case 64805:
                        if (label.equals("AIM")) {
                            var10000 = "AIM";
                            return var10000;
                        }
                        break;
                    case 72311:
                        if (label.equals("ICQ")) {
                            var10000 = "ICQ";
                            return var10000;
                        }
                        break;
                    case 76648:
                        if (label.equals("MSN")) {
                            var10000 = "MSN";
                            return var10000;
                        }
                        break;
                    case 79959734:
                        if (label.equals("Skype")) {
                            var10000 = "Skype";
                            return var10000;
                        }
                        break;
                    case 85186592:
                        if (label.equals("Yahoo")) {
                            var10000 = "Yahoo";
                            return var10000;
                        }
                        break;
                    case 199602071:
                        if (label.equals("_$!<Main>!$_")) {
                            var10000 = "主要";
                            return var10000;
                        }
                        break;
                    case 230129646:
                        if (label.equals("_$!<Other>!$_")) {
                            var10000 = "其他";
                            return var10000;
                        }
                        break;
                    case 232335883:
                        if (label.equals("_$!<Manager>!$_")) {
                            var10000 = "上司";
                            return var10000;
                        }
                        break;
                    case 242877679:
                        if (label.equals("_$!<Work>!$_")) {
                            var10000 = "工作";
                            return var10000;
                        }
                        break;
                    case 932638208:
                        if (label.equals("_$!<Mobile>!$_")) {
                            var10000 = "手机";
                            return var10000;
                        }
                        break;
                    case 1150238830:
                        if (label.equals("_$!<Anniversary>!$_")) {
                            var10000 = "纪念日";
                            return var10000;
                        }
                        break;
                    case 1162551201:
                        if (label.equals("_$!<Mother>!$_")) {
                            var10000 = "母亲";
                            return var10000;
                        }
                        break;
                    case 1167782310:
                        if (label.equals("_$!<Partner>!$_")) {
                            var10000 = "伴侣";
                            return var10000;
                        }
                        break;
                    case 1296894460:
                        if (label.equals("_$!<Friend>!$_")) {
                            var10000 = "朋友";
                            return var10000;
                        }
                        break;
                    case 1388314227:
                        if (label.equals("_$!<Spouse>!$_")) {
                            var10000 = "配偶";
                            return var10000;
                        }
                        break;
                    case 1471867215:
                        if (label.equals("_$!<Custom>!$_")) {
                            break label267;
                        }
                        break;
                    case 1606876574:
                        if (label.equals("_$!<Brother>!$_")) {
                            var10000 = "兄弟";
                            return var10000;
                        }
                        break;
                    case 1847791346:
                        if (label.equals("GOOGLE_TALK")) {
                            var10000 = "GOOGLE_TALK";
                            return var10000;
                        }
                        break;
                    case 2029746065:
                        if (label.equals("Custom")) {
                            break label267;
                        }
                        break;
                    case 2072704442:
                        if (label.equals("_$!<Father>!$_")) {
                            var10000 = "父亲";
                            return var10000;
                        }
                        break;
                    case 2080837626:
                        if (label.equals("_$!<Child>!$_")) {
                            var10000 = "子女";
                            return var10000;
                        }
                        break;
                    case 2082186140:
                        if (label.equals("_$!<HomeFAX>!$_")) {
                            var10000 = "住宅传真";
                            return var10000;
                        }
                }
            }

            var10000 = displayLabel;
            return var10000;
        }

        var10000 = displayLabel;
        return var10000;
    }

    public static int getRelationType(@Nullable String label) {
        byte var10000;
        if (label != null) {
            switch (label.hashCode()) {
                case -1739405694:
                    if (label.equals("_$!<Sister>!$_")) {
                        var10000 = 13;
                        return var10000;
                    }
                    break;
                case -1486085624:
                    if (label.equals("_$!<Parent>!$_")) {
                        var10000 = 9;
                        return var10000;
                    }
                    break;
                case -1074447172:
                    if (label.equals("_$!<Assistant>!$_")) {
                        var10000 = 1;
                        return var10000;
                    }
                    break;
                case -83559056:
                    if (label.equals("_$!<Son>!$_")) {
                        var10000 = 0;
                        return var10000;
                    }
                    break;
                case -37519002:
                    if (label.equals("_$!<Daughter>!$_")) {
                        var10000 = 0;
                        return var10000;
                    }
                    break;
                case 230129646:
                    if (label.equals("_$!<Other>!$_")) {
                        var10000 = 0;
                        return var10000;
                    }
                    break;
                case 232335883:
                    if (label.equals("_$!<Manager>!$_")) {
                        var10000 = 7;
                        return var10000;
                    }
                    break;
                case 1162551201:
                    if (label.equals("_$!<Mother>!$_")) {
                        var10000 = 8;
                        return var10000;
                    }
                    break;
                case 1167782310:
                    if (label.equals("_$!<Partner>!$_")) {
                        var10000 = 10;
                        return var10000;
                    }
                    break;
                case 1296894460:
                    if (label.equals("_$!<Friend>!$_")) {
                        var10000 = 6;
                        return var10000;
                    }
                    break;
                case 1388314227:
                    if (label.equals("_$!<Spouse>!$_")) {
                        var10000 = 14;
                        return var10000;
                    }
                    break;
                case 1606876574:
                    if (label.equals("_$!<Brother>!$_")) {
                        var10000 = 2;
                        return var10000;
                    }
                    break;
                case 2072704442:
                    if (label.equals("_$!<Father>!$_")) {
                        var10000 = 5;
                        return var10000;
                    }
                    break;
                case 2080837626:
                    if (label.equals("_$!<Child>!$_")) {
                        var10000 = 3;
                        return var10000;
                    }
            }
        }

        var10000 = 0;
        return var10000;
    }

    @NotNull
    public static String getRelationLabel(int type) {
        String var10000;
        switch (type) {
            case 0:
                var10000 = "_$!<Mother>!$_";
                break;
            case 1:
                var10000 = "_$!<Assistant>!$_";
                break;
            case 2:
                var10000 = "_$!<Brother>!$_";
                break;
            case 3:
                var10000 = "_$!<Child>!$_";
                break;
            case 4:
            case 11:
            case 12:
            default:
                var10000 = "自定义";
                break;
            case 5:
                var10000 = "_$!<Father>!$_";
                break;
            case 6:
                var10000 = "_$!<Friend>!$_";
                break;
            case 7:
                var10000 = "_$!<Manager>!$_";
                break;
            case 8:
                var10000 = "_$!<Mother>!$_";
                break;
            case 9:
                var10000 = "_$!<Parent>!$_";
                break;
            case 10:
                var10000 = "_$!<Partner>!$_";
                break;
            case 13:
                var10000 = "_$!<Sister>!$_";
                break;
            case 14:
                var10000 = "_$!<Spouse>!$_";
        }

        return var10000;
    }

    public static int getPhoneType(@Nullable String label) {
        byte var10000;
        if (label != null) {
            switch (label.hashCode()) {
                case -1977658070:
                    if (label.equals("_$!<WorkFAX>!$_")) {
                        var10000 = 4;
                        return var10000;
                    }
                    break;
                case -1493468400:
                    if (label.equals("_$!<WorkPager>!$_")) {
                        var10000 = 18;
                        return var10000;
                    }
                    break;
                case -1218486495:
                    if (label.equals("_$!<Pager>!$_")) {
                        var10000 = 6;
                        return var10000;
                    }
                    break;
                case -1074447172:
                    if (label.equals("_$!<Assistant>!$_")) {
                        var10000 = 19;
                        return var10000;
                    }
                    break;
                case -278160451:
                    if (label.equals("_$!<Home>!$_")) {
                        var10000 = 1;
                        return var10000;
                    }
                    break;
                case 199602071:
                    if (label.equals("_$!<Main>!$_")) {
                        var10000 = 12;
                        return var10000;
                    }
                    break;
                case 230129646:
                    if (label.equals("_$!<Other>!$_")) {
                        var10000 = 7;
                        return var10000;
                    }
                    break;
                case 242877679:
                    if (label.equals("_$!<Work>!$_")) {
                        var10000 = 3;
                        return var10000;
                    }
                    break;
                case 497780171:
                    if (label.equals("_$!<OtherFAX>!$_")) {
                        var10000 = 13;
                        return var10000;
                    }
                    break;
                case 932638208:
                    if (label.equals("_$!<Mobile>!$_")) {
                        var10000 = 2;
                        return var10000;
                    }
                    break;
                case 1471867215:
                    if (label.equals("_$!<Custom>!$_")) {
                        var10000 = 0;
                        return var10000;
                    }
                    break;
                case 2082186140:
                    if (label.equals("_$!<HomeFAX>!$_")) {
                        var10000 = 5;
                        return var10000;
                    }
            }
        }

        var10000 = 0;
        return var10000;
    }

    @Nullable
    public static String getPhoneLabel(int type) {
        String var10000;
        switch (type) {
            case 0:
                var10000 = "自定义";
                break;
            case 1:
                var10000 = "_$!<Home>!$_";
                break;
            case 2:
                var10000 = "_$!<Mobile>!$_";
                break;
            case 3:
                var10000 = "_$!<Work>!$_";
                break;
            case 4:
                var10000 = "_$!<WorkFAX>!$_";
                break;
            case 5:
                var10000 = "_$!<HomeFAX>!$_";
                break;
            case 6:
                var10000 = "_$!<Pager>!$_";
                break;
            case 7:
                var10000 = "_$!<Other>!$_";
                break;
            case 8:
            case 9:
            case 10:
            case 11:
            case 14:
            case 15:
            case 16:
            case 17:
            default:
                var10000 = "自定义";
                break;
            case 12:
                var10000 = "_$!<Main>!$_";
                break;
            case 13:
                var10000 = "_$!<OtherFAX>!$_";
                break;
            case 18:
                var10000 = "_$!<WorkPager>!$_";
                break;
            case 19:
                var10000 = "_$!<Assistant>!$_";
        }

        return var10000;
    }

    public static int getImProtocol(@Nullable String label) {
        byte var10000;
        if (label != null) {
            switch (label.hashCode()) {
                case -2083811644:
                    if (label.equals("Jabber")) {
                        var10000 = 7;
                        return var10000;
                    }
                    break;
                case -576261634:
                    if (label.equals("NETMEETING")) {
                        var10000 = 8;
                        return var10000;
                    }
                    break;
                case 2592:
                    if (label.equals("QQ")) {
                        var10000 = 4;
                        return var10000;
                    }
                    break;
                case 64805:
                    if (label.equals("AIM")) {
                        var10000 = 0;
                        return var10000;
                    }
                    break;
                case 72311:
                    if (label.equals("ICQ")) {
                        var10000 = 6;
                        return var10000;
                    }
                    break;
                case 76648:
                    if (label.equals("MSN")) {
                        var10000 = 1;
                        return var10000;
                    }
                    break;
                case 79959734:
                    if (label.equals("Skype")) {
                        var10000 = 3;
                        return var10000;
                    }
                    break;
                case 85186592:
                    if (label.equals("Yahoo")) {
                        var10000 = 2;
                        return var10000;
                    }
                    break;
                case 1847791346:
                    if (label.equals("GOOGLE_TALK")) {
                        var10000 = 5;
                        return var10000;
                    }
                    break;
                case 2029746065:
                    if (label.equals("Custom")) {
                        var10000 = -1;
                        return var10000;
                    }
            }
        }

        var10000 = -1;
        return var10000;
    }

    @Nullable
    public static String getImLabel(int type) {
        String var10000;
        switch (type) {
            case -1:
                var10000 = "Custom";
                break;
            case 0:
                var10000 = "AIM";
                break;
            case 1:
                var10000 = "MSN";
                break;
            case 2:
                var10000 = "Yahoo";
                break;
            case 3:
                var10000 = "Skype";
                break;
            case 4:
                var10000 = "QQ";
                break;
            case 5:
                var10000 = "GOOGLE_TALK";
                break;
            case 6:
                var10000 = "ICQ";
                break;
            case 7:
                var10000 = "Jabber";
                break;
            case 8:
                var10000 = "NETMEETING";
                break;
            default:
                var10000 = "自定义";
        }

        return var10000;
    }

    public static int getPostalAddresseType(@Nullable String label) {
        byte var10000;
        if (label != null) {
            switch (label.hashCode()) {
                case -278160451:
                    if (label.equals("_$!<Home>!$_")) {
                        var10000 = 1;
                        return var10000;
                    }
                    break;
                case 230129646:
                    if (label.equals("_$!<Other>!$_")) {
                        var10000 = 3;
                        return var10000;
                    }
                    break;
                case 242877679:
                    if (label.equals("_$!<Work>!$_")) {
                        var10000 = 2;
                        return var10000;
                    }
                    break;
                case 1471867215:
                    if (label.equals("_$!<Custom>!$_")) {
                        var10000 = 0;
                        return var10000;
                    }
            }
        }

        var10000 = 0;
        return var10000;
    }

    @NotNull
    public static String getPostalAddressesLabel(int type) {
        String var10000;
        switch (type) {
            case 0:
                var10000 = "_$!<Custom>!$_";
                break;
            case 1:
                var10000 = "_$!<Home>!$_";
                break;
            case 2:
                var10000 = "_$!<Work>!$_";
                break;
            case 3:
                var10000 = "_$!<Other>!$_";
                break;
            default:
                var10000 = "自定义";
        }

        return var10000;
    }

    public static int getUrlType(@Nullable String label) {
        byte var10000;
        if (label != null) {
            switch (label.hashCode()) {
                case -826844308:
                    if (label.equals("_$!<HomePage>!$_")) {
                        var10000 = 1;
                        return var10000;
                    }
                    break;
                case -453526272:
                    if (label.equals("_$!<Blog>!$_")) {
                        var10000 = 2;
                        return var10000;
                    }
                    break;
                case -278160451:
                    if (label.equals("_$!<Home>!$_")) {
                        var10000 = 4;
                        return var10000;
                    }
                    break;
                case 230129646:
                    if (label.equals("_$!<Other>!$_")) {
                        var10000 = 7;
                        return var10000;
                    }
                    break;
                case 242877679:
                    if (label.equals("_$!<Work>!$_")) {
                        var10000 = 5;
                        return var10000;
                    }
                    break;
                case 370755079:
                    if (label.equals("_$!<Profile>!$_")) {
                        var10000 = 3;
                        return var10000;
                    }
                    break;
                case 1408787776:
                    if (label.equals("_$!<Ftp>!$_")) {
                        var10000 = 6;
                        return var10000;
                    }
                    break;
                case 1471867215:
                    if (label.equals("_$!<Custom>!$_")) {
                        var10000 = 0;
                        return var10000;
                    }
            }
        }

        var10000 = 0;
        return var10000;
    }

    @NotNull
    public static String getUrlLabel(int type) {
        String var10000;
        switch (type) {
            case 0:
                var10000 = "_$!<Custom>!$_";
                break;
            case 1:
                var10000 = "_$!<HomePage>!$_";
                break;
            case 2:
                var10000 = "_$!<Blog>!$_";
                break;
            case 3:
                var10000 = "_$!<Profile>!$_";
                break;
            case 4:
                var10000 = "_$!<Home>!$_";
                break;
            case 5:
                var10000 = "_$!<Work>!$_";
                break;
            case 6:
                var10000 = "_$!<Ftp>!$_";
                break;
            case 7:
                var10000 = "_$!<Other>!$_";
                break;
            default:
                var10000 = "自定义";
        }

        return var10000;
    }

    public static int getEmailType(@Nullable String label) {
        byte var10000;
        if (label != null) {
            switch (label.hashCode()) {
                case -278160451:
                    if (label.equals("_$!<Home>!$_")) {
                        var10000 = 1;
                        return var10000;
                    }
                    break;
                case 230129646:
                    if (label.equals("_$!<Other>!$_")) {
                        var10000 = 3;
                        return var10000;
                    }
                    break;
                case 242877679:
                    if (label.equals("_$!<Work>!$_")) {
                        var10000 = 2;
                        return var10000;
                    }
                    break;
                case 932638208:
                    if (label.equals("_$!<Mobile>!$_")) {
                        var10000 = 4;
                        return var10000;
                    }
                    break;
                case 1471867215:
                    if (label.equals("_$!<Custom>!$_")) {
                        var10000 = 0;
                        return var10000;
                    }
            }
        }

        var10000 = 0;
        return var10000;
    }

    @NotNull
    public static String getEmailLabel(int type) {
        String var10000;
        switch (type) {
            case 0:
                var10000 = "_$!<Custom>!$_";
                break;
            case 1:
                var10000 = "_$!<Home>!$_";
                break;
            case 2:
                var10000 = "_$!<Work>!$_";
                break;
            case 3:
                var10000 = "_$!<Other>!$_";
                break;
            case 4:
                var10000 = "_$!<Mobile>!$_";
                break;
            default:
                var10000 = "自定义";
        }

        return var10000;
    }

    public static int getEventType(@Nullable String label) {
        byte var10000;
        if (label != null) {
            switch (label.hashCode()) {
                case -347166117:
                    if (label.equals("_$!<Birthday>!$_")) {
                        var10000 = 3;
                        return var10000;
                    }
                    break;
                case 230129646:
                    if (label.equals("_$!<Other>!$_")) {
                        var10000 = 2;
                        return var10000;
                    }
                    break;
                case 1150238830:
                    if (label.equals("_$!<Anniversary>!$_")) {
                        var10000 = 1;
                        return var10000;
                    }
                    break;
                case 1471867215:
                    if (label.equals("_$!<Custom>!$_")) {
                        var10000 = 0;
                        return var10000;
                    }
            }
        }

        var10000 = 0;
        return var10000;
    }

    @NotNull
    public static String getEventLabel(int type) {
        String var10000;
        switch (type) {
            case 0:
                var10000 = "_$!<Custom>!$_";
                break;
            case 1:
                var10000 = "_$!<Anniversary>!$_";
                break;
            case 2:
                var10000 = "_$!<Other>!$_";
                break;
            case 3:
                var10000 = "_$!<Birthday>!$_";
                break;
            default:
                var10000 = "自定义";
        }

        return var10000;
    }
}
