package com.company.commonlib.contacts.kotlin

/**
 * @author dinglaihong
 * @email 18279727279@163.com
 * @date 2019/7/30
 * @des 通讯录备份实体类，与IOS同步协议
 */
class ContactBackupResponse {


    /**
     * name :
     * contacts : [{"familyName":"周","nonGregorianBirthday":{"caledarIdentifier":2,"year":34,"month":5,"day":26},"birthday":{"month":7,"day":29,"caledarIdentifier":0},"contactRelations":[{"label":"_$!<Mother>!$_","relation":{"name":"荷花"},"displayLabel":"母亲","identifier":"8B4EBB10-9DE2-421B-9276-55E793497714"},{"label":"_$!<Father>!$_","relation":{"name":"周览"},"displayLabel":"父亲","identifier":"F8B70E89-5666-419F-9AED-5DBB7CC7E769"},{"label":"_$!<Manager>!$_","relation":{"name":"大师"},"displayLabel":"上司","identifier":"A21A6722-1C6E-484F-A748-EF8960E6CF89"}],"nickname":"虎子","phoneticOrganizationName":"Huáwèi","organizationName":"华为","departmentName":"市场","namePrefix":"娃娃","nameSuffix":"金喜","socialProfiles":[{"label":"sinaweibo","social":{"username":"eryut@ttiio.von","userIdentifier":"","service":"SinaWeibo","urlString":"http://weibo.com/n/eryut@ttiio.von"},"displayLabel":"sinaweibo","identifier":"625EB6F0-AC4D-44BF-933B-9EF3B0CCB797"}],"dates":[{"label":"_$!<Anniversary>!$_","displayLabel":"纪念日","identifier":"A4E2D01F-3325-4022-8208-36DD5137338C","date":{"caledarIdentifier":0,"year":2015,"month":7,"day":29}},{"label":"_$!<Other>!$_","displayLabel":"其他","identifier":"0ADAFB04-ADF5-411A-A7C1-FC2574DB2DBB","date":{"caledarIdentifier":0,"year":2002,"month":5,"day":20}}],"phoneNumbers":[{"label":"_$!<Home>!$_","displayLabel":"家庭","identifier":"7D4A1481-99AB-4C28-8D2F-4C402BA9CEF4","phoneNumber":{"number":"16784546487"}},{"label":"_$!<Work>!$_","displayLabel":"工作","identifier":"0C85CBDC-0D79-499C-A21E-23544836C0B1","phoneNumber":{"number":"123456"}},{"label":"iPhone","displayLabel":"iPhone","identifier":"C53973F2-CE7D-41D2-91F6-F961E26C0C65","phoneNumber":{"number":"789 123"}},{"label":"_$!<WorkFAX>!$_","displayLabel":"工作传真","identifier":"0526C389-57DE-48E7-90FE-63FC19974DE7","phoneNumber":{"number":"2164 6979"}}],"identifier":"F442C74B-22DD-4BCF-868E-E7AD4B6187D8:ABPerson","urlAddresses":[{"label":"_$!<HomePage>!$_","displayLabel":"主页","identifier":"A7F19D42-0148-4E96-8ACF-A2A4D26414C8","url":"http://www.baidu.com"},{"label":"_$!<Work>!$_","displayLabel":"工作","identifier":"ADCE28BB-4DFD-4901-B268-518C51A1CC6C","url":"http://www.i4.cn"}],"postalAddresses":[{"label":"_$!<Home>!$_","displayLabel":"家庭","identifier":"B1D5C2D5-72C7-4F2B-B41F-21743E3B55F0","address":{"street":"西丽 桃源","subLocality":"南山区","country":"中国大陆","postalCode":"518000","city":"深圳","subAdministrativeArea":"","state":"广东","isoCountryCode":"cn"}},{"label":"_$!<Work>!$_","displayLabel":"工作","identifier":"4CACE207-1E14-438C-AAF4-A889CD8EFC77","address":{"street":"天河 ","subLocality":"白云","country":"中国大陆","postalCode":"277372","city":"广州","subAdministrativeArea":"","state":"广东","isoCountryCode":"cn"}}],"groups":[],"middleName":"小","jobTitle":"销售代表","contactType":0,"phoneticMiddleName":"Xiǎo","note":"腹股沟","phoneticGivenName":"Xiǎohuā","phoneticFamilyName":"Zhōu","emailAddresses":[{"email":"asd@co.com","label":"_$!<Home>!$_","displayLabel":"家庭","identifier":"3006A776-AAF0-44DF-A4F7-1F8B1C84323B"},{"email":"7728383@qq.com","label":"_$!<Work>!$_","displayLabel":"工作","identifier":"9E08CBCD-937A-4A87-857D-10EA9D952C95"},{"email":"6277282@163.com","label":"iCloud","displayLabel":"iCloud","identifier":"D8DCCDD2-F743-4917-A55A-65425C87FB63"}],"givenName":"小花","instantMessageAddresses":[{"label":"QQ","displayLabel":"QQ","identifier":"B0C26A0C-403D-4850-BE9A-A29C99610544","service":{"service":"QQ","username":"6468843"}},{"label":"Skype","displayLabel":"Skype","identifier":"40311C8C-6368-46D2-BF5D-173F63A5F597","service":{"service":"Skype","username":"ghjkg"}},{"label":"旺旺","displayLabel":"旺旺","identifier":"256BE51E-C460-4DA5-B616-993BF5945040","service":{"service":"旺旺","username":"并不难"}}],"previousFamilyName":"朴"}]
     * identifier : 51DF4D5D-C00E-4386-9DB7-A702E1D18FF1
    </Work></Home></Work></Home></Work></HomePage></WorkFAX></Work></Home></Other></Anniversary></Manager></Father></Mother> */

