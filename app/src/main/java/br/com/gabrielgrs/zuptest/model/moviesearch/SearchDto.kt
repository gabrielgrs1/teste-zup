package br.com.gabrielgrs.zuptest.model.moviesearch

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by gabrielgrs
 * Date: 2019-05-07
 * Time: 21:57
 * Project: ZUPTest
 */
data class SearchDto(
    @SerializedName("Search") val search: List<MovieSearchDto>,
    @SerializedName("totalResults") val totalResults: String,
    @SerializedName("Response") val response: String
) : Serializable