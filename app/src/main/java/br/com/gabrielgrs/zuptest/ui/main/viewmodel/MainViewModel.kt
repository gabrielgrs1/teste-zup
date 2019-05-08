package br.com.gabrielgrs.zuptest.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import br.com.gabrielgrs.zuptest.R
import br.com.gabrielgrs.zuptest.application.ZupTestApplication
import br.com.gabrielgrs.zuptest.model.movie.MovieDto
import br.com.gabrielgrs.zuptest.utils.IGenerickCallback
import br.com.gabrielgrs.zuptest.utils.getContext
import com.orhanobut.hawk.Hawk

/**
 * Created by gabrielgrs
 * Date: 29/04/19
 * Time: 21:21
 * Project: ZUPTest
 */
class MainViewModel : ViewModel() {

    private lateinit var callback: IGenerickCallback
    private val tag = "MainViewModel"

    fun setCallback(callback: IGenerickCallback) {
        this.callback = callback
    }

    fun getMovies(): MutableList<MovieDto> {
        var movieList: MutableList<MovieDto> = mutableListOf()

        if (Hawk.get(ZupTestApplication.MOVIE_LIST_KEY, mutableListOf<MovieDto>()).isNotEmpty()) {
            movieList = Hawk.get(ZupTestApplication.MOVIE_LIST_KEY)
        } else {
            Log.e(tag, "Nenhum filme encontrado!")
            callback.onError(getContext().getString(R.string.generic_movie_not_found))
        }

        return movieList
    }

}