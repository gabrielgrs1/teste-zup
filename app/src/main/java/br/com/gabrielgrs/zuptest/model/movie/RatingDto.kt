package br.com.gabrielgrs.zuptest.model.movie

import java.io.Serializable

/**
 * Created by gabrielgrs
 * Date: 29/04/19
 * Time: 21:05
 * Project: ZUPTest
 */
data class RatingDto(
    private var source: String?,
    private var value: String?
) : Serializable