package com.company.commonlib.contacts.java;

import android.annotation.SuppressLint;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Base64;
import android.util.Log;

import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.Utils;
import com.company.commonlib.contacts.kotlin.ContactBackupUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * @author dinglaihong
 * @email 18279727279@163.com
 * @date 2019/8/7
 * @des
 */
public class ContactsUtils {
    private static ArrayList<ContentProviderOperation> operations = new ArrayList<>();
    private static int rawContactInsertIndex;
    private static final String TAG = ContactBackupUtils.class.getSimpleName();
    private static final Uri DATA_URI = ContactsContract.Data.CONTENT_URI;
    private static final String RAW_CONTACT_ID = ContactsContract.Data.RAW_CONTACT_ID;
    private static final String MIME_TYPE = ContactsContract.Data.MIMETYPE;

    @SuppressLint({"Recycle"})
    public static ContactsBackupBean readContacts() {
        ContentResolver contentResolver = Utils.getApp().getContentResolver();
        ContactsBackupBean response = new ContactsBackupBean();
        List<ContactsBackupBean.ContactsBean> contacts = new ArrayList<>();
        response.setName("");
        response.setIdentifier("");
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        Cursor var10000 = contentResolver.query(uri, null, null, null, null);
        if (var10000 != null) {
            Cursor cursor;
            ContactsBackupBean.ContactsBean contactsBean;
            for (cursor = var10000; cursor.moveToNext(); contacts.add(contactsBean)) {
                contactsBean = new ContactsBackupBean.ContactsBean();
                String contactId = cursor.getString(cursor.getColumnIndex("_id"));
                String name = cursor.getString(cursor.getColumnIndex("display_name"));
                contactsBean.setIdentifier(contactId);
                contactsBean.setDisplayName(name);
                Cursor phones = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, "contact_id = " + contactId, null, null);
                if (phones != null) {
                    List<ContactsBackupBean.ContactsBean.PhoneNumbersBean> phoneNumbers = new ArrayList<>();

                    while (phones.moveToNext()) {
                        ContactsBackupBean.ContactsBean.PhoneNumbersBean numbersBean = new ContactsBackupBean.ContactsBean.PhoneNumbersBean();
                        int phoneType = phones.getInt(phones.getColumnIndex("data2"));
                        String label = phones.getString(phones.getColumnIndex("data3"));
                        numbersBean.setLabel(ContactsConverter.getPhoneLabel(phoneType));
                        numbersBean.setDisplayLabel(ContactsConverter.getDisplayLabel(numbersBean.getLabel(), label));
                        String phoneNumber = phones.getString(phones.getColumnIndex("data1"));
                        ContactsBackupBean.ContactsBean.PhoneNumbersBean.PhoneNumberBean bean = new ContactsBackupBean.ContactsBean.PhoneNumbersBean.PhoneNumberBean();
                        bean.setNumber(phoneNumber);
                        numbersBean.setPhoneNumber(bean);
                        phoneNumbers.add(numbersBean);
                    }

                    contactsBean.setPhoneNumbers(phoneNumbers);
                    phones.close();
                }

                Cursor emails = contentResolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, "contact_id = " + contactId, null, null);
                if (emails != null) {
                    ArrayList<ContactsBackupBean.ContactsBean.EmailAddressesBean> emailAddresses = new ArrayList<>();
                    while (emails.moveToNext()) {
                        ContactsBackupBean.ContactsBean.EmailAddressesBean emailAddressesBean = new ContactsBackupBean.ContactsBean.EmailAddressesBean();
                        String label = emails.getString(emails.getColumnIndex("data3"));
                        int type = emails.getInt(emails.getColumnIndex("data2"));
                        emailAddressesBean.setLabel(ContactsConverter.getEmailLabel(type));
                        emailAddressesBean.setDisplayLabel(ContactsConverter.getDisplayLabel(emailAddressesBean.getLabel(), label));
                        String email = emails.getString(emails.getColumnIndex("data1"));
                        emailAddressesBean.setEmail(email);
                        emailAddresses.add(emailAddressesBean);
                    }

                    contactsBean.setEmailAddresses(emailAddresses);
                    emails.close();
                }

                String imWhere = "contact_id = ? AND mimetype = ?";
                String[] imWhereParams = new String[]{contactId, "vnd.android.cursor.item/im"};
                Cursor ims = contentResolver.query(ContactsContract.Data.CONTENT_URI, null, imWhere, imWhereParams, null);
                if (ims != null) {
                    List<ContactsBackupBean.ContactsBean.InstantMessageAddressesBean> imAddresses = new ArrayList<>();

                    while (ims.moveToNext()) {
                        ContactsBackupBean.ContactsBean.InstantMessageAddressesBean imAddressesBean = new ContactsBackupBean.ContactsBean.InstantMessageAddressesBean();
                        String imsLabel = ims.getString(ims.getColumnIndex("data3"));
                        int type = ims.getInt(ims.getColumnIndex("data5"));
                        imAddressesBean.setLabel(ContactsConverter.getImLabel(type));
                        imAddressesBean.setDisplayLabel(ContactsConverter.getDisplayLabel(imAddressesBean.getLabel(), imsLabel));
                        String data = ims.getString(ims.getColumnIndex("data1"));
                        ContactsBackupBean.ContactsBean.InstantMessageAddressesBean.ServiceBean serviceBean = new ContactsBackupBean.ContactsBean.InstantMessageAddressesBean.ServiceBean();
                        serviceBean.setService(imAddressesBean.getDisplayLabel());
                        serviceBean.setUsername(data);
                        imAddressesBean.setService(serviceBean);
                        imAddresses.add(imAddressesBean);
                    }

                    contactsBean.setInstantMessageAddresses(imAddresses);
                    ims.close();
                }

                String relationWhere = "contact_id = ? AND mimetype = ?";
                String[] relationWhereParams = new String[]{contactId, "vnd.android.cursor.item/relation"};
                Cursor relationCur = contentResolver.query(ContactsContract.Data.CONTENT_URI, null, relationWhere, relationWhereParams, null);
                if (relationCur != null) {
                    List<ContactsBackupBean.ContactsBean.ContactRelationsBean> relationsBeans = new ArrayList<>();
                    while (relationCur.moveToNext()) {
                        ContactsBackupBean.ContactsBean.ContactRelationsBean contactRelationsBean = new ContactsBackupBean.ContactsBean.ContactRelationsBean();
                        String relationsLabel = relationCur.getString(relationCur.getColumnIndex("data3"));
                        int type = relationCur.getInt(relationCur.getColumnIndex("data2"));
                        contactRelationsBean.setLabel(ContactsConverter.getRelationLabel(type));
                        contactRelationsBean.setDisplayLabel(ContactsConverter.getDisplayLabel(contactRelationsBean.getLabel(), relationsLabel));
                        String data = relationCur.getString(relationCur.getColumnIndex("data1"));
                        ContactsBackupBean.ContactsBean.ContactRelationsBean.RelationBean relationBean = new ContactsBackupBean.ContactsBean.ContactRelationsBean.RelationBean();
                        relationBean.setName(data);
                        contactRelationsBean.setRelation(relationBean);
                        relationsBeans.add(contactRelationsBean);
                    }

                    contactsBean.setContactRelations(relationsBeans);
                    relationCur.close();
                }

                String urlWhere = "contact_id = ? AND mimetype = ?";
                String[] urlWhereParams = new String[]{contactId, "vnd.android.cursor.item/website"};
                Cursor urlCur = contentResolver.query(ContactsContract.Data.CONTENT_URI, null, urlWhere, urlWhereParams, null);
                if (urlCur != null) {
                    List<ContactsBackupBean.ContactsBean.UrlAddressesBean> urlAddresses = new ArrayList<>();

                    while (urlCur.moveToNext()) {
                        ContactsBackupBean.ContactsBean.UrlAddressesBean urlAddresseBean = new ContactsBackupBean.ContactsBean.UrlAddressesBean();
                        String urlLabel = urlCur.getString(urlCur.getColumnIndex("data3"));
                        int type = urlCur.getInt(urlCur.getColumnIndex("data2"));
                        urlAddresseBean.setLabel(ContactsConverter.getUrlLabel(type));
                        urlAddresseBean.setDisplayLabel(ContactsConverter.getDisplayLabel(urlAddresseBean.getLabel(), urlLabel));
                        String url = urlCur.getString(urlCur.getColumnIndex("data1"));
                        urlAddresseBean.setUrl(url);
                        urlAddresses.add(urlAddresseBean);
                    }

                    contactsBean.setUrlAddresses(urlAddresses);
                    urlCur.close();
                }

                String eventWhere = "contact_id = ? AND mimetype = ?";
                String[] eventParams = new String[]{contactId, "vnd.android.cursor.item/contact_event"};
                Cursor eventCur = contentResolver.query(ContactsContract.Data.CONTENT_URI, null, eventWhere, eventParams, null);
                if (eventCur != null) {
                    List<ContactsBackupBean.ContactsBean.DatesBean> dates = new ArrayList<>();

                    while (eventCur.moveToNext()) {
                        ContactsBackupBean.ContactsBean.DatesBean date = new ContactsBackupBean.ContactsBean.DatesBean();
                        String photoLabel = eventCur.getString(eventCur.getColumnIndex("data3"));
                        int type = eventCur.getInt(eventCur.getColumnIndex("data2"));
                        String startTime = eventCur.getString(eventCur.getColumnIndex("data1"));
                        String[] ymd = startTime.split("-");
                        if (ymd.length > 0) {
                            String var40;
                            int var43;
                            switch (type) {
                                case 0:
                                    ContactsBackupBean.ContactsBean.NonGregorianBirthdayBean nonGregorianBirthdayBean = new ContactsBackupBean.ContactsBean.NonGregorianBirthdayBean();
                                    var40 = ymd[0];
                                    var43 = Integer.parseInt(var40);
                                    nonGregorianBirthdayBean.setYear(var43);
                                    var40 = ymd[1];
                                    var43 = Integer.parseInt(var40);
                                    nonGregorianBirthdayBean.setMonth(var43);
                                    var40 = ymd[2];
                                    var43 = Integer.parseInt(var40);
                                    nonGregorianBirthdayBean.setDay(var43);
                                    nonGregorianBirthdayBean.setCaledarIdentifier(2);
                                    contactsBean.setNonGregorianBirthday(nonGregorianBirthdayBean);
                                    break;
                                case 1:
                                case 2:
                                    ContactsBackupBean.ContactsBean.DatesBean.DateBean dateBean = new ContactsBackupBean.ContactsBean.DatesBean.DateBean();
                                    date.setLabel(ContactsConverter.getEventLabel(type));
                                    date.setDisplayLabel(ContactsConverter.getDisplayLabel(date.getLabel(), photoLabel));
                                    var40 = ymd[0];
                                    var43 = Integer.parseInt(var40);
                                    dateBean.setYear(var43);
                                    var40 = ymd[1];
                                    var43 = Integer.parseInt(var40);
                                    dateBean.setMonth(var43);
                                    var40 = ymd[2];
                                    var43 = Integer.parseInt(var40);
                                    dateBean.setDay(var43);
                                    dateBean.setCaledarIdentifier(0);
                                    date.setDate(dateBean);
                                    dates.add(date);
                                    break;
                                case 3:
                                default:
                                    ContactsBackupBean.ContactsBean.BirthdayBean birthdayBean = new ContactsBackupBean.ContactsBean.BirthdayBean();
                                    var40 = ymd[1];
                                    var43 = Integer.parseInt(var40);
                                    birthdayBean.setMonth(var43);
                                    var40 = ymd[2];
                                    var43 = Integer.parseInt(var40);
                                    birthdayBean.setDay(var43);
                                    birthdayBean.setCaledarIdentifier(0);
                                    contactsBean.setBirthday(birthdayBean);
                            }

                        }
                    }

                    contactsBean.setDates(dates);
                    eventCur.close();
                }

                Cursor addressCur = contentResolver.query(ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI, null, "contact_id = " + contactId, null, null);
                if (addressCur != null) {
                    List<ContactsBackupBean.ContactsBean.PostalAddressesBean> postalAddresses = new ArrayList<>();

                    while (addressCur.moveToNext()) {
                        ContactsBackupBean.ContactsBean.PostalAddressesBean postalAddresseBean = new ContactsBackupBean.ContactsBean.PostalAddressesBean();
                        String postalLabel = addressCur.getString(addressCur.getColumnIndex("data3"));
                        int type = addressCur.getInt(addressCur.getColumnIndex("data2"));
                        postalAddresseBean.setLabel(ContactsConverter.getPostalAddressesLabel(type));
                        postalAddresseBean.setDisplayLabel(ContactsConverter.getDisplayLabel(postalAddresseBean.getLabel(), postalLabel));
                        addressCur.getString(addressCur.getColumnIndex("data1"));
                        String city = addressCur.getString(addressCur.getColumnIndex("data7"));
                        String country = addressCur.getString(addressCur.getColumnIndex("data10"));
                        String street = addressCur.getString(addressCur.getColumnIndex("data4"));
                        String postalCode = addressCur.getString(addressCur.getColumnIndex("data9"));
                        String region = addressCur.getString(addressCur.getColumnIndex("data8"));
                        ContactsBackupBean.ContactsBean.PostalAddressesBean.AddressBean bean = new ContactsBackupBean.ContactsBean.PostalAddressesBean.AddressBean();
                        bean.setCity(city);
                        bean.setCountry(country);
                        bean.setPostalCode(postalCode);
                        bean.setState(region);
                        bean.setStreet(street);
                        bean.setSubLocality(region);
                        postalAddresseBean.setAddress(bean);
                        postalAddresses.add(postalAddresseBean);
                    }

                    contactsBean.setPostalAddresses(postalAddresses);
                    addressCur.close();
                }

                String orgWhere = "contact_id = ? AND mimetype = ?";
                String[] orgWhereParams = new String[]{contactId, "vnd.android.cursor.item/organization"};
                Cursor orgCur = contentResolver.query(ContactsContract.Data.CONTENT_URI, null, orgWhere, orgWhereParams, null);
                if (orgCur != null) {
                    if (orgCur.moveToFirst()) {
                        String company = orgCur.getString(orgCur.getColumnIndex("data1"));
                        contactsBean.setOrganizationName(company);
                        String organizationPh = orgCur.getString(orgCur.getColumnIndex("data8"));
                        contactsBean.setPhoneticOrganizationName(organizationPh);
                        String department = orgCur.getString(orgCur.getColumnIndex("data5"));
                        contactsBean.setDepartmentName(department);
                        String title = orgCur.getString(orgCur.getColumnIndex("data4"));
                        contactsBean.setJobTitle(title);
                    }

                    orgCur.close();
                }

                String nickNameWhere = "contact_id = ? AND mimetype = ?";
                String[] nickNameWhereParams = new String[]{contactId, "vnd.android.cursor.item/nickname"};
                Cursor nickNameCur = contentResolver.query(ContactsContract.Data.CONTENT_URI, null, nickNameWhere, nickNameWhereParams, null);
                if (nickNameCur != null) {
                    if (nickNameCur.moveToFirst()) {
                        String nickname = nickNameCur.getString(nickNameCur.getColumnIndex("data1"));
                        contactsBean.setNickname(nickname);
                    }

                    nickNameCur.close();
                }

                String photoWhere = "contact_id = ? AND mimetype = ?";
                String[] photoWhereParams = new String[]{contactId, "vnd.android.cursor.item/photo"};
                Cursor photoCur = contentResolver.query(ContactsContract.Data.CONTENT_URI, null, photoWhere, photoWhereParams, null);
                if (photoCur != null) {
                    if (photoCur.moveToFirst()) {
                        byte[] imageData = photoCur.getBlob(photoCur.getColumnIndex("data15"));

                        try {
                            ContactsBackupBean.ContactsBean var126 = contactsBean;
                            String var10001;
                            if (imageData != null) {
                                String encode = Base64.encodeToString(imageData, 0);
                                var126 = contactsBean;
                                var10001 = encode;
                            } else {
                                var10001 = null;
                            }

                            var126.setImageData(var10001);
                        } catch (Exception var52) {
                            var52.printStackTrace();
                        }
                    }
                    photoCur.close();
                }

                String noteWhere = "contact_id = ? AND mimetype = ?";
                String[] noteWhereParams = new String[]{contactId, "vnd.android.cursor.item/note"};
                Cursor noteCur = contentResolver.query(ContactsContract.Data.CONTENT_URI, null, noteWhere, noteWhereParams, null);
                if (noteCur != null) {
                    if (noteCur.moveToFirst()) {
                        String note = noteCur.getString(noteCur.getColumnIndex("data1"));
                        contactsBean.setNote(note);
                    }

                    noteCur.close();
                }

                String nameWhere = "contact_id = ? AND mimetype = ?";
                String[] nameWhereParams = new String[]{contactId, "vnd.android.cursor.item/name"};
                Cursor nameCur = contentResolver.query(ContactsContract.Data.CONTENT_URI, null, nameWhere, nameWhereParams, null);
                if (nameCur != null) {
                    if (nameCur.moveToFirst()) {
                        nameCur.getString(nameCur.getColumnIndex("data1"));
                        String familyName = nameCur.getString(nameCur.getColumnIndex("data3"));
                        String phoneticFamilyName = nameCur.getString(nameCur.getColumnIndex("data9"));
                        String midName = nameCur.getString(nameCur.getColumnIndex("data5"));
                        String phoneticMidName = nameCur.getString(nameCur.getColumnIndex("data8"));
                        String givenName = nameCur.getString(nameCur.getColumnIndex("data2"));
                        String phoneticGivenName = nameCur.getString(nameCur.getColumnIndex("data7"));
                        String prefix = nameCur.getString(nameCur.getColumnIndex("data4"));
                        String suffix = nameCur.getString(nameCur.getColumnIndex("data6"));
                        contactsBean.setFamilyName(familyName);
                        contactsBean.setPhoneticFamilyName(phoneticFamilyName);
                        contactsBean.setMiddleName(midName);
                        contactsBean.setPhoneticMiddleName(phoneticMidName);
                        contactsBean.setGivenName(givenName);
                        contactsBean.setPhoneticGivenName(phoneticGivenName);
                        contactsBean.setNameSuffix(suffix);
                        contactsBean.setNamePrefix(prefix);
                    }

                    nameCur.close();
                }
            }

