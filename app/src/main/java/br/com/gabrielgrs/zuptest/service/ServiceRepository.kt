package br.com.gabrielgrs.zuptest.service

import br.com.gabrielgrs.zuptest.model.movie.MovieDto
import br.com.gabrielgrs.zuptest.model.moviesearch.SearchDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

/**
 * Created by gabrielgrs
 * Date: 29/04/19
 * Time: 21:26
 * Project: ZUPTest
 */
class ServiceRepository {

    fun searchMovieByTitle(movieTitle: String, page: Int, listener: RetrofitResponse<SearchDto>) {
        val call: Call<SearchDto> = RetrofitConfig().getRetrofit().searchMovieByTitle(movieTitle, page)

        call.enqueue(object : Callback<SearchDto> {
            override fun onFailure(call: Call<SearchDto>, t: Throwable) {
                listener.onResponseError(t.localizedMessage)
            }

            override fun onResponse(call: Call<SearchDto>, response: Response<SearchDto>) {
                if (response.code() == HttpURLConnection.HTTP_NOT_FOUND) {
                    listener.onResponseErrorNotFound()
                } else {
                    response.body().let {
                        listener.onResponseSuccess(response.body())
                    }
                }
            }
        })
    }

    fun searchMovieByImdbId(movieImdbId: String, listener: RetrofitResponse<MovieDto>) {
        val call: Call<MovieDto> = RetrofitConfig().getRetrofit().searchMovieByImdbId(movieImdbId)

        call.enqueue(object : Callback<MovieDto> {
            override fun onFailure(call: Call<MovieDto>, t: Throwable) {
                listener.onResponseError(t.localizedMessage)
            }

            override fun onResponse(call: Call<MovieDto>, response: Response<MovieDto>) {
                if (response.code() == HttpURLConnection.HTTP_NOT_FOUND) {
                    listener.onResponseErrorNotFound()
                } else {
                    response.body().let {
                        listener.onResponseSuccess(response.body())
                    }
                }
            }
        })
    }


}