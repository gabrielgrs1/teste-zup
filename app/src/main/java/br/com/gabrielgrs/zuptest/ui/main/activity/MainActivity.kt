package br.com.gabrielgrs.zuptest.ui.main.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.gabrielgrs.zuptest.R
import br.com.gabrielgrs.zuptest.model.movie.MovieDto
import br.com.gabrielgrs.zuptest.ui.detail.activity.DetailActivity
import br.com.gabrielgrs.zuptest.ui.main.adapter.MainAdapter
import br.com.gabrielgrs.zuptest.ui.main.viewmodel.MainViewModel
import br.com.gabrielgrs.zuptest.ui.search.activity.SearchActivity
import br.com.gabrielgrs.zuptest.utils.IGenerickCallback
import br.com.gabrielgrs.zuptest.utils.forceHideKeyboard
import br.com.gabrielgrs.zuptest.utils.initActivity
import br.com.gabrielgrs.zuptest.utils.makeToast
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor

class MainActivity : AppCompatActivity(), IGenerickCallback {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivity(R.layout.activity_main)

        init()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                performSearchButton()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onError(error: String) {
        forceHideKeyboard()
        makeToast(error)
    }

    override fun onResume() {
        super.onResume()
        initRecyclerView()

    }

    private fun initActionBar() {
        setSupportActionBar(main_toolbar)
    }

    private fun init() {
        initActionBar()
        initViewModel()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val recyclerView = main_movies_recyclerview
        recyclerView.adapter = MainAdapter(viewModel.getMovies(), onMovieClickListener())

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
    }

    private fun onMovieClickListener() = { movieDto: MovieDto ->
        startActivity(intentFor<DetailActivity>("movie" to movieDto))
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.setCallback(this)
    }

    private fun performSearchButton() {
        startActivity(intentFor<SearchActivity>())
    }

}
