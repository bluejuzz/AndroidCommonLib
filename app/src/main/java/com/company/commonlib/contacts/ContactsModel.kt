package com.company.commonlib.contacts

import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.FileIOUtils
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.Utils
import com.company.commonlibrary.base.BaseViewModel
import com.google.gson.Gson
import com.google.gson.internal.bind.JsonTreeReader
import java.io.File
import java.io.Reader

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
        if (!::writeContactsComplete.isInitialized) {
            writeContactsComplete = MutableLiveData()
        }
        val b = ContactBackupUtil(Utils.getApp()).writeContacts(Gson().fromJson("{\n" +
                "    \"name\": \"\",\n" +
                "    \"contacts\": [\n" +
                "        {\n" +
                "            \"familyName\": \"周\",\n" +
                "            \"nonGregorianBirthday\": {\n" +
                "                \"caledarIdentifier\": 2,\n" +
                "                \"year\": 34,\n" +
                "                \"month\": 5,\n" +
                "                \"day\": 26\n" +
                "            },\n" +
                "            \"birthday\": {\n" +
                "                \"month\": 7,\n" +
                "                \"day\": 29,\n" +
                "                \"caledarIdentifier\": 0\n" +
                "            },\n" +
                "            \"contactRelations\": [\n" +
                "                {\n" +
                "                    \"label\": \"_\$!<Mother>!\$_\",\n" +
                "                    \"relation\": {\n" +
                "                        \"name\": \"荷花\"\n" +
                "                    },\n" +
                "                    \"displayLabel\": \"母亲\",\n" +
                "                    \"identifier\": \"8B4EBB10-9DE2-421B-9276-55E793497714\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"label\": \"_\$!<Father>!\$_\",\n" +
                "                    \"relation\": {\n" +
                "                        \"name\": \"周览\"\n" +
                "                    },\n" +
                "                    \"displayLabel\": \"父亲\",\n" +
                "                    \"identifier\": \"F8B70E89-5666-419F-9AED-5DBB7CC7E769\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"label\": \"_\$!<Manager>!\$_\",\n" +
                "                    \"relation\": {\n" +
                "                        \"name\": \"大师\"\n" +
                "                    },\n" +
                "                    \"displayLabel\": \"上司\",\n" +
                "                    \"identifier\": \"A21A6722-1C6E-484F-A748-EF8960E6CF89\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"nickname\": \"虎子\",\n" +
                "            \"phoneticOrganizationName\": \"Huáwèi\",\n" +
                "            \"organizationName\": \"华为\",\n" +
                "            \"departmentName\": \"市场\",\n" +
                "            \"imageData\": \"\",\n" +
                "            \"namePrefix\": \"娃娃\",\n" +
                "            \"nameSuffix\": \"金喜\",\n" +
                "            \"socialProfiles\": [\n" +
                "                {\n" +
                "                    \"label\": \"sinaweibo\",\n" +
                "                    \"social\": {\n" +
                "                        \"username\": \"eryut@ttiio.von\",\n" +
                "                        \"userIdentifier\": \"\",\n" +
                "                        \"service\": \"SinaWeibo\",\n" +
                "                        \"urlString\": \"http://weibo.com/n/eryut@ttiio.von\"\n" +
                "                    },\n" +
                "                    \"displayLabel\": \"sinaweibo\",\n" +
                "                    \"identifier\": \"625EB6F0-AC4D-44BF-933B-9EF3B0CCB797\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"dates\": [\n" +
                "                {\n" +
                "                    \"label\": \"_\$!<Anniversary>\$_\",\n" +
                "                    \"displayLabel\": \"纪念日\",\n" +
                "                    \"identifier\": \"A4E2D01F-3325-4022-8208-36DD5137338C\",\n" +
                "                    \"date\": {\n" +
                "                        \"caledarIdentifier\": 0,\n" +
                "                        \"year\": 2015,\n" +
                "                        \"month\": 7,\n" +
                "                        \"day\": 29\n" +
                "                    }\n" +
                "                },\n" +
                "                {\n" +
                "                    \"label\": \"_\$!<Other>!\$_\",\n" +
                "                    \"displayLabel\": \"其他\",\n" +
                "                    \"identifier\": \"0ADAFB04-ADF5-411A-A7C1-FC2574DB2DBB\",\n" +
                "                    \"date\": {\n" +
                "                        \"caledarIdentifier\": 0,\n" +
                "                        \"year\": 2002,\n" +
                "                        \"month\": 5,\n" +
                "                        \"day\": 20\n" +
                "                    }\n" +
                "                }\n" +
                "            ],\n" +
                "            \"phoneNumbers\": [\n" +
                "                {\n" +
                "                    \"label\": \"_\$!<Home>!\$_\",\n" +
                "                    \"displayLabel\": \"家庭\",\n" +
                "                    \"identifier\": \"7D4A1481-99AB-4C28-8D2F-4C402BA9CEF4\",\n" +
                "                    \"phoneNumber\": {\n" +
                "                        \"number\": \"16784546487\"\n" +
                "                    }\n" +
                "                },\n" +
                "                {\n" +
                "                    \"label\": \"_\$!<Work>!\$_\",\n" +
                "                    \"displayLabel\": \"工作\",\n" +
                "                    \"identifier\": \"0C85CBDC-0D79-499C-A21E-23544836C0B1\",\n" +
                "                    \"phoneNumber\": {\n" +
                "                        \"number\": \"123456\"\n" +
                "                    }\n" +
                "                },\n" +
                "                {\n" +
                "                    \"label\": \"iPhone\",\n" +
                "                    \"displayLabel\": \"iPhone\",\n" +
                "                    \"identifier\": \"C53973F2-CE7D-41D2-91F6-F961E26C0C65\",\n" +
                "                    \"phoneNumber\": {\n" +
                "                        \"number\": \"789 123\"\n" +
                "                    }\n" +
                "                },\n" +
                "                {\n" +
                "                    \"label\": \"_\$!<WorkFAX>!\$_\",\n" +
                "                    \"displayLabel\": \"工作传真\",\n" +
                "                    \"identifier\": \"0526C389-57DE-48E7-90FE-63FC19974DE7\",\n" +
                "                    \"phoneNumber\": {\n" +
                "                        \"number\": \"2164 6979\"\n" +
                "                    }\n" +
                "                }\n" +
                "            ],\n" +
                "            \"identifier\": \"F442C74B-22DD-4BCF-868E-E7AD4B6187D8:ABPerson\",\n" +
                "            \"urlAddresses\": [\n" +
                "                {\n" +
                "                    \"label\": \"_\$!<HomePage>!\$_\",\n" +
                "                    \"displayLabel\": \"主页\",\n" +
                "                    \"identifier\": \"A7F19D42-0148-4E96-8ACF-A2A4D26414C8\",\n" +
                "                    \"url\": \"http://www.baidu.com\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"label\": \"_\$!<Work>!\$_\",\n" +
                "                    \"displayLabel\": \"工作\",\n" +
                "                    \"identifier\": \"ADCE28BB-4DFD-4901-B268-518C51A1CC6C\",\n" +
                "                    \"url\": \"http://www.i4.cn\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"postalAddresses\": [\n" +
                "                {\n" +
                "                    \"label\": \"_\$!<Home>!\$_\",\n" +
                "                    \"displayLabel\": \"家庭\",\n" +
                "                    \"identifier\": \"B1D5C2D5-72C7-4F2B-B41F-21743E3B55F0\",\n" +
                "                    \"address\": {\n" +
                "                        \"street\": \"西丽桃源 \",\n" +
                "                        \"subLocality\": \"南山区\",\n" +
                "                        \"country\": \"中国大陆\",\n" +
                "                        \"postalCode\": \"518000\",\n" +
                "                        \"city\": \"深圳\",\n" +
                "                        \"subAdministrativeArea\": \"\",\n" +
                "                        \"state\": \"广东\",\n" +
                "                        \"isoCountryCode\": \"cn\"\n" +
                "                    }\n" +
                "                },\n" +
                "                {\n" +
                "                    \"label\": \"_\$!<Work>!\$_\",\n" +
                "                    \"displayLabel\": \"工作\",\n" +
                "                    \"identifier\": \"4CACE207-1E14-438C-AAF4-A889CD8EFC77\",\n" +
                "                    \"address\": {\n" +
                "                        \"street\": \"天河\",\n" +
                "                        \"subLocality\": \"白云\",\n" +
                "                        \"country\": \"中国大陆\",\n" +
                "                        \"postalCode\": \"277372\",\n" +
                "                        \"city\": \"广州\",\n" +
                "                        \"subAdministrativeArea\": \"\",\n" +
                "                        \"state\": \"广东\",\n" +
                "                        \"isoCountryCode\": \"cn\"\n" +
                "                    }\n" +
                "                }\n" +
                "            ],\n" +
                "            \"groups\": [],\n" +
                "            \"middleName\": \"小\",\n" +
                "            \"jobTitle\": \"销售代表\",\n" +
                "            \"contactType\": 0,\n" +
                "            \"phoneticMiddleName\": \"Xiǎo\",\n" +
                "            \"note\": \"腹股沟\",\n" +
                "            \"phoneticGivenName\": \"Xiǎohuā\",\n" +
                "            \"phoneticFamilyName\": \"Zhōu\",\n" +
                "            \"emailAddresses\": [\n" +
                "                {\n" +
                "                    \"email\": \"asd@co.com\",\n" +
                "                    \"label\": \"_\$!<Home>!\$_\",\n" +
                "                    \"displayLabel\": \"家庭\",\n" +
                "                    \"identifier\": \"3006A776-AAF0-44DF-A4F7-1F8B1C84323B\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"email\": \"7728383@qq.com\",\n" +
                "                    \"label\": \"_\$!<Work>!\$_\",\n" +
                "                    \"displayLabel\": \"工作\",\n" +
                "                    \"identifier\": \"9E08CBCD-937A-4A87-857D-10EA9D952C95\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"email\": \"6277282@163.com\",\n" +
                "                    \"label\": \"iCloud\",\n" +
                "                    \"displayLabel\": \"iCloud\",\n" +
                "                    \"identifier\": \"D8DCCDD2-F743-4917-A55A-65425C87FB63\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"givenName\": \"小花\",\n" +
                "            \"instantMessageAddresses\": [\n" +
                "                {\n" +
                "                    \"label\": \"QQ\",\n" +
                "                    \"displayLabel\": \"QQ\",\n" +
                "                    \"identifier\": \"B0C26A0C-403D-4850-BE9A-A29C99610544\",\n" +
                "                    \"service\": {\n" +
                "                        \"service\": \"QQ\",\n" +
                "                        \"username\": \"6468843\"\n" +
                "                    }\n" +
                "                },\n" +
                "                {\n" +
                "                    \"label\": \"Skype\",\n" +
                "                    \"displayLabel\": \"Skype\",\n" +
                "                    \"identifier\": \"40311C8C-6368-46D2-BF5D-173F63A5F597\",\n" +
                "                    \"service\": {\n" +
                "                        \"service\": \"Skype\",\n" +
                "                        \"username\": \"ghjkg\"\n" +
                "                    }\n" +
                "                },\n" +
                "                {\n" +
                "                    \"label\": \"旺旺\",\n" +
                "                    \"displayLabel\": \"旺旺\",\n" +
                "                    \"identifier\": \"256BE51E-C460-4DA5-B616-993BF5945040\",\n" +
                "                    \"service\": {\n" +
                "                        \"service\": \"旺旺\",\n" +
                "                        \"username\": \"并不难\"\n" +
                "                    }\n" +
                "                }\n" +
                "            ],\n" +
                "            \"previousFamilyName\": \"朴\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"familyName\": \"李\",\n" +
                "            \"nonGregorianBirthday\": {\n" +
                "                \"caledarIdentifier\": 2,\n" +
                "                \"year\": 34,\n" +
                "                \"month\": 5,\n" +
                "                \"day\": 26\n" +
                "            },\n" +
                "            \"birthday\": {\n" +
                "                \"month\": 7,\n" +
                "                \"day\": 29,\n" +
                "                \"caledarIdentifier\": 0\n" +
                "            },\n" +
                "            \"contactRelations\": [\n" +
                "                {\n" +
                "                    \"label\": \"_\$!<Mother>!\$_\",\n" +
                "                    \"relation\": {\n" +
                "                        \"name\": \"666\"\n" +
                "                    },\n" +
                "                    \"displayLabel\": \"母亲\",\n" +
                "                    \"identifier\": \"8B4EBB10-9DE2-421B-9276-55E793497714\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"label\": \"_\$!<Father>!\$_\",\n" +
                "                    \"relation\": {\n" +
                "                        \"name\": \"周览888\"\n" +
                "                    },\n" +
                "                    \"displayLabel\": \"父亲\",\n" +
                "                    \"identifier\": \"F8B70E89-5666-419F-9AED-5DBB7CC7E769\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"label\": \"_\$!<Manager>!\$_\",\n" +
                "                    \"relation\": {\n" +
                "                        \"name\": \"大师\"\n" +
                "                    },\n" +
                "                    \"displayLabel\": \"上司\",\n" +
                "                    \"identifier\": \"A21A6722-1C6E-484F-A748-EF8960E6CF89\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"nickname\": \"狗子\",\n" +
                "            \"phoneticOrganizationName\": \"Huáwèi\",\n" +
                "            \"organizationName\": \"华为666\",\n" +
                "            \"departmentName\": \"市场666\",\n" +
                "            \"imageData\": \"\",\n" +
                "            \"namePrefix\": \"前\",\n" +
                "            \"nameSuffix\": \"后\",\n" +
                "            \"socialProfiles\": [\n" +
                "                {\n" +
                "                    \"label\": \"sinaweibo\",\n" +
                "                    \"social\": {\n" +
                "                        \"username\": \"eryut@ttiio.von\",\n" +
                "                        \"userIdentifier\": \"\",\n" +
                "                        \"service\": \"SinaWeibo\",\n" +
                "                        \"urlString\": \"http://weibo.com/n/eryut@ttiio.von\"\n" +
                "                    },\n" +
                "                    \"displayLabel\": \"sinaweibo\",\n" +
                "                    \"identifier\": \"625EB6F0-AC4D-44BF-933B-9EF3B0CCB797\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"dates\": [\n" +
                "                {\n" +
                "                    \"label\": \"_\$!<Anniversary>\$_\",\n" +
                "                    \"displayLabel\": \"纪念日\",\n" +
                "                    \"identifier\": \"A4E2D01F-3325-4022-8208-36DD5137338C\",\n" +
                "                    \"date\": {\n" +
                "                        \"caledarIdentifier\": 0,\n" +
                "                        \"year\": 2015,\n" +
                "                        \"month\": 7,\n" +
                "                        \"day\": 29\n" +
                "                    }\n" +
                "                },\n" +
                "                {\n" +
                "                    \"label\": \"_\$!<Other>!\$_\",\n" +
                "                    \"displayLabel\": \"其他\",\n" +
                "                    \"identifier\": \"0ADAFB04-ADF5-411A-A7C1-FC2574DB2DBB\",\n" +
                "                    \"date\": {\n" +
                "                        \"caledarIdentifier\": 0,\n" +
                "                        \"year\": 2002,\n" +
                "                        \"month\": 5,\n" +
                "                        \"day\": 20\n" +
                "                    }\n" +
                "                }\n" +
                "            ],\n" +
                "            \"phoneNumbers\": [\n" +
                "                {\n" +
                "                    \"label\": \"_\$!<Home>!\$_\",\n" +
                "                    \"displayLabel\": \"家庭\",\n" +
                "                    \"identifier\": \"7D4A1481-99AB-4C28-8D2F-4C402BA9CEF4\",\n" +
                "                    \"phoneNumber\": {\n" +
                "                        \"number\": \"16784888546487\"\n" +
                "                    }\n" +
                "                },\n" +
                "                {\n" +
                "                    \"label\": \"_\$!<Work>!\$_\",\n" +
                "                    \"displayLabel\": \"工作\",\n" +
                "                    \"identifier\": \"0C85CBDC-0D79-499C-A21E-23544836C0B1\",\n" +
                "                    \"phoneNumber\": {\n" +
                "                        \"number\": \"123488856\"\n" +
                "                    }\n" +
                "                },\n" +
                "                {\n" +
                "                    \"label\": \"iPhone\",\n" +
                "                    \"displayLabel\": \"iPhone\",\n" +
                "                    \"identifier\": \"C53973F2-CE7D-41D2-91F6-F961E26C0C65\",\n" +
                "                    \"phoneNumber\": {\n" +
                "                        \"number\": \"789 128883\"\n" +
                "                    }\n" +
                "                },\n" +
                "                {\n" +
                "                    \"label\": \"_\$!<WorkFAX>!\$_\",\n" +
                "                    \"displayLabel\": \"工作传真\",\n" +
                "                    \"identifier\": \"0526C389-57DE-48E7-90FE-63FC19974DE7\",\n" +
                "                    \"phoneNumber\": {\n" +
                "                        \"number\": \"2164 698889\"\n" +
                "                    }\n" +
                "                }\n" +
                "            ],\n" +
                "            \"identifier\": \"F442C74B-22DD-4BCF-868E-E7AD4B6187D8:ABPerson\",\n" +
                "            \"urlAddresses\": [\n" +
                "                {\n" +
                "                    \"label\": \"_\$!<HomePage>!\$_\",\n" +
                "                    \"displayLabel\": \"主页\",\n" +
                "                    \"identifier\": \"A7F19D42-0148-4E96-8ACF-A2A4D26414C8\",\n" +
                "                    \"url\": \"http://www.baidu.com\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"label\": \"_\$!<Work>!\$_\",\n" +
                "                    \"displayLabel\": \"工作\",\n" +
                "                    \"identifier\": \"ADCE28BB-4DFD-4901-B268-518C51A1CC6C\",\n" +
                "                    \"url\": \"http://www.i4666.cn\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"postalAddresses\": [\n" +
                "                {\n" +
                "                    \"label\": \"_\$!<Home>!\$_\",\n" +
                "                    \"displayLabel\": \"家庭\",\n" +
                "                    \"identifier\": \"B1D5C2D5-72C7-4F2B-B41F-21743E3B55F0\",\n" +
                "                    \"address\": {\n" +
                "                        \"street\": \"西丽桃源666 \",\n" +
                "                        \"subLocality\": \"南山区666\",\n" +
                "                        \"country\": \"中国大陆\",\n" +
                "                        \"postalCode\": \"666\",\n" +
                "                        \"city\": \"深圳666\",\n" +
                "                        \"subAdministrativeArea\": \"\",\n" +
                "                        \"state\": \"广东666\",\n" +
                "                        \"isoCountryCode\": \"cn\"\n" +
                "                    }\n" +
                "                },\n" +
                "                {\n" +
                "                    \"label\": \"_\$!<Work>!\$_\",\n" +
                "                    \"displayLabel\": \"工作\",\n" +
                "                    \"identifier\": \"4CACE207-1E14-438C-AAF4-A889CD8EFC77\",\n" +
                "                    \"address\": {\n" +
                "                        \"street\": \"天河666\",\n" +
                "                        \"subLocality\": \"白云\",\n" +
                "                        \"country\": \"中国大陆\",\n" +
                "                        \"postalCode\": \"666\",\n" +
                "                        \"city\": \"广州666\",\n" +
                "                        \"subAdministrativeArea\": \"\",\n" +
                "                        \"state\": \"广东666\",\n" +
                "                        \"isoCountryCode\": \"cn\"\n" +
                "                    }\n" +
                "                }\n" +
                "            ],\n" +
                "            \"groups\": [],\n" +
                "            \"middleName\": \"小\",\n" +
                "            \"jobTitle\": \"游戏运营\",\n" +
                "            \"contactType\": 0,\n" +
                "            \"phoneticMiddleName\": \"Xiǎo\",\n" +
                "            \"note\": \"皮皮虾\",\n" +
                "            \"phoneticGivenName\": \"Xiǎohuā\",\n" +
                "            \"phoneticFamilyName\": \"Zhōu\",\n" +
                "            \"emailAddresses\": [\n" +
                "                {\n" +
                "                    \"email\": \"a999sd@co.com\",\n" +
                "                    \"label\": \"_\$!<Home>!\$_\",\n" +
                "                    \"displayLabel\": \"家庭\",\n" +
                "                    \"identifier\": \"3006A776-AAF0-44DF-A4F7-1F8B1C84323B\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"email\": \"7799928383@qq.com\",\n" +
                "                    \"label\": \"_\$!<Work>!\$_\",\n" +
                "                    \"displayLabel\": \"工作\",\n" +
                "                    \"identifier\": \"9E08CBCD-937A-4A87-857D-10EA9D952C95\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"email\": \"6299977282@163.com\",\n" +
                "                    \"label\": \"iCloud\",\n" +
                "                    \"displayLabel\": \"iCloud\",\n" +
                "                    \"identifier\": \"D8DCCDD2-F743-4917-A55A-65425C87FB63\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"givenName\": \"小龙\",\n" +
                "            \"instantMessageAddresses\": [\n" +
                "                {\n" +
                "                    \"label\": \"QQ\",\n" +
                "                    \"displayLabel\": \"QQ\",\n" +
                "                    \"identifier\": \"B0C26A0C-403D-4850-BE9A-A29C99610544\",\n" +
                "                    \"service\": {\n" +
                "                        \"service\": \"QQ\",\n" +
                "                        \"username\": \"5555\"\n" +
                "                    }\n" +
                "                },\n" +
                "                {\n" +
                "                    \"label\": \"Skype\",\n" +
                "                    \"displayLabel\": \"Skype\",\n" +
                "                    \"identifier\": \"40311C8C-6368-46D2-BF5D-173F63A5F597\",\n" +
                "                    \"service\": {\n" +
                "                        \"service\": \"Skype\",\n" +
                "                        \"username\": \"5555\"\n" +
                "                    }\n" +
                "                },\n" +
                "                {\n" +
                "                    \"label\": \"旺旺\",\n" +
                "                    \"displayLabel\": \"p\",\n" +
                "                    \"identifier\": \"256BE51E-C460-4DA5-B616-993BF5945040\",\n" +
                "                    \"service\": {\n" +
                "                        \"service\": \"p\",\n" +
                "                        \"username\": \"p\"\n" +
                "                    }\n" +
                "                }\n" +
                "            ],\n" +
                "            \"previousFamilyName\": \"p\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"identifier\": \"51DF4D5D-C00E-4386-9DB7-A702E1D18FF1\"\n" +
                "}", ContactBackupResponse::class.java))
        writeContactsComplete.value = b
        return writeContactsComplete
    }

    private fun loadUsers() {
        val phones: MutableList<ContactBean> = mutableListOf()
        val response: ContactBackupResponse? = ContactBackupUtil(Utils.getApp()).readContacts
        response?.contacts?.forEach { contact ->
            val phoneNumbers: List<ContactBackupResponse.ContactsBean.PhoneNumbersBean>? = contact.phoneNumbers
            val name: String = contact.givenName.toString()
            var number = ""
            when (phoneNumbers.isNullOrEmpty()) {
                true -> {
                }
                false -> {
                    val phoneNumbersBean: ContactBackupResponse.ContactsBean.PhoneNumbersBean? = phoneNumbers[0]

                    phoneNumbersBean?.let {
                        number = it.phoneNumber?.number.toString()
                    }
                }

            }
            val contactBean = ContactBean(name, number)
            phones.add(contactBean)
        }
        contacts.value = phones
    }
}