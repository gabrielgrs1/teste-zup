package br.com.gabrielgrs.zuptest.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import br.com.gabrielgrs.zuptest.application.ZupTestApplication


/**
 * Created by gabrielgrs
 * Date: 01/05/19
 * Time: 12:36
 * Project: ZUPTest
 */
class Utils {
    fun isOnline(): Boolean {
        val connectivityManager =
            ZupTestApplication().applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }
}