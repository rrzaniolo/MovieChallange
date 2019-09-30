package com.rrzaniolo.movieapichallenge.presentation.main.favorite

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rrzaniolo.movieapichallenge.data.models.GenreResponse
import com.rrzaniolo.movieapichallenge.domain.usecase.FavoriteUseCase
import com.rrzaniolo.movieapichallenge.presentation.base.BaseViewModel

/**
 * Created by Rodrigo Rodrigues Zaniolo on 9/28/2019.
 * All rights reserved.
 */
class FavoriteViewModel(private val useCase: FavoriteUseCase): BaseViewModel() {

    @Suppress("PropertyName")
    @VisibleForTesting var _favoriteViewState = MutableLiveData<FavoriteViewState>()
    val favoriteViewState: LiveData<FavoriteViewState>
    get() = _favoriteViewState

    @VisibleForTesting private lateinit var genres: GenreResponse

    fun loadFavorites() {
        _favoriteViewState.postValue(FavoriteViewState.ShowLoading)
        disposableTask.add(
            useCase.getFavorites().subscribe({
                if (it.isEmpty()) {
                    _favoriteViewState.postValue(FavoriteViewState.ShowEmpty)
                } else {
                    _favoriteViewState.postValue(FavoriteViewState.ShowSuccess(it))
                }
            }, {
                _favoriteViewState.postValue(FavoriteViewState.ShowError)
            })
        )
    }
}