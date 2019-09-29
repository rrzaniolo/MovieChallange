package com.rrzaniolo.movieapichallenge.presentation.detail

import com.rrzaniolo.movieapichallenge.data.models.MovieResponse

/**
 * Created by Rodrigo Rodrigues Zaniolo on 9/28/2019.
 * All rights reserved.
 */
open class DetailViewStates {
    object SaveSuccess : DetailViewStates()
    object SaveError : DetailViewStates()

    object RemoveSuccess : DetailViewStates()
    object RemoveError : DetailViewStates()

    data class GetMovieSuccess(val movie: MovieResponse) : DetailViewStates()
    object GetMovieError : DetailViewStates()

    object ShowLoading: DetailViewStates()
    object HideLoading: DetailViewStates()
}