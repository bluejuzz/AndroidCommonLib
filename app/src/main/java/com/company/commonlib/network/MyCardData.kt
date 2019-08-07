package com.company.commonlib.network

/**
 * @author dlh
 * @email 18279727279@163.com
 * @date 2019/04/15 17:07
 * @des
 */
class MyCardData {

    /**
     * body : [{"cardUrl":"http://192.168.50.223:8890/AAAAOARXRc303.jpg"},{"endTime":" http://192.168.50.223:8890/AAAAOARXRc303.jpg "}]
     * code : 8000
     * msg : 操作成功
     */

    var body: List<CardBean>? = null

    class CardBean {
        /**
         * cardUrl : http://192.168.50.223:8890/AAAAOARXRc303.jpg
         * endTime :  http://192.168.50.223:8890/AAAAOARXRc303.jpg
         */

        var cardUrl: String? = null
        var endTime: String? = null
    }
}
