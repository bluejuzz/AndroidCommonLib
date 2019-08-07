package com.company.commonlib.contacts.java;

import java.util.List;

/**
 * @author dinglaihong
 * @email 18279727279@163.com
 * @date 2019/8/7
 * @des
 */
public class ContactsBackupBean {

    /**
     * name :
     * contacts : [{"familyName":"周","nonGregorianBirthday":{"caledarIdentifier":2,"year":34,"month":5,"day":26},"birthday":{"month":7,"day":29,"caledarIdentifier":0},"contactRelations":[{"label":"_$!<Mother>!$_","relation":{"name":"荷花"},"displayLabel":"母亲","identifier":"8B4EBB10-9DE2-421B-9276-55E793497714"},{"label":"_$!<Father>!$_","relation":{"name":"周览"},"displayLabel":"父亲","identifier":"F8B70E89-5666-419F-9AED-5DBB7CC7E769"},{"label":"_$!<Manager>!$_","relation":{"name":"大师"},"displayLabel":"上司","identifier":"A21A6722-1C6E-484F-A748-EF8960E6CF89"}],"nickname":"虎子","phoneticOrganizationName":"Huáwèi","organizationName":"华为","departmentName":"市场","imageData":"","namePrefix":"娃娃","nameSuffix":"金喜","socialProfiles":[{"label":"sinaweibo","social":{"username":"eryut@ttiio.von","userIdentifier":"","service":"SinaWeibo","urlString":"http://weibo.com/n/eryut@ttiio.von"},"displayLabel":"sinaweibo","identifier":"625EB6F0-AC4D-44BF-933B-9EF3B0CCB797"}],"dates":[{"label":"_$!<Anniversary>$_","displayLabel":"纪念日","identifier":"A4E2D01F-3325-4022-8208-36DD5137338C","date":{"caledarIdentifier":0,"year":2015,"month":7,"day":29}},{"label":"_$!<Other>!$_","displayLabel":"其他","identifier":"0ADAFB04-ADF5-411A-A7C1-FC2574DB2DBB","date":{"caledarIdentifier":0,"year":2002,"month":5,"day":20}}],"phoneNumbers":[{"label":"_$!<Home>!$_","displayLabel":"家庭","identifier":"7D4A1481-99AB-4C28-8D2F-4C402BA9CEF4","phoneNumber":{"number":"16784546487"}},{"label":"_$!<Work>!$_","displayLabel":"工作","identifier":"0C85CBDC-0D79-499C-A21E-23544836C0B1","phoneNumber":{"number":"123456"}},{"label":"iPhone","displayLabel":"iPhone","identifier":"C53973F2-CE7D-41D2-91F6-F961E26C0C65","phoneNumber":{"number":"789 123"}},{"label":"_$!<WorkFAX>!$_","displayLabel":"工作传真","identifier":"0526C389-57DE-48E7-90FE-63FC19974DE7","phoneNumber":{"number":"2164 6979"}}],"identifier":"F442C74B-22DD-4BCF-868E-E7AD4B6187D8:ABPerson","urlAddresses":[{"label":"_$!<HomePage>!$_","displayLabel":"主页","identifier":"A7F19D42-0148-4E96-8ACF-A2A4D26414C8","url":"http://www.baidu.com"},{"label":"_$!<Work>!$_","displayLabel":"工作","identifier":"ADCE28BB-4DFD-4901-B268-518C51A1CC6C","url":"http://www.i4.cn"}],"postalAddresses":[{"label":"_$!<Home>!$_","displayLabel":"家庭","identifier":"B1D5C2D5-72C7-4F2B-B41F-21743E3B55F0","address":{"street":"西丽桃源 ","subLocality":"南山区","country":"中国大陆","postalCode":"518000","city":"深圳","subAdministrativeArea":"","state":"广东","isoCountryCode":"cn"}},{"label":"_$!<Work>!$_","displayLabel":"工作","identifier":"4CACE207-1E14-438C-AAF4-A889CD8EFC77","address":{"street":"天河","subLocality":"白云","country":"中国大陆","postalCode":"277372","city":"广州","subAdministrativeArea":"","state":"广东","isoCountryCode":"cn"}}],"groups":[],"middleName":"小","jobTitle":"销售代表","contactType":0,"phoneticMiddleName":"Xiǎo","note":"腹股沟","phoneticGivenName":"Xiǎohuā","phoneticFamilyName":"Zhōu","emailAddresses":[{"email":"asd@co.com","label":"_$!<Home>!$_","displayLabel":"家庭","identifier":"3006A776-AAF0-44DF-A4F7-1F8B1C84323B"},{"email":"7728383@qq.com","label":"_$!<Work>!$_","displayLabel":"工作","identifier":"9E08CBCD-937A-4A87-857D-10EA9D952C95"},{"email":"6277282@163.com","label":"iCloud","displayLabel":"iCloud","identifier":"D8DCCDD2-F743-4917-A55A-65425C87FB63"}],"givenName":"小花","instantMessageAddresses":[{"label":"QQ","displayLabel":"QQ","identifier":"B0C26A0C-403D-4850-BE9A-A29C99610544","service":{"service":"QQ","username":"6468843"}},{"label":"Skype","displayLabel":"Skype","identifier":"40311C8C-6368-46D2-BF5D-173F63A5F597","service":{"service":"Skype","username":"ghjkg"}},{"label":"旺旺","displayLabel":"旺旺","identifier":"256BE51E-C460-4DA5-B616-993BF5945040","service":{"service":"旺旺","username":"并不难"}}],"previousFamilyName":"朴"}]
     * identifier : 51DF4D5D-C00E-4386-9DB7-A702E1D18FF1
     */

