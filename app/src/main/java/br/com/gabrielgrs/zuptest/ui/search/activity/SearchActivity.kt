package br.com.gabrielgrs.zuptest.ui.search.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.gabrielgrs.zuptest.R
import br.com.gabrielgrs.zuptest.utils.initActivity

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivity(R.layout.activity_search)
    }
}
