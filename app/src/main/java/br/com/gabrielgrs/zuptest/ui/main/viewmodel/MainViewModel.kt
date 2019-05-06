package br.com.gabrielgrs.zuptest.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import br.com.gabrielgrs.zuptest.R
import br.com.gabrielgrs.zuptest.model.movie.MovieDto
import br.com.gabrielgrs.zuptest.utils.IGenericCallback
import br.com.gabrielgrs.zuptest.utils.getContext
import com.orhanobut.hawk.Hawk

/**
 * Created by gabrielgrs
 * Date: 29/04/19
 * Time: 21:21
 * Project: ZUPTest
 */
class MainViewModel : ViewModel() {

    private lateinit var callback: IGenericCallback
    private val TAG = "MainViewModel"

    fun setCallback(callback: IGenericCallback) {
        this.callback = callback
    }

    fun getMovies(): MutableList<MovieDto> {
        var movieList: MutableList<MovieDto> = mutableListOf()

        if (Hawk.get(MovieDto.MOVIE_LIST_KEY, mutableListOf<MovieDto>()).isNotEmpty()) {
            movieList = Hawk.get(MovieDto.MOVIE_LIST_KEY)
        } else {
            Log.e(TAG, "Nenhum filme encontrado!")
            callback.onError(getContext().getString(R.string.main_movies_not_found_toast))
        }

        return movieList
    }

}