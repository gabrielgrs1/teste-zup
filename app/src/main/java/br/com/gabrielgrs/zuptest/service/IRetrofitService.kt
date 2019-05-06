package br.com.gabrielgrs.zuptest.service

import br.com.gabrielgrs.zuptest.model.movie.MovieDto
import retrofit2.http.GET

/**
 * Created by gabrielgrs
 * Date: 29/04/19
 * Time: 21:26
 * Project: ZUPTest
 */
interface IRetrofitService {

    @GET("/")
    fun searchMovie(): List<MovieDto>
}