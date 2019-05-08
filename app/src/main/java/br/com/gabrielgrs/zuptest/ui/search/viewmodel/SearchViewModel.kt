package br.com.gabrielgrs.zuptest.ui.search.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.gabrielgrs.zuptest.R
import br.com.gabrielgrs.zuptest.model.movie.MovieDto
import br.com.gabrielgrs.zuptest.model.moviesearch.SearchDto
import br.com.gabrielgrs.zuptest.service.RetrofitResponse
import br.com.gabrielgrs.zuptest.service.ServiceRepository
import br.com.gabrielgrs.zuptest.utils.IGenerickCallback
import br.com.gabrielgrs.zuptest.utils.getContext

/**
 * Created by gabrielgrs
 * Date: 29/04/19
 * Time: 21:21
 * Project: ZUPTest
 */
class SearchViewModel : ViewModel() {

    private lateinit var searchMovieByTitleResponse: MutableLiveData<SearchDto>
    private lateinit var searchMovieByImdbIdResponse: MutableLiveData<MovieDto>
    private lateinit var mCallback: IGenerickCallback
    private val TAG = "SearchViewModel"

    fun setCallback(callback: IGenerickCallback) {
        this.mCallback = callback
    }

    fun searchMovieByTitle(movieTitle: String?, page : Int): LiveData<SearchDto> {
        searchMovieByTitleResponse = MutableLiveData()

        ServiceRepository().searchMovieByTitle(movieTitle!!, page, object : RetrofitResponse<SearchDto> {
            override fun onResponseSuccess(response: SearchDto?) {
                searchMovieByTitleResponse.value = response
            }

            override fun onResponseErrorNotFound() {
                Log.e(TAG, "Nenhum filme encontrado")
                mCallback.onError(getContext().getString(R.string.generic_movie_not_found))
            }

            override fun onResponseError(message: String) {
                Log.e(TAG, message)
                mCallback.onError(getContext().getString(R.string.generic_server_error))
            }
        })

        return searchMovieByTitleResponse
    }

    fun searchMovieByImdbId(movieImdbId: String?): LiveData<MovieDto> {
        searchMovieByImdbIdResponse = MutableLiveData()

        ServiceRepository().searchMovieByImdbId(movieImdbId!!, object : RetrofitResponse<MovieDto> {
            override fun onResponseSuccess(response: MovieDto?) {
                searchMovieByImdbIdResponse.value = response
            }

            override fun onResponseErrorNotFound() {
                Log.e(TAG, "Nenhum filme encontrado")
                mCallback.onError(getContext().getString(R.string.generic_movie_not_found))
            }

            override fun onResponseError(message: String) {
                Log.e(TAG, message)
                mCallback.onError(getContext().getString(R.string.generic_server_error))
            }
        })

        return searchMovieByImdbIdResponse
    }

}