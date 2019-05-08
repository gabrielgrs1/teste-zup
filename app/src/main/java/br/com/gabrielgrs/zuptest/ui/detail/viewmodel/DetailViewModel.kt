package br.com.gabrielgrs.zuptest.ui.detail.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import br.com.gabrielgrs.zuptest.application.ZupTestApplication
import br.com.gabrielgrs.zuptest.model.movie.MovieDto
import br.com.gabrielgrs.zuptest.utils.IGenerickCallback
import com.orhanobut.hawk.Hawk

/**
 * Created by gabrielgrs
 * Date: 29/04/19
 * Time: 21:24
 * Project: ZUPTest
 */
class DetailViewModel : ViewModel() {

    private lateinit var callback: IGenerickCallback
    private val tag = "DetailViewModel"

    fun setCallback(callback: IGenerickCallback) {
        this.callback = callback
    }

    fun saveMovie(movie: MovieDto) {
        val movieList: MutableList<MovieDto> = Hawk.get(ZupTestApplication.MOVIE_LIST_KEY, mutableListOf())

        if (!movieList.contains(movie)) {
            movieList.add(movie)
            Hawk.put(ZupTestApplication.MOVIE_LIST_KEY, movieList)
        } else {
            Log.e(tag, "Filme já está salvo!")

        }
    }

    fun deleteMovie(movie: MovieDto) {
        val movieList: MutableList<MovieDto> = Hawk.get(ZupTestApplication.MOVIE_LIST_KEY, mutableListOf())

        if (movieList.contains(movie)) {
            movieList.remove(movie)
            Hawk.put(ZupTestApplication.MOVIE_LIST_KEY, movieList)
        } else {
            Log.e(tag, "Filme nao está na lista!")

        }
    }
}