            cursor.close();
            response.setContacts(contacts);
            return response;
        } else {
            return null;
        }
    }

    public static boolean writeContacts(ContactsBackupBean response) {
        if (response != null) {
            List<ContactsBackupBean.ContactsBean> var21 = response.getContacts();
            if (var21 != null) {

                for (ContactsBackupBean.ContactsBean elementIv : var21) {
                    rawContactInsertIndex = operations.size();
                    ContentProviderOperation operation = ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                            .withValue("account_type", null)
                            .withValue("account_name", null)
                            .withYieldAllowed(true)
                            .build();
                    operations.add(operation);
                    addIMAddresses(elementIv.getInstantMessageAddresses());
                    addPhoneNumbers(elementIv.getPhoneNumbers());
                    addStructuredName(elementIv);
                    addImageInfo(elementIv.getImageData());
                    addPostalAddresses(elementIv.getPostalAddresses());
                    addUrlAddresses(elementIv.getUrlAddresses());
                    addEmailAddresses(elementIv.getEmailAddresses());
                    addNicknameInfo(elementIv.getNickname());
                    addNoteInfo(elementIv.getNote());
                    addOrganizationInfo(elementIv);
                    addRelations(elementIv.getContactRelations());
                    addBirthday(elementIv.getBirthday(), elementIv.getNonGregorianBirthday());
                    addDates(elementIv.getDates());
//                    addGroups(elementIv.getGroups());
                    addSocialProfiles(elementIv.getSocialProfiles());
                    ContentResolver resolver = Utils.getApp().getContentResolver();

                    try {
                        resolver.applyBatch("com.android.contacts", operations);
                    } catch (Exception var19) {
                        var19.printStackTrace();
                    } finally {
                        operations.clear();
                    }
                }
            }

            Log.i(TAG, "联系人写入完成");
        }

        return true;
    }

    private static void addImageInfo(String imageData) {
        if (imageData != null) {
            byte[] bytes = Base64.decode(imageData, 0);
            ContentProviderOperation var10000 = ContentProviderOperation.newInsert(DATA_URI)
                    .withValueBackReference(RAW_CONTACT_ID, rawContactInsertIndex)
                    .withValue(MIME_TYPE, "vnd.android.cursor.item/photo")
                    .withValue("data15", bytes)
                    .withYieldAllowed(true)
                    .build();
            if (var10000 != null) {
                operations.add(var10000);
            }
        }

    }

    private static void addSocialProfiles(List<ContactsBackupBean.ContactsBean.SocialProfilesBean> socialProfiles) {
        if (socialProfiles != null) {

            for (ContactsBackupBean.ContactsBean.SocialProfilesBean elementIv : socialProfiles) {
                if (elementIv.getLabel() != null) {
                    ContactsBackupBean.ContactsBean.SocialProfilesBean.SocialBean var10002 = elementIv.getSocial();
                    ContentProviderOperation var14 = ContentProviderOperation.newInsert(DATA_URI)
                            .withValueBackReference(RAW_CONTACT_ID, rawContactInsertIndex)
                            .withValue(MIME_TYPE, "vnd.android.cursor.item/im")
                            .withValue("data2", 2)
                            .withValue("data5", -1)
                            .withValue("data1", var10002 != null ? var10002.getUrlString() : null)
                            .withValue("data6", elementIv.getDisplayLabel())
                            .withValue("data3", elementIv.getDisplayLabel())
                            .withYieldAllowed(true)
                            .build();
                    if (var14 != null) {
                        operations.add(var14);
                    }
                }
            }
        }

    }

    /*private static void addGroups(List<ContactsBackupBean.ContactsBean.GroupsBean> groups) {
        if (groups != null) {
            for (ContactsBackupBean.ContactsBean.GroupsBean elementIv : groups) {
                if (elementIv.getLabel() != null) {
                    ContentProviderOperation var10000 = ContentProviderOperation.newInsert(DATA_URI)
                            .withValueBackReference(RAW_CONTACT_ID, rawContactInsertIndex)
                            .withValue(MIME_TYPE, "vnd.android.cursor.item/group_membership")
                            .withValue("data1", elementIv.getName())
                            .withYieldAllowed(true)
                            .build();
                    if (var10000 != null) {
                        operations.add(var10000);
                    }
                }
            }
        }

    }*/

    private static void addDates(List<ContactsBackupBean.ContactsBean.DatesBean> dates) {
        if (dates != null) {

            for (ContactsBackupBean.ContactsBean.DatesBean elementIv : dates) {
                String var10000 = elementIv.getLabel();
                if (var10000 != null) {
                    StringBuilder var10002 = new StringBuilder();
                    ContactsBackupBean.ContactsBean.DatesBean.DateBean var10003 = elementIv.getDate();
                    var10002 = var10002.append(var10003 != null ? var10003.getYear() : 0).append("-");
                    var10003 = elementIv.getDate();
                    var10002 = var10002.append(var10003 != null ? var10003.getMonth() : 0).append("-");
                    var10003 = elementIv.getDate();
                    ContentProviderOperation var15 = ContentProviderOperation.newInsert(DATA_URI)
                            .withValueBackReference(RAW_CONTACT_ID, rawContactInsertIndex)
                            .withValue(MIME_TYPE, "vnd.android.cursor.item/contact_event")
                            .withValue("data2", ContactsConverter.getEventType(var10000))
                            .withValue("data1", var10002.append(var10003 != null ? var10003.getDay() : 0).toString())
                            .withValue("data3", ContactsConverter.getDisplayLabel(var10000, elementIv.getDisplayLabel()))
                            .withYieldAllowed(true)
                            .build();
                    if (var15 != null) {
                        operations.add(var15);
                    }
                }
            }
        }

    }

    private static void addBirthday(ContactsBackupBean.ContactsBean.BirthdayBean birthday, ContactsBackupBean.ContactsBean.NonGregorianBirthdayBean nonGregorianBirthday) {
        ContentProviderOperation operation;
        if (birthday != null) {
            operation = ContentProviderOperation.newInsert(DATA_URI)
                    .withValueBackReference(RAW_CONTACT_ID, rawContactInsertIndex)
                    .withValue(MIME_TYPE, "vnd.android.cursor.item/contact_event")
                    .withValue("data2", 3)
                    .withValue("data1", TimeUtils.getNowString((new SimpleDateFormat("yyyy", Locale.getDefault()))) + "-" + birthday.getMonth() + "-" + birthday.getDay())
                    .withYieldAllowed(true)
                    .build();
            if (operation != null) {
                operations.add(operation);
            }
        }

        if (nonGregorianBirthday != null) {
            operation = ContentProviderOperation.newInsert(DATA_URI)
                    .withValueBackReference(RAW_CONTACT_ID, rawContactInsertIndex)
                    .withValue(MIME_TYPE, "vnd.android.cursor.item/contact_event")
                    .withValue("data2", 0)
                    .withValue("data1", "" + nonGregorianBirthday.getYear() + "-" + nonGregorianBirthday.getMonth() + "-" + nonGregorianBirthday.getDay())
                    .withValue("data3", "农历生日")
                    .withYieldAllowed(true)
                    .build();
            if (operation != null) {
                operations.add(operation);
            }
        }

    }

    private static void addRelations(List<ContactsBackupBean.ContactsBean.ContactRelationsBean> contactRelations) {
        if (contactRelations != null) {

            for (ContactsBackupBean.ContactsBean.ContactRelationsBean elementIv : contactRelations) {
                String var10000 = elementIv.getLabel();
                if (var10000 != null) {
                    ContactsBackupBean.ContactsBean.ContactRelationsBean.RelationBean var10002 = elementIv.getRelation();
                    ContentProviderOperation var14 = ContentProviderOperation.newInsert(DATA_URI)
                            .withValueBackReference(RAW_CONTACT_ID, rawContactInsertIndex)
                            .withValue(MIME_TYPE, "vnd.android.cursor.item/relation")
                            .withValue("data2", ContactsConverter.getRelationType(var10000))
                            .withValue("data1", var10002 != null ? var10002.getName() : null)
                            .withValue("data3", ContactsConverter.getDisplayLabel(var10000, elementIv.getDisplayLabel()))
                            .withYieldAllowed(true)
                            .build();
                    if (var14 != null) {
                        operations.add(var14);
                    }
                }
            }
        }

    }

    private static void addOrganizationInfo(ContactsBackupBean.ContactsBean it) {
        ContentProviderOperation operation = ContentProviderOperation.newInsert(DATA_URI)
                .withValueBackReference(RAW_CONTACT_ID, rawContactInsertIndex)
                .withValue(MIME_TYPE, "vnd.android.cursor.item/organization")
                .withValue("data2", 2).withValue("data1", it.getOrganizationName())
                .withValue("data5", it.getDepartmentName())
                .withValue("data4", it.getJobTitle())
                .withValue("data7", "")
                .withValue("data6", "")
                .withValue("data9", "")
                .withValue("data8", it.getPhoneticOrganizationName())
                .withYieldAllowed(true).build();
        if (operation != null) {
            operations.add(operation);
        }
    }

    private static void addEmailAddresses(List<ContactsBackupBean.ContactsBean.EmailAddressesBean> emailAddresses) {
        if (emailAddresses != null) {

            for (ContactsBackupBean.ContactsBean.EmailAddressesBean elementIv : emailAddresses) {
                String var10000 = elementIv.getLabel();
                if (var10000 != null) {
                    ContentProviderOperation operation = ContentProviderOperation.newInsert(DATA_URI)
                            .withValueBackReference(RAW_CONTACT_ID, rawContactInsertIndex)
                            .withValue(MIME_TYPE, "vnd.android.cursor.item/email_v2")
                            .withValue("data2", ContactsConverter.getEmailType(var10000))
                            .withValue("data1", elementIv.getEmail())
                            .withValue("data3", ContactsConverter.getDisplayLabel(var10000, elementIv.getDisplayLabel()))
                            .withYieldAllowed(true)
                            .build();
                    if (operation != null) {
                        operations.add(operation);
                    }
                }
            }
        }

    }

    private static void addNoteInfo(String note) {
        if (note != null) {
            ContentProviderOperation operation = ContentProviderOperation.newInsert(DATA_URI)
                    .withValueBackReference(RAW_CONTACT_ID, rawContactInsertIndex)
                    .withValue(MIME_TYPE, "vnd.android.cursor.item/note")
                    .withValue("data1", note)
                    .withYieldAllowed(true)
                    .build();

            if (operation != null) {
                operations.add(operation);
            }
        }

    }

    private static void addNicknameInfo(String nickname) {
        if (nickname != null) {
            ContentProviderOperation operation = ContentProviderOperation.newInsert(DATA_URI)
                    .withValueBackReference(RAW_CONTACT_ID, rawContactInsertIndex)
                    .withValue(MIME_TYPE, "vnd.android.cursor.item/nickname")
                    .withValue("data1", nickname)
                    .withYieldAllowed(true)
                    .build();
            if (operation != null) {
                operations.add(operation);
            }
        }

    }

    private static void addUrlAddresses(List<ContactsBackupBean.ContactsBean.UrlAddressesBean> urlAddresses) {
        if (urlAddresses != null) {

            for (ContactsBackupBean.ContactsBean.UrlAddressesBean elementIv : urlAddresses) {
                String var10000 = elementIv.getLabel();
                if (var10000 != null) {
                    ContentProviderOperation operation = ContentProviderOperation.newInsert(DATA_URI)
                            .withValueBackReference(RAW_CONTACT_ID, rawContactInsertIndex)
                            .withValue(MIME_TYPE, "vnd.android.cursor.item/website")
                            .withValue("data2", ContactsConverter.getUrlType(var10000))
                            .withValue("data1", elementIv.getUrl())
                            .withValue("data3", ContactsConverter.getDisplayLabel(var10000, elementIv.getDisplayLabel()))
                            .withYieldAllowed(true)
                            .build();
                    if (operation != null) {
                        operations.add(operation);
                    }
                }
            }
        }

    }

    private static void addPostalAddresses(List<ContactsBackupBean.ContactsBean.PostalAddressesBean> postalAddresses) {
        if (postalAddresses != null) {

            for (ContactsBackupBean.ContactsBean.PostalAddressesBean elementIv : postalAddresses) {
                ContactsBackupBean.ContactsBean.PostalAddressesBean.AddressBean var10000 = elementIv.getAddress();
                if (var10000 != null) {
                    ContentProviderOperation operation = ContentProviderOperation.newInsert(DATA_URI)
                            .withValueBackReference(RAW_CONTACT_ID, rawContactInsertIndex)
                            .withValue(MIME_TYPE, "vnd.android.cursor.item/postal-address_v2")
                            .withValue("data2", ContactsConverter.getPostalAddresseType(elementIv.getLabel()))
                            .withValue("data10", var10000.getCountry())
                            .withValue("data7", var10000.getCity())
                            .withValue("data8", var10000.getSubLocality())
                            .withValue("data4", var10000.getStreet())
                            .withValue("data9", var10000.getPostalCode())
                            .withValue("data1", var10000.getCountry() + var10000.getState() + var10000.getCity() + var10000.getSubLocality() + var10000.getStreet())
                            .withValue("data3", ContactsConverter.getDisplayLabel(elementIv.getLabel(), elementIv.getDisplayLabel()))
                            .withYieldAllowed(true)
                            .build();
                    if (operation != null) {
                        operations.add(operation);
                    }
                }
            }
        }

    }

    private static void addStructuredName(ContactsBackupBean.ContactsBean it) {
        ContentProviderOperation operation = ContentProviderOperation.newInsert(DATA_URI)
                .withValueBackReference(RAW_CONTACT_ID, rawContactInsertIndex)
                .withValue(MIME_TYPE, "vnd.android.cursor.item/name")
                .withValue("data1", (it.getFamilyName() + it.getGivenName()))
                .withValue("data3", it.getFamilyName())
                .withValue("data9", it.getPhoneticFamilyName())
                .withValue("data5", it.getMiddleName())
                .withValue("data8", it.getPhoneticMiddleName())
                .withValue("data2", it.getGivenName())
                .withValue("data7", it.getPhoneticGivenName())
                .withValue("data10", 3).withValue("data11", 3)
                .withValue("data4", it.getNamePrefix())
                .withValue("data6", it.getNameSuffix())
                .withYieldAllowed(true)
                .build();
        if (operation != null) {
            operations.add(operation);
        }
    }

    private static void addPhoneNumbers(List<ContactsBackupBean.ContactsBean.PhoneNumbersBean> it) {
        if (it != null) {
            for (ContactsBackupBean.ContactsBean.PhoneNumbersBean elementIv : it) {
                String var10000 = elementIv.getLabel();
                if (var10000 != null) {
                    ContentProviderOperation.Builder var14 = ContentProviderOperation.newInsert(DATA_URI).withValueBackReference(RAW_CONTACT_ID, rawContactInsertIndex).withValue(MIME_TYPE, "vnd.android.cursor.item/phone_v2").withValue("data2", ContactsConverter.getPhoneType(var10000));
                    ContactsBackupBean.ContactsBean.PhoneNumbersBean.PhoneNumberBean var10002 = elementIv.getPhoneNumber();
                    ContentProviderOperation operation = var14.withValue("data1", var10002 != null ? var10002.getNumber() : null).withValue("data3", ContactsConverter.getDisplayLabel(var10000, elementIv.getDisplayLabel())).withYieldAllowed(true).build();
                    if (operation != null) {
                        operations.add(operation);
                    }
                }
            }
        }

    }

    private static void addIMAddresses(List<ContactsBackupBean.ContactsBean.InstantMessageAddressesBean> it) {
        if (it != null) {

            for (ContactsBackupBean.ContactsBean.InstantMessageAddressesBean elementIv : it) {
                String var10000 = elementIv.getLabel();
                if (var10000 != null) {
                    ContactsBackupBean.ContactsBean.InstantMessageAddressesBean.ServiceBean service = elementIv.getService();
                    ContentProviderOperation operation = ContentProviderOperation.newInsert(DATA_URI)
                            .withValueBackReference(RAW_CONTACT_ID, rawContactInsertIndex)
                            .withValue(MIME_TYPE, "vnd.android.cursor.item/im")
                            .withValue("data2", 2)
                            .withValue("data5", ContactsConverter.getImProtocol(var10000)).withValue("data1", service != null ? service.getUsername() : null)
                            .withValue("data6", elementIv.getDisplayLabel())
                            .withValue("data3", ContactsConverter.getDisplayLabel(var10000, elementIv.getDisplayLabel()))
                            .withYieldAllowed(true)
                            .build();
                    if (operation != null) {
                        operations.add(operation);
                    }
                }
            }
        }

    }

}
