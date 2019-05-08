package br.com.gabrielgrs.zuptest.utils

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.pm.ActivityInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.gabrielgrs.zuptest.application.ZupTestApplication

/**
 * Created by gabrielgrs
 * Date: 2019-05-01
 * Time: 13:20
 * Project: ZUPTest
 */


fun getContext(): Application {
    return ZupTestApplication.applicationContext() as Application
}

fun Context.makeToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.showProgress() {
    GenericProgressDialog.instance.show(this.supportFragmentManager)
}

fun hideProgress() {
    GenericProgressDialog.instance.dismiss()
}


fun Activity.forceHideKeyboard() {
    val view = this.currentFocus
    if (view != null) {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun Activity.initActivity(idLayout: Int) {
    this.setContentView(idLayout)
    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
}