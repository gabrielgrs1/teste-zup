package br.com.gabrielgrs.zuptest.application

import android.app.Application
import android.content.Context
import br.com.gabrielgrs.zuptest.service.RetrofitConfig


/**
 * Created by gabrielgrs
 * Date: 29/04/19
 * Time: 21:26
 * Project: ZUPTest
 */
class ZupTestApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: ZupTestApplication? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }


    override fun onCreate() {
        super.onCreate()
        RetrofitConfig().configureRetrofit()
    }


}