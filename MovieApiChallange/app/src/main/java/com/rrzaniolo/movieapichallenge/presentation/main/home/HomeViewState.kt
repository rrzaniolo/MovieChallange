package com.rrzaniolo.movieapichallenge.presentation.main.home

import com.rrzaniolo.movieapichallenge.data.models.GenreResponse
import com.rrzaniolo.movieapichallenge.data.models.MovieResponse

/**
 * Created by Rodrigo Rodrigues Zaniolo on 9/28/2019.
 * All rights reserved.
 */
open class HomeViewState {
    data class ShowSuccess(val movies: List<MovieResponse>, val genres: GenreResponse): HomeViewState()

    object ShowError: HomeViewState()
    object ShowLoading: HomeViewState()
}