package br.com.gabrielgrs.zuptest.ui.detail.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.gabrielgrs.zuptest.R
import br.com.gabrielgrs.zuptest.utils.initActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivity(R.layout.activity_detail)
    }
}
