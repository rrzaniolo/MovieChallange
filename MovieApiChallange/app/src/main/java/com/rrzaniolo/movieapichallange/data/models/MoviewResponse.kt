package com.rrzaniolo.movieapichallange.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

/**
 * Created by Rodrigo Rodrigues Zaniolo on 9/25/2019.
 * All rights reserved.
 */

@Parcelize
data class GetMovieResponse(
    @field:Json(name = "dates") val dates: DatesResponse?,
    @field:Json(name = "page") val page: Int?,
    @field:Json(name = "results") val results: List<MovieResponse>?,
    @field:Json(name = "total_pages") val totalPages: Int?,
    @field:Json(name = "total_results") val totalResults: Int?
) : Parcelable

@Parcelize
data class DatesResponse(
    @field:Json(name = "maximum") val maximum: String?,
    @field:Json(name = "minimum") val minimum: String?
) : Parcelable

@Entity
@Parcelize
data class MovieResponse(
    @Ignore @field:Json(name = "adult") var adult: Boolean?,
    @field:Json(name = "backdrop_path") var backdropPath: String?,
    @Ignore @field:Json(name = "genre_ids") var genreIds: List<Int?>?,
    @PrimaryKey @field:Json(name = "id") var id: Int?,
    @field:Json(name = "original_language") var originalLanguage: String?,
    @field:Json(name = "original_title") var originalTitle: String?,
    @field:Json(name = "overview") var overview: String?,
    @field:Json(name = "popularity") var popularity: Double?,
    @field:Json(name = "poster_path") var posterPath: String?,
    @field:Json(name = "release_date") var releaseDate: String?,
    @field:Json(name = "title") var title: String?,
    @field:Json(name = "video") var video: Boolean?,
    @field:Json(name = "vote_average") var voteAverage: Double,
    @field:Json(name = "vote_count") var voteCount: Int
) : Parcelable {
    constructor() : this(false, "", null, 0, "", "", "", 0.0, "", "", "", false, 0.0, 0)
}