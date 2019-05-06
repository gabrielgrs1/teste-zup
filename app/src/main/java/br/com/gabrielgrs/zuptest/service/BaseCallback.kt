package br.com.gabrielgrs.zuptest.service

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by gabrielgrs
 * Date: 29/04/19
 * Time: 21:27
 * Project: ZUPTest
 */
abstract class BaseCallback<T> : Callback<T> {

    abstract fun onSuccess(response: T?)

    abstract fun onError(error: String)

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful) {
            onSuccess(response.body())
        } else {
            onError(response.message())
        }
    }
}