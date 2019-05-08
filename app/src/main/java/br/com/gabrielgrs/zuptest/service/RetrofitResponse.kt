package br.com.gabrielgrs.zuptest.service

/**
 * Created by gabrielgrs
 * Date: 2019-05-06
 * Time: 22:23
 * Project: ZUPTest
 */
interface RetrofitResponse<T> {
    fun onResponseSuccess(response: T?)
    fun onResponseError(message: String)
    fun onResponseErrorNotFound()
}