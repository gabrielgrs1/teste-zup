package br.com.gabrielgrs.zuptest.ui.search.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.gabrielgrs.zuptest.R
import br.com.gabrielgrs.zuptest.model.moviesearch.MovieSearchDto
import br.com.gabrielgrs.zuptest.ui.detail.activity.DetailActivity
import br.com.gabrielgrs.zuptest.ui.search.adapter.SearchAdapter
import br.com.gabrielgrs.zuptest.ui.search.viewmodel.SearchViewModel
import br.com.gabrielgrs.zuptest.utils.*
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.intentFor

class SearchActivity : AppCompatActivity(), IGenerickCallback {


    private lateinit var viewModel: SearchViewModel
    private lateinit var mAdapter: SearchAdapter
    private var movieList: MutableList<MovieSearchDto> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivity(R.layout.activity_search)

        init()
    }

    override fun onError(error: String) {
        forceHideKeyboard()
        makeToast(error)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initActionBar() {
        setSupportActionBar(search_toolbar)
        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun init() {
        initActionBar()
        initViewModel()
        initListeners()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val recyclerView = search_movies_recyclerview
        mAdapter = SearchAdapter(this, movieList, onMovieClickListener())
        recyclerView.adapter = mAdapter

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
    }

    private fun onMovieClickListener() = { movie: MovieSearchDto ->
        showProgress()

        viewModel.searchMovieByImdbId(movie.imdbId).observe(this@SearchActivity, Observer { response ->
            hideProgress()
            startActivity(intentFor<DetailActivity>("movie" to response))
        })

    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        viewModel.setCallback(this)
    }

    private fun initListeners() {
        initOnTextChangeListener()
    }

    private fun initOnTextChangeListener() {
        search_title_movie_inputlayout.editText!!.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!!.isNotEmpty()) {
                    val movieName = s.toString()
                    viewModel.searchMovieByTitle(movieName).observe(this@SearchActivity, Observer { response ->
                        if (response != null && response.search != null
                            && response.totalResults.toInt() > 0 && response.response == "True"
                        ) {
                            movieList.clear()
                            movieList.addAll(response.search)

                            mAdapter.notifyDataSetChanged()
                        }
                        //TODO implementar scroll pra pegar as outras paginas
                        //TODO Quando a requisicao tiver menos de 10 resultados é pq nao tem mais pagina pra frente

                    })
                }
            }
        })
    }
}