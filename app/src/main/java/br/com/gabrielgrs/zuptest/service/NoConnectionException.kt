package br.com.gabrielgrs.zuptest.service

import br.com.gabrielgrs.zuptest.R
import br.com.gabrielgrs.zuptest.application.ZupTestApplication
import java.io.IOException


/**
 * Created by gabrielgrs
 * Date: 01/05/19
 * Time: 12:33
 * Project: ZUPTest
 */
class NoConnectionException : IOException() {

    override val message: String?
        get() = ZupTestApplication().applicationContext.getString(R.string.generic_no_connection)

}