    private String name;
    private String identifier;
    private List<ContactsBean> contacts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public List<ContactsBean> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactsBean> contacts) {
        this.contacts = contacts;
    }

    public static class ContactsBean {
        /**
         * familyName : 周
         * nonGregorianBirthday : {"caledarIdentifier":2,"year":34,"month":5,"day":26}
         * birthday : {"month":7,"day":29,"caledarIdentifier":0}
         * contactRelations : [{"label":"_$!<Mother>!$_","relation":{"name":"荷花"},"displayLabel":"母亲","identifier":"8B4EBB10-9DE2-421B-9276-55E793497714"},{"label":"_$!<Father>!$_","relation":{"name":"周览"},"displayLabel":"父亲","identifier":"F8B70E89-5666-419F-9AED-5DBB7CC7E769"},{"label":"_$!<Manager>!$_","relation":{"name":"大师"},"displayLabel":"上司","identifier":"A21A6722-1C6E-484F-A748-EF8960E6CF89"}]
         * nickname : 虎子
         * phoneticOrganizationName : Huáwèi
         * organizationName : 华为
         * departmentName : 市场
         * imageData :
         * namePrefix : 娃娃
         * nameSuffix : 金喜
         * socialProfiles : [{"label":"sinaweibo","social":{"username":"eryut@ttiio.von","userIdentifier":"","service":"SinaWeibo","urlString":"http://weibo.com/n/eryut@ttiio.von"},"displayLabel":"sinaweibo","identifier":"625EB6F0-AC4D-44BF-933B-9EF3B0CCB797"}]
         * dates : [{"label":"_$!<Anniversary>$_","displayLabel":"纪念日","identifier":"A4E2D01F-3325-4022-8208-36DD5137338C","date":{"caledarIdentifier":0,"year":2015,"month":7,"day":29}},{"label":"_$!<Other>!$_","displayLabel":"其他","identifier":"0ADAFB04-ADF5-411A-A7C1-FC2574DB2DBB","date":{"caledarIdentifier":0,"year":2002,"month":5,"day":20}}]
         * phoneNumbers : [{"label":"_$!<Home>!$_","displayLabel":"家庭","identifier":"7D4A1481-99AB-4C28-8D2F-4C402BA9CEF4","phoneNumber":{"number":"16784546487"}},{"label":"_$!<Work>!$_","displayLabel":"工作","identifier":"0C85CBDC-0D79-499C-A21E-23544836C0B1","phoneNumber":{"number":"123456"}},{"label":"iPhone","displayLabel":"iPhone","identifier":"C53973F2-CE7D-41D2-91F6-F961E26C0C65","phoneNumber":{"number":"789 123"}},{"label":"_$!<WorkFAX>!$_","displayLabel":"工作传真","identifier":"0526C389-57DE-48E7-90FE-63FC19974DE7","phoneNumber":{"number":"2164 6979"}}]
         * identifier : F442C74B-22DD-4BCF-868E-E7AD4B6187D8:ABPerson
         * urlAddresses : [{"label":"_$!<HomePage>!$_","displayLabel":"主页","identifier":"A7F19D42-0148-4E96-8ACF-A2A4D26414C8","url":"http://www.baidu.com"},{"label":"_$!<Work>!$_","displayLabel":"工作","identifier":"ADCE28BB-4DFD-4901-B268-518C51A1CC6C","url":"http://www.i4.cn"}]
         * postalAddresses : [{"label":"_$!<Home>!$_","displayLabel":"家庭","identifier":"B1D5C2D5-72C7-4F2B-B41F-21743E3B55F0","address":{"street":"西丽桃源 ","subLocality":"南山区","country":"中国大陆","postalCode":"518000","city":"深圳","subAdministrativeArea":"","state":"广东","isoCountryCode":"cn"}},{"label":"_$!<Work>!$_","displayLabel":"工作","identifier":"4CACE207-1E14-438C-AAF4-A889CD8EFC77","address":{"street":"天河","subLocality":"白云","country":"中国大陆","postalCode":"277372","city":"广州","subAdministrativeArea":"","state":"广东","isoCountryCode":"cn"}}]
         * groups : []
         * middleName : 小
         * jobTitle : 销售代表
         * contactType : 0
         * phoneticMiddleName : Xiǎo
         * note : 腹股沟
         * phoneticGivenName : Xiǎohuā
         * phoneticFamilyName : Zhōu
         * emailAddresses : [{"email":"asd@co.com","label":"_$!<Home>!$_","displayLabel":"家庭","identifier":"3006A776-AAF0-44DF-A4F7-1F8B1C84323B"},{"email":"7728383@qq.com","label":"_$!<Work>!$_","displayLabel":"工作","identifier":"9E08CBCD-937A-4A87-857D-10EA9D952C95"},{"email":"6277282@163.com","label":"iCloud","displayLabel":"iCloud","identifier":"D8DCCDD2-F743-4917-A55A-65425C87FB63"}]
         * givenName : 小花
         * instantMessageAddresses : [{"label":"QQ","displayLabel":"QQ","identifier":"B0C26A0C-403D-4850-BE9A-A29C99610544","service":{"service":"QQ","username":"6468843"}},{"label":"Skype","displayLabel":"Skype","identifier":"40311C8C-6368-46D2-BF5D-173F63A5F597","service":{"service":"Skype","username":"ghjkg"}},{"label":"旺旺","displayLabel":"旺旺","identifier":"256BE51E-C460-4DA5-B616-993BF5945040","service":{"service":"旺旺","username":"并不难"}}]
         * previousFamilyName : 朴
         */

        private String familyName;
        private String displayName;
        private NonGregorianBirthdayBean nonGregorianBirthday;
        private BirthdayBean birthday;
        private String nickname;
        private String phoneticOrganizationName;
        private String organizationName;
        private String departmentName;
        private String imageData;
        private String namePrefix;
        private String nameSuffix;
        private String identifier;
        private String middleName;
        private String jobTitle;
        private int contactType;
        private String phoneticMiddleName;
        private String note;
        private String phoneticGivenName;
        private String phoneticFamilyName;
        private String givenName;
        private String previousFamilyName;
        private List<ContactRelationsBean> contactRelations;
        private List<SocialProfilesBean> socialProfiles;
        private List<DatesBean> dates;
        private List<PhoneNumbersBean> phoneNumbers;
        private List<UrlAddressesBean> urlAddresses;
        private List<PostalAddressesBean> postalAddresses;
        private List<?> groups;
        private List<EmailAddressesBean> emailAddresses;
        private List<InstantMessageAddressesBean> instantMessageAddresses;

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public String getFamilyName() {
            return familyName;
        }

        public void setFamilyName(String familyName) {
            this.familyName = familyName;
        }

        public NonGregorianBirthdayBean getNonGregorianBirthday() {
            return nonGregorianBirthday;
        }

        public void setNonGregorianBirthday(NonGregorianBirthdayBean nonGregorianBirthday) {
            this.nonGregorianBirthday = nonGregorianBirthday;
        }

        public BirthdayBean getBirthday() {
            return birthday;
        }

        public void setBirthday(BirthdayBean birthday) {
            this.birthday = birthday;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPhoneticOrganizationName() {
            return phoneticOrganizationName;
        }

        public void setPhoneticOrganizationName(String phoneticOrganizationName) {
            this.phoneticOrganizationName = phoneticOrganizationName;
        }

        public String getOrganizationName() {
            return organizationName;
        }

        public void setOrganizationName(String organizationName) {
            this.organizationName = organizationName;
        }

        public String getDepartmentName() {
            return departmentName;
        }

        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
        }

        public String getImageData() {
            return imageData;
        }

        public void setImageData(String imageData) {
            this.imageData = imageData;
        }

        public String getNamePrefix() {
            return namePrefix;
        }

        public void setNamePrefix(String namePrefix) {
            this.namePrefix = namePrefix;
        }

        public String getNameSuffix() {
            return nameSuffix;
        }

        public void setNameSuffix(String nameSuffix) {
            this.nameSuffix = nameSuffix;
        }

        public String getIdentifier() {
            return identifier;
        }

        public void setIdentifier(String identifier) {
            this.identifier = identifier;
        }

        public String getMiddleName() {
            return middleName;
        }

        public void setMiddleName(String middleName) {
            this.middleName = middleName;
        }

        public String getJobTitle() {
            return jobTitle;
        }

        public void setJobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
        }

        public int getContactType() {
            return contactType;
        }

        public void setContactType(int contactType) {
            this.contactType = contactType;
        }

        public String getPhoneticMiddleName() {
            return phoneticMiddleName;
        }

        public void setPhoneticMiddleName(String phoneticMiddleName) {
            this.phoneticMiddleName = phoneticMiddleName;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getPhoneticGivenName() {
            return phoneticGivenName;
        }

        public void setPhoneticGivenName(String phoneticGivenName) {
            this.phoneticGivenName = phoneticGivenName;
        }

        public String getPhoneticFamilyName() {
            return phoneticFamilyName;
        }

        public void setPhoneticFamilyName(String phoneticFamilyName) {
            this.phoneticFamilyName = phoneticFamilyName;
        }

        public String getGivenName() {
            return givenName;
        }

        public void setGivenName(String givenName) {
            this.givenName = givenName;
        }

        public String getPreviousFamilyName() {
            return previousFamilyName;
        }

        public void setPreviousFamilyName(String previousFamilyName) {
            this.previousFamilyName = previousFamilyName;
        }

        public List<ContactRelationsBean> getContactRelations() {
            return contactRelations;
        }

        public void setContactRelations(List<ContactRelationsBean> contactRelations) {
            this.contactRelations = contactRelations;
        }

        public List<SocialProfilesBean> getSocialProfiles() {
            return socialProfiles;
        }

        public void setSocialProfiles(List<SocialProfilesBean> socialProfiles) {
            this.socialProfiles = socialProfiles;
        }

        public List<DatesBean> getDates() {
            return dates;
        }

        public void setDates(List<DatesBean> dates) {
            this.dates = dates;
        }

        public List<PhoneNumbersBean> getPhoneNumbers() {
            return phoneNumbers;
        }

        public void setPhoneNumbers(List<PhoneNumbersBean> phoneNumbers) {
            this.phoneNumbers = phoneNumbers;
        }

        public List<UrlAddressesBean> getUrlAddresses() {
            return urlAddresses;
        }

        public void setUrlAddresses(List<UrlAddressesBean> urlAddresses) {
            this.urlAddresses = urlAddresses;
        }

        public List<PostalAddressesBean> getPostalAddresses() {
            return postalAddresses;
        }

        public void setPostalAddresses(List<PostalAddressesBean> postalAddresses) {
            this.postalAddresses = postalAddresses;
        }

        public List<?> getGroups() {
            return groups;
        }

        public void setGroups(List<?> groups) {
            this.groups = groups;
        }

        public List<EmailAddressesBean> getEmailAddresses() {
            return emailAddresses;
        }

        public void setEmailAddresses(List<EmailAddressesBean> emailAddresses) {
            this.emailAddresses = emailAddresses;
        }

        public List<InstantMessageAddressesBean> getInstantMessageAddresses() {
            return instantMessageAddresses;
        }

        public void setInstantMessageAddresses(List<InstantMessageAddressesBean> instantMessageAddresses) {
            this.instantMessageAddresses = instantMessageAddresses;
        }

        public static class NonGregorianBirthdayBean {
            /**
             * caledarIdentifier : 2
             * year : 34
             * month : 5
             * day : 26
             */

            private int caledarIdentifier;
            private int year;
            private int month;
            private int day;

            public int getCaledarIdentifier() {
                return caledarIdentifier;
            }

            public void setCaledarIdentifier(int caledarIdentifier) {
                this.caledarIdentifier = caledarIdentifier;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }
        }

        public static class BirthdayBean {
            /**
             * month : 7
             * day : 29
             * caledarIdentifier : 0
             */

            private int month;
            private int day;
            private int caledarIdentifier;

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }

            public int getCaledarIdentifier() {
                return caledarIdentifier;
            }

            public void setCaledarIdentifier(int caledarIdentifier) {
                this.caledarIdentifier = caledarIdentifier;
            }
        }

        public static class ContactRelationsBean {
            /**
             * label : _$!<Mother>!$_
             * relation : {"name":"荷花"}
             * displayLabel : 母亲
             * identifier : 8B4EBB10-9DE2-421B-9276-55E793497714
             */

            private String label;
            private RelationBean relation;
            private String displayLabel;
            private String identifier;

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public RelationBean getRelation() {
                return relation;
            }

            public void setRelation(RelationBean relation) {
                this.relation = relation;
            }

            public String getDisplayLabel() {
                return displayLabel;
            }

            public void setDisplayLabel(String displayLabel) {
                this.displayLabel = displayLabel;
            }

            public String getIdentifier() {
                return identifier;
            }

            public void setIdentifier(String identifier) {
                this.identifier = identifier;
            }

            public static class RelationBean {
                /**
                 * name : 荷花
                 */

                private String name;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }

        public static class SocialProfilesBean {
            /**
             * label : sinaweibo
             * social : {"username":"eryut@ttiio.von","userIdentifier":"","service":"SinaWeibo","urlString":"http://weibo.com/n/eryut@ttiio.von"}
             * displayLabel : sinaweibo
             * identifier : 625EB6F0-AC4D-44BF-933B-9EF3B0CCB797
             */

            private String label;
            private SocialBean social;
            private String displayLabel;
            private String identifier;

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public SocialBean getSocial() {
                return social;
            }

            public void setSocial(SocialBean social) {
                this.social = social;
            }

            public String getDisplayLabel() {
                return displayLabel;
            }

            public void setDisplayLabel(String displayLabel) {
                this.displayLabel = displayLabel;
            }

            public String getIdentifier() {
                return identifier;
            }

            public void setIdentifier(String identifier) {
                this.identifier = identifier;
            }

            public static class SocialBean {
                /**
                 * username : eryut@ttiio.von
                 * userIdentifier :
                 * service : SinaWeibo
                 * urlString : http://weibo.com/n/eryut@ttiio.von
                 */

                private String username;
                private String userIdentifier;
                private String service;
                private String urlString;

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public String getUserIdentifier() {
                    return userIdentifier;
                }

                public void setUserIdentifier(String userIdentifier) {
                    this.userIdentifier = userIdentifier;
                }

                public String getService() {
                    return service;
                }

                public void setService(String service) {
                    this.service = service;
                }

                public String getUrlString() {
                    return urlString;
                }

                public void setUrlString(String urlString) {
                    this.urlString = urlString;
                }
            }
        }

        public static class DatesBean {
            /**
             * label : _$!<Anniversary>$_
             * displayLabel : 纪念日
             * identifier : A4E2D01F-3325-4022-8208-36DD5137338C
             * date : {"caledarIdentifier":0,"year":2015,"month":7,"day":29}
             */

            private String label;
            private String displayLabel;
            private String identifier;
            private DateBean date;

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getDisplayLabel() {
                return displayLabel;
            }

            public void setDisplayLabel(String displayLabel) {
                this.displayLabel = displayLabel;
            }

            public String getIdentifier() {
                return identifier;
            }

            public void setIdentifier(String identifier) {
                this.identifier = identifier;
            }

            public DateBean getDate() {
                return date;
            }

            public void setDate(DateBean date) {
                this.date = date;
            }

            public static class DateBean {
                /**
                 * caledarIdentifier : 0
                 * year : 2015
                 * month : 7
                 * day : 29
                 */

                private int caledarIdentifier;
                private int year;
                private int month;
                private int day;

                public int getCaledarIdentifier() {
                    return caledarIdentifier;
                }

                public void setCaledarIdentifier(int caledarIdentifier) {
                    this.caledarIdentifier = caledarIdentifier;
                }

                public int getYear() {
                    return year;
                }

                public void setYear(int year) {
                    this.year = year;
                }

                public int getMonth() {
                    return month;
                }

                public void setMonth(int month) {
                    this.month = month;
                }

                public int getDay() {
                    return day;
                }

                public void setDay(int day) {
                    this.day = day;
                }
            }
        }

        public static class PhoneNumbersBean {
            /**
             * label : _$!<Home>!$_
             * displayLabel : 家庭
             * identifier : 7D4A1481-99AB-4C28-8D2F-4C402BA9CEF4
             * phoneNumber : {"number":"16784546487"}
             */

            private String label;
            private String displayLabel;
            private String identifier;
            private PhoneNumberBean phoneNumber;

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getDisplayLabel() {
                return displayLabel;
            }

            public void setDisplayLabel(String displayLabel) {
                this.displayLabel = displayLabel;
            }

            public String getIdentifier() {
                return identifier;
            }

            public void setIdentifier(String identifier) {
                this.identifier = identifier;
            }

            public PhoneNumberBean getPhoneNumber() {
                return phoneNumber;
            }

            public void setPhoneNumber(PhoneNumberBean phoneNumber) {
                this.phoneNumber = phoneNumber;
            }

            public static class PhoneNumberBean {
                /**
                 * number : 16784546487
                 */

                private String number;

                public String getNumber() {
                    return number;
                }

                public void setNumber(String number) {
                    this.number = number;
                }
            }
        }

        public static class UrlAddressesBean {
            /**
             * label : _$!<HomePage>!$_
             * displayLabel : 主页
             * identifier : A7F19D42-0148-4E96-8ACF-A2A4D26414C8
             * url : http://www.baidu.com
             */

            private String label;
            private String displayLabel;
            private String identifier;
            private String url;

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getDisplayLabel() {
                return displayLabel;
            }

            public void setDisplayLabel(String displayLabel) {
                this.displayLabel = displayLabel;
            }

            public String getIdentifier() {
                return identifier;
            }

            public void setIdentifier(String identifier) {
                this.identifier = identifier;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class PostalAddressesBean {
            /**
             * label : _$!<Home>!$_
             * displayLabel : 家庭
             * identifier : B1D5C2D5-72C7-4F2B-B41F-21743E3B55F0
             * address : {"street":"西丽桃源 ","subLocality":"南山区","country":"中国大陆","postalCode":"518000","city":"深圳","subAdministrativeArea":"","state":"广东","isoCountryCode":"cn"}
             */

            private String label;
            private String displayLabel;
            private String identifier;
            private AddressBean address;

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getDisplayLabel() {
                return displayLabel;
            }

            public void setDisplayLabel(String displayLabel) {
                this.displayLabel = displayLabel;
            }

            public String getIdentifier() {
                return identifier;
            }

            public void setIdentifier(String identifier) {
                this.identifier = identifier;
            }

            public AddressBean getAddress() {
                return address;
            }

            public void setAddress(AddressBean address) {
                this.address = address;
            }

            public static class AddressBean {
                /**
                 * street : 西丽桃源
                 * subLocality : 南山区
                 * country : 中国大陆
                 * postalCode : 518000
                 * city : 深圳
                 * subAdministrativeArea :
                 * state : 广东
                 * isoCountryCode : cn
                 */

                private String street;
                private String subLocality;
                private String country;
                private String postalCode;
                private String city;
                private String subAdministrativeArea;
                private String state;
                private String isoCountryCode;

                public String getStreet() {
                    return street;
                }

                public void setStreet(String street) {
                    this.street = street;
                }

                public String getSubLocality() {
                    return subLocality;
                }

                public void setSubLocality(String subLocality) {
                    this.subLocality = subLocality;
                }

                public String getCountry() {
                    return country;
                }

                public void setCountry(String country) {
                    this.country = country;
                }

                public String getPostalCode() {
                    return postalCode;
                }

                public void setPostalCode(String postalCode) {
                    this.postalCode = postalCode;
                }

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
                }

                public String getSubAdministrativeArea() {
                    return subAdministrativeArea;
                }

                public void setSubAdministrativeArea(String subAdministrativeArea) {
                    this.subAdministrativeArea = subAdministrativeArea;
                }

                public String getState() {
                    return state;
                }

                public void setState(String state) {
                    this.state = state;
                }

                public String getIsoCountryCode() {
                    return isoCountryCode;
                }

                public void setIsoCountryCode(String isoCountryCode) {
                    this.isoCountryCode = isoCountryCode;
                }
            }
        }

        public static class EmailAddressesBean {
            /**
             * email : asd@co.com
             * label : _$!<Home>!$_
             * displayLabel : 家庭
             * identifier : 3006A776-AAF0-44DF-A4F7-1F8B1C84323B
             */

            private String email;
            private String label;
            private String displayLabel;
            private String identifier;

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getDisplayLabel() {
                return displayLabel;
            }

            public void setDisplayLabel(String displayLabel) {
                this.displayLabel = displayLabel;
            }

            public String getIdentifier() {
                return identifier;
            }

            public void setIdentifier(String identifier) {
                this.identifier = identifier;
            }
        }

        public static class InstantMessageAddressesBean {
            /**
             * label : QQ
             * displayLabel : QQ
             * identifier : B0C26A0C-403D-4850-BE9A-A29C99610544
             * service : {"service":"QQ","username":"6468843"}
             */

            private String label;
            private String displayLabel;
            private String identifier;
            private ServiceBean service;

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getDisplayLabel() {
                return displayLabel;
            }

            public void setDisplayLabel(String displayLabel) {
                this.displayLabel = displayLabel;
            }

            public String getIdentifier() {
                return identifier;
            }

            public void setIdentifier(String identifier) {
                this.identifier = identifier;
            }

            public ServiceBean getService() {
                return service;
            }

            public void setService(ServiceBean service) {
                this.service = service;
            }

            public static class ServiceBean {
                /**
                 * service : QQ
                 * username : 6468843
                 */

                private String service;
                private String username;

                public String getService() {
                    return service;
                }

                public void setService(String service) {
                    this.service = service;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }
            }
        }
    }
}
