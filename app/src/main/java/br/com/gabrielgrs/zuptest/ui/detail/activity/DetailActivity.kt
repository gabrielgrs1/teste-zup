package br.com.gabrielgrs.zuptest.ui.detail.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import br.com.gabrielgrs.zuptest.R
import br.com.gabrielgrs.zuptest.model.movie.MovieDto
import br.com.gabrielgrs.zuptest.ui.detail.viewmodel.DetailViewModel
import br.com.gabrielgrs.zuptest.utils.IGenerickCallback
import br.com.gabrielgrs.zuptest.utils.forceHideKeyboard
import br.com.gabrielgrs.zuptest.utils.initActivity
import br.com.gabrielgrs.zuptest.utils.makeToast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), IGenerickCallback {


    private lateinit var viewModel: DetailViewModel
    private lateinit var movie: MovieDto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivity(R.layout.activity_detail)

        init()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_delete -> {
                performDeleteButton()
                return true
            }
            R.id.action_save -> {
                performSaveButton()
                return true
            }
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onError(error: String) {
        forceHideKeyboard()
        makeToast(error)
    }

    private fun performSaveButton() {
        viewModel.saveMovie(movie)
        makeToast(getString(R.string.detail_movie_saved_toast))
    }

    private fun performDeleteButton() {
        viewModel.deleteMovie(movie)
        makeToast(getString(R.string.detail_movie_deleted_toast))
    }

    private fun initActionBar() {
        setSupportActionBar(detail_toolbar)
        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
    }


    private fun init() {
        initViewModel()
        getMovieExtras()
        initActionBar()
    }

    private fun getMovieExtras() {
        if (intent != null && intent.extras != null) {
            movie = intent.extras!!.getSerializable("movie") as MovieDto

            setBanner()
            setTexts()
        } else {
            makeToast(getString(R.string.generic_error_get_movie))
            finish()
        }


    }

    private fun setBanner() {
        if (movie.poster == "N/A") {
            detail_banner_imageview.setImageDrawable(getDrawable(R.drawable.error_image))
        } else {
            Glide.with(this)
                .load(movie.poster)
                .thumbnail(0.1f)
                .into(detail_banner_imageview)
        }
    }

    private fun setTexts() {
        val title = movie.title + " " + movie.year
        detail_title_textview.text = title
        detail_plot_textview.text = movie.plot
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.setCallback(this)
    }

}
