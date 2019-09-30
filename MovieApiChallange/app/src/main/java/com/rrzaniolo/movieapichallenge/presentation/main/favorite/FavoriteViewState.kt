package com.rrzaniolo.movieapichallenge.presentation.main.favorite

import com.rrzaniolo.movieapichallenge.data.models.MovieResponse

/**
 * Created by Rodrigo Rodrigues Zaniolo on 9/28/2019.
 * All rights reserved.
 */
open class FavoriteViewState {
    data class ShowSuccess(val movies: List<MovieResponse>): FavoriteViewState()

    object ShowEmpty: FavoriteViewState()
    object ShowError: FavoriteViewState()
    object ShowLoading: FavoriteViewState()
}