    var name: String? = null
    var identifier: String? = null
    var contacts: List<ContactsBean>? = null

    class ContactsBean {

        /**
         * familyName : 周
         * nonGregorianBirthday : {"caledarIdentifier":2,"year":34,"month":5,"day":26}
         * birthday : {"month":7,"day":29,"caledarIdentifier":0}
         * contactRelations : [{"label":"_$!<Mother>!$_","relation":{"name":"荷花"},"displayLabel":"母亲","identifier":"8B4EBB10-9DE2-421B-9276-55E793497714"},{"label":"_$!<Father>!$_","relation":{"name":"周览"},"displayLabel":"父亲","identifier":"F8B70E89-5666-419F-9AED-5DBB7CC7E769"},{"label":"_$!<Manager>!$_","relation":{"name":"大师"},"displayLabel":"上司","identifier":"A21A6722-1C6E-484F-A748-EF8960E6CF89"}]
         * nickname : 虎子
         * phoneticOrganizationName : Huáwèi
         * organizationName : 华为
         * departmentName : 市场
         * namePrefix : 娃娃
         * nameSuffix : 金喜
         * socialProfiles : [{"label":"sinaweibo","social":{"username":"eryut@ttiio.von","userIdentifier":"","service":"SinaWeibo","urlString":"http://weibo.com/n/eryut@ttiio.von"},"displayLabel":"sinaweibo","identifier":"625EB6F0-AC4D-44BF-933B-9EF3B0CCB797"}]
         * dates : [{"label":"_$!<Anniversary>!$_","displayLabel":"纪念日","identifier":"A4E2D01F-3325-4022-8208-36DD5137338C","date":{"caledarIdentifier":0,"year":2015,"month":7,"day":29}},{"label":"_$!<Other>!$_","displayLabel":"其他","identifier":"0ADAFB04-ADF5-411A-A7C1-FC2574DB2DBB","date":{"caledarIdentifier":0,"year":2002,"month":5,"day":20}}]
         * phoneNumbers : [{"label":"_$!<Home>!$_","displayLabel":"家庭","identifier":"7D4A1481-99AB-4C28-8D2F-4C402BA9CEF4","phoneNumber":{"number":"16784546487"}},{"label":"_$!<Work>!$_","displayLabel":"工作","identifier":"0C85CBDC-0D79-499C-A21E-23544836C0B1","phoneNumber":{"number":"123456"}},{"label":"iPhone","displayLabel":"iPhone","identifier":"C53973F2-CE7D-41D2-91F6-F961E26C0C65","phoneNumber":{"number":"789 123"}},{"label":"_$!<WorkFAX>!$_","displayLabel":"工作传真","identifier":"0526C389-57DE-48E7-90FE-63FC19974DE7","phoneNumber":{"number":"2164 6979"}}]
         * identifier : F442C74B-22DD-4BCF-868E-E7AD4B6187D8:ABPerson
         * urlAddresses : [{"label":"_$!<HomePage>!$_","displayLabel":"主页","identifier":"A7F19D42-0148-4E96-8ACF-A2A4D26414C8","url":"http://www.baidu.com"},{"label":"_$!<Work>!$_","displayLabel":"工作","identifier":"ADCE28BB-4DFD-4901-B268-518C51A1CC6C","url":"http://www.i4.cn"}]
         * postalAddresses : [{"label":"_$!<Home>!$_","displayLabel":"家庭","identifier":"B1D5C2D5-72C7-4F2B-B41F-21743E3B55F0","address":{"street":"西丽 桃源","subLocality":"南山区","country":"中国大陆","postalCode":"518000","city":"深圳","subAdministrativeArea":"","state":"广东","isoCountryCode":"cn"}},{"label":"_$!<Work>!$_","displayLabel":"工作","identifier":"4CACE207-1E14-438C-AAF4-A889CD8EFC77","address":{"street":"天河 ","subLocality":"白云","country":"中国大陆","postalCode":"277372","city":"广州","subAdministrativeArea":"","state":"广东","isoCountryCode":"cn"}}]
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
        </Work></Home></Work></Home></Work></HomePage></WorkFAX></Work></Home></Other></Anniversary></Manager></Father></Mother> */
        var displayName: String? = null
        var familyName: String? = null
        /**
         * 头像，将文件通过base64加密后数据（限制大小为1M）
         */
        var imageData: String? = null
        var nonGregorianBirthday: NonGregorianBirthdayBean? = null
        var birthday: BirthdayBean? = null
        var nickname: String? = null
        var phoneticOrganizationName: String? = null
        var organizationName: String? = null
        var departmentName: String? = null
        var namePrefix: String? = null
        var nameSuffix: String? = null
        var identifier: String? = null
        var middleName: String? = null
        var jobTitle: String? = null
        var contactType: Int = 0
        var phoneticMiddleName: String? = null
        var note: String? = null
        var phoneticGivenName: String? = null
        var phoneticFamilyName: String? = null
        var givenName: String? = null
        var previousFamilyName: String? = null
        var contactRelations: List<ContactRelationsBean>? = null
        var socialProfiles: List<SocialProfilesBean>? = null
        var dates: List<DatesBean>? = null
        var phoneNumbers: List<PhoneNumbersBean>? = null
        var urlAddresses: List<UrlAddressesBean>? = null
        var postalAddresses: List<PostalAddressesBean>? = null
        var groups: List<GroupsBean>? = null
        var emailAddresses: List<EmailAddressesBean>? = null
        var instantMessageAddresses: List<InstantMessageAddressesBean>? = null

