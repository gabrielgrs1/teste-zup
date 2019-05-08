package br.com.gabrielgrs.zuptest.service

import br.com.gabrielgrs.zuptest.BuildConfig
import br.com.gabrielgrs.zuptest.application.ZupTestApplication
import br.com.gabrielgrs.zuptest.utils.Utils
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by gabrielgrs
 * Date: 29/04/19
 * Time: 22:12
 * Project: ZUPTest
 */
class RetrofitConfig {

    fun configureRetrofit(): Retrofit? {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(checkConnectionInterceptor())
            .addInterceptor(addQueryParameters()).build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()

    }

    private fun addQueryParameters(): (Interceptor.Chain) -> Response {
        return { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("apiKey", BuildConfig.API_KEY)
                .build()

            val requestBuilder = original.newBuilder()
                .url(url)

            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }

    private fun checkConnectionInterceptor(): (Interceptor.Chain) -> Response {
        return { chain ->
            if (!Utils().isOnline()) {
                throw NoConnectionException()
            }
            chain.proceed(chain.request())
        }
    }

    fun getRetrofit(): IRetrofitService {
        return ZupTestApplication.retrofit!!.create(IRetrofitService::class.java)
    }

}