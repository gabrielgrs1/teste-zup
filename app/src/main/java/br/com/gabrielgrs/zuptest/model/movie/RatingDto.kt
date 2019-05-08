package br.com.gabrielgrs.zuptest.model.movie

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by gabrielgrs
 * Date: 29/04/19
 * Time: 21:05
 * Project: ZUPTest
 */
data class RatingDto(
    @SerializedName("Source") val source: String?,
    @SerializedName("Value") val value: String?
) : Serializable