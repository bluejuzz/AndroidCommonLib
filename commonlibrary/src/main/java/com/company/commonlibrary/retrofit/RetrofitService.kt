package com.company.commonlibrary.retrofit


import io.reactivex.Flowable
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.QueryMap
import retrofit2.http.Streaming
import retrofit2.http.Url

/**
 * @author dinglaihong
 * @email 18279727279@163.com
 * @date 2018/08/13 13:52
 * @des
 */

interface RetrofitService {
    /**
     * 提交json数据
     *
     * @param url  请求地址
     * @param body 请求体
     * @return Observable
     */
    @POST
    fun postJson(@Url url: String, @Body body: RequestBody): Flowable<Response<ResponseBody>>

    /**
     * 提交json数据
     *
     * @param url      请求路径
     * @param queryMap 请求参数集合
     * @return Observable
     */
    @GET
    fun getJson(@Url url: String, @QueryMap queryMap: Map<String, Any>): Flowable<Response<ResponseBody>>

    /**
     * 提交json数据
     *
     * @param url 请求地址
     * @return Observable
     */
    @GET
    fun getJson(@Url url: String): Flowable<Response<ResponseBody>>


    /**
     * 上传文件
     *
     * @param url         url
     * @param description description
     * @param filePart    filePart
     * @return Observable
     */
    @Multipart
    @POST
    fun postFile(@Url url: String, @Part("parameter") description: RequestBody, @Part filePart: MultipartBody.Part): Flowable<Response<ResponseBody>>

    /**
     * 下载文件
     *
     * @param downloadUrl 下载地址
     * @return Observable
     */
    @Streaming
    @GET
    fun downloadFile(@Url downloadUrl: String): Flowable<ResponseBody>

}
