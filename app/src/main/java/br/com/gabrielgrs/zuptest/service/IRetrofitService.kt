package br.com.gabrielgrs.zuptest.service

import br.com.gabrielgrs.zuptest.model.movie.MovieDto
import br.com.gabrielgrs.zuptest.model.moviesearch.SearchDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by gabrielgrs
 * Date: 29/04/19
 * Time: 21:26
 * Project: ZUPTest
 */
interface IRetrofitService {

    @GET("/")
    fun searchMovieByTitle(@Query("s") movieTitle: String): Call<SearchDto>

    @GET("/")
    fun searchMovieByImdbId(@Query("i") movieImdbId: String): Call<MovieDto>
}