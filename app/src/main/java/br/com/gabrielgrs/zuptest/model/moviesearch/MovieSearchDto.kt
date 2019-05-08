package br.com.gabrielgrs.zuptest.model.moviesearch

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by gabrielgrs
 * Date: 2019-05-07
 * Time: 21:53
 * Project: ZUPTest
 */
data class MovieSearchDto(
    @SerializedName("Title") val title: String?,
    @SerializedName("Year") val year: String?,
    @SerializedName("imdbID") val imdbId: String?,
    @SerializedName("Type") val type: String?,
    @SerializedName("Poster") val poster: String?
) : Serializable
