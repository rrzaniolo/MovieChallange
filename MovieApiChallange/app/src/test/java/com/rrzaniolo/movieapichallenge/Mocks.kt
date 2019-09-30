package com.rrzaniolo.movieapichallenge

import com.rrzaniolo.movieapichallenge.data.models.*

/**
 * Created by Rodrigo Rodrigues Zaniolo on 9/30/2019.
 * All rights reserved.
 */

val mockedGenreResponse = GenreResponse(ArrayList<Genre>().apply{ add(mockedGenre) })
val mockedGenre = Genre(0, "")

val mockedDatesResponse = DatesResponse("1900-10-01", "2019-9-30")
val mockedMovieResponse = MovieResponse()
val mockedGetMovieResponse = GetMovieResponse(
    mockedDatesResponse, 1,
    ArrayList<MovieResponse>().apply { add(mockedMovieResponse) },
    1, 10
)
