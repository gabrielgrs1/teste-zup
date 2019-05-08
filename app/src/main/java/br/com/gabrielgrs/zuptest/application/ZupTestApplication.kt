package br.com.gabrielgrs.zuptest.application

import android.app.Application
import android.content.Context
import br.com.gabrielgrs.zuptest.service.RetrofitConfig
import com.orhanobut.hawk.Hawk


/**
 * Created by gabrielgrs
 * Date: 29/04/19
 * Time: 21:26
 * Project: ZUPTest
 */
class ZupTestApplication : Application() {

    companion object {
        const val MOVIE_LIST_KEY = "movie_list"

        private var instance: ZupTestApplication? = null
        var retrofit = RetrofitConfig().configureRetrofit()

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
        Hawk.init(applicationContext).build()
    }


}