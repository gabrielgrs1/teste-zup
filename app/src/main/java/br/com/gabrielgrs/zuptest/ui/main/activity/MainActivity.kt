package br.com.gabrielgrs.zuptest.ui.main.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.gabrielgrs.zuptest.R
import br.com.gabrielgrs.zuptest.model.movie.MovieDto
import br.com.gabrielgrs.zuptest.ui.detail.activity.DetailActivity
import br.com.gabrielgrs.zuptest.ui.main.adapter.MovieAdapter
import br.com.gabrielgrs.zuptest.ui.main.viewmodel.MainViewModel
import br.com.gabrielgrs.zuptest.ui.search.activity.SearchActivity
import br.com.gabrielgrs.zuptest.utils.IGenericCallback
import br.com.gabrielgrs.zuptest.utils.forceHideKeyboard
import br.com.gabrielgrs.zuptest.utils.initActivity
import br.com.gabrielgrs.zuptest.utils.makeToast
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor

class MainActivity : AppCompatActivity(), View.OnClickListener, IGenericCallback {


    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivity(R.layout.activity_main)

        init()
    }

    override fun onError(error: String) {
        forceHideKeyboard()
        makeToast(error)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.main_search_icon_imageview -> performSearchButton()
        }
    }

    private fun init() {
        initViewModel()
        initRecyclerView()
        initListeners()
    }

    private fun initRecyclerView() {
        val recyclerView = main_movies_recyclerview
        recyclerView.adapter = MovieAdapter(viewModel.getMovies(), onMovieClickListener())

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

    private fun initListeners() {
        initOnClickListener()
    }

    private fun initOnClickListener() {
        main_search_icon_imageview.setOnClickListener(this)
    }
}
