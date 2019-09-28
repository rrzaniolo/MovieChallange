package com.rrzaniolo.movieapichallange.data.models

import com.squareup.moshi.Json

/**
 * Created by Rodrigo Rodrigues Zaniolo on 9/28/2019.
 * All rights reserved.
 */
data class GenreResponse(
    @field:Json(name = "genres") val genreList: List<Genre>
)

data class Genre(
    val id: Int,
    @field:Json(name = "name") val name: String
)