        class GroupsBean {
            /**
             * label : _$!<Anniversary>!$_
             * displayLabel : 纪念日
             * identifier : A4E2D01F-3325-4022-8208-36DD5137338C
             * date : {"caledarIdentifier":0,"year":2015,"month":7,"day":29}
            </Anniversary> */

            var label: String? = null
            var displayLabel: String? = null
            var identifier: String? = null
            var name: String? = null
        }

        class NonGregorianBirthdayBean {
            /**
             * caledarIdentifier : 2
             * year : 34
             * month : 5
             * day : 26
             */

            var caledarIdentifier: Int = 0
            var year: Int = 0
            var month: Int = 0
            var day: Int = 0
        }

        class BirthdayBean {
            /**
             * month : 7
             * day : 29
             * caledarIdentifier : 0
             */

            var month: Int = 0
            var day: Int = 0
            var caledarIdentifier: Int = 0
        }

        class ContactRelationsBean {
            /**
             * label : _$!<Mother>!$_
             * relation : {"name":"荷花"}
             * displayLabel : 母亲
             * identifier : 8B4EBB10-9DE2-421B-9276-55E793497714
            </Mother> */

            var label: String? = null
            var relation: RelationBean? = null
            var displayLabel: String? = null
            var identifier: String? = null

            class RelationBean {
                /**
                 * name : 荷花
                 */

                var name: String? = null
            }
        }

