package br.com.gabrielgrs.zuptest.model.movie

import java.io.Serializable

/**
 * Created by gabrielgrs
 * Date: 29/04/19
 * Time: 21:02
 * Project: ZUPTest
 */
data class MovieDto(
    var title: String?,
    var year: String?,
    var rated: String?,
    var released: String?,
    var runtime: String?,
    var genre: String?,
    var director: String?,
    var writer: String?,
    var actors: String?,
    var plot: String?,
    var language: String?,
    var country: String?,
    var awards: String?,
    var poster: String?,
    var ratings: List<RatingDto>,
    var metascore: String?,
    var imdbRating: String?,
    var imdbVotes: String?,
    var imdbID: String?,
    var type: String?,
    var dVD: String?,
    var boxOffice: String?,
    var production: String?,
    var website: String?,
    var response: String?
) : Serializable {

    companion object {
        const val MOVIE_LIST_KEY = "movie_list"
    }
}