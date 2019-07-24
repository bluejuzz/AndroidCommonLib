package com.company.commonlibrary.bean


/**
 * @author dlh
 * @email 18279727279@163.com
 * @date 2019/05/05 13:48
 * @des 网络请求Response基类，适用于格式为{code,message,body}的数据,实际使用需要实现本接口，
 * 查看示例[ResponseExample2],[ResponseExample1]
 */
interface IResponse<T> {
    /**
     * 获得返回码
     *
     * @return 返回码
     */
    val code: Int

    /**
     * 获得返回信息
     *
     * @return 返回信息
     */
    val message: String?

    /**
     * 获得返回内容
     *
     * @return 返回内容
     */
    val body: T

    /**
     * 是否成功
     *
     * @return 是否成功（代表本次请求是否有返回请求内容）
     */
    val isSuccessful: Boolean

}