        class SocialProfilesBean {
            /**
             * label : sinaweibo
             * social : {"username":"eryut@ttiio.von","userIdentifier":"","service":"SinaWeibo","urlString":"http://weibo.com/n/eryut@ttiio.von"}
             * displayLabel : sinaweibo
             * identifier : 625EB6F0-AC4D-44BF-933B-9EF3B0CCB797
             */

            var label: String? = null
            var social: SocialBean? = null
            var displayLabel: String? = null
            var identifier: String? = null

            class SocialBean {
                /**
                 * username : eryut@ttiio.von
                 * userIdentifier :
                 * service : SinaWeibo
                 * urlString : http://weibo.com/n/eryut@ttiio.von
                 */

                var username: String? = null
                var userIdentifier: String? = null
                var service: String? = null
                var urlString: String? = null
            }
        }

        class DatesBean {
            /**
             * label : _$!<Anniversary>!$_
             * displayLabel : 纪念日
             * identifier : A4E2D01F-3325-4022-8208-36DD5137338C
             * date : {"caledarIdentifier":0,"year":2015,"month":7,"day":29}
            </Anniversary> */

            var label: String? = null
            var displayLabel: String? = null
            var identifier: String? = null
            var date: DateBean? = null

            class DateBean {
                /**
                 * caledarIdentifier : 0
                 * year : 2015
                 * month : 7
                 * day : 29
                 */

                var caledarIdentifier: Int = 0
                var year: Int = 0
                var month: Int = 0
                var day: Int = 0
            }
        }

        class PhoneNumbersBean {
            /**
             * label : _$!<Home>!$_
             * displayLabel : 家庭
             * identifier : 7D4A1481-99AB-4C28-8D2F-4C402BA9CEF4
             * phoneNumber : {"number":"16784546487"}
            </Home> */

            var label: String? = null
            var displayLabel: String? = null
            var identifier: String? = null
            var phoneNumber: PhoneNumberBean? = null

            class PhoneNumberBean {
                /**
                 * number : 16784546487
                 */

                var number: String? = null
            }
        }

        class UrlAddressesBean {
            /**
             * label : _$!<HomePage>!$_
             * displayLabel : 主页
             * identifier : A7F19D42-0148-4E96-8ACF-A2A4D26414C8
             * url : http://www.baidu.com
            </HomePage> */

            var label: String? = null
            var displayLabel: String? = null
            var identifier: String? = null
            var url: String? = null
        }

        class PostalAddressesBean {
            /**
             * label : _$!<Home>!$_
             * displayLabel : 家庭
             * identifier : B1D5C2D5-72C7-4F2B-B41F-21743E3B55F0
             * address : {"street":"西丽 桃源","subLocality":"南山区","country":"中国大陆","postalCode":"518000","city":"深圳","subAdministrativeArea":"","state":"广东","isoCountryCode":"cn"}
            </Home> */

            var label: String? = null
            var displayLabel: String? = null
            var identifier: String? = null
            var address: AddressBean? = null

            class AddressBean {
                /**
                 * street : 西丽 桃源
                 * subLocality : 南山区
                 * country : 中国大陆
                 * postalCode : 518000
                 * city : 深圳
                 * subAdministrativeArea :
                 * state : 广东
                 * isoCountryCode : cn
                 */

                var street: String? = null
                var subLocality: String? = null
                var country: String? = null
                var postalCode: String? = null
                var city: String? = null
                var subAdministrativeArea: String? = null
                var state: String? = null
                var isoCountryCode: String? = null
            }
        }

        class EmailAddressesBean {
            /**
             * email : asd@co.com
             * label : _$!<Home>!$_
             * displayLabel : 家庭
             * identifier : 3006A776-AAF0-44DF-A4F7-1F8B1C84323B
            </Home> */

            var email: String? = null
            var label: String? = null
            var displayLabel: String? = null
            var identifier: String? = null
        }

        class InstantMessageAddressesBean {
            /**
             * label : QQ
             * displayLabel : QQ
             * identifier : B0C26A0C-403D-4850-BE9A-A29C99610544
             * service : {"service":"QQ","username":"6468843"}
             */

            var label: String? = null
            var displayLabel: String? = null
            var identifier: String? = null
            var service: ServiceBean? = null

            class ServiceBean {
                /**
                 * service : QQ
                 * username : 6468843
                 */

                var service: String? = null
                var username: String? = null
            }
        }
    }
}
