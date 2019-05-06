package br.com.gabrielgrs.zuptest.model.movie

import java.io.Serializable

/**
 * Created by gabrielgrs
 * Date: 29/04/19
 * Time: 21:02
 * Project: ZUPTest
 */
data class MovieDto(
    private var title: String?,
    private var year: String?,
    private var rated: String?,
    private var released: String?,
    private var runtime: String?,
    private var genre: String?,
    private var director: String?,
    private var writer: String?,
    private var actors: String?,
    private var plot: String?,
    private var language: String?,
    private var country: String?,
    private var awards: String?,
    private var poster: String?,
    private var ratings: List<RatingDto>,
    private var metascore: String?,
    private var imdbRating: String?,
    private var imdbVotes: String?,
    private var imdbID: String?,
    private var type: String?,
    private var dVD: String?,
    private var boxOffice: String?,
    private var production: String?,
    private var website: String?,
    private var response: String?
) : Serializable