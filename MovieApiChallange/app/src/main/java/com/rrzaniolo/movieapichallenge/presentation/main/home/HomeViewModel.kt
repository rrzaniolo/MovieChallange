package com.rrzaniolo.movieapichallenge.presentation.main.home

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rrzaniolo.movieapichallenge.data.models.GenreResponse
import com.rrzaniolo.movieapichallenge.domain.usecase.HomeUseCase
import com.rrzaniolo.movieapichallenge.presentation.base.BaseViewModel

/**
 * Created by Rodrigo Rodrigues Zaniolo on 9/28/2019.
 * All rights reserved.
 */
class HomeViewModel(private val useCase: HomeUseCase): BaseViewModel() {

    @Suppress("PropertyName")
    @VisibleForTesting private val _homeViewState by lazy { MutableLiveData<HomeViewState>() }
    val homeViewState: LiveData<HomeViewState>
    get() = _homeViewState

    @VisibleForTesting private lateinit var genres: GenreResponse

    private fun getMovies(){
        disposableTask.add(
            useCase.getMovies()
                .subscribe (
                    {
                        /* Success */
                        _homeViewState.postValue(HomeViewState.HideLoading)
                        it.results?.also {data ->
                            _homeViewState.postValue(HomeViewState.ShowSuccess(data, genres))
                        }?:run{
                            _homeViewState.postValue(HomeViewState.ShowError)
                        }
                    },
                    {
                        /* Error */
                        _homeViewState.postValue(HomeViewState.HideLoading)
                        _homeViewState.postValue(HomeViewState.ShowError)
                    }
                )
        )
    }

    private fun getGenres(){
        _homeViewState.value = HomeViewState.ShowLoading
        disposableTask.add(
            useCase.getGenres()
                .subscribe (
                    {
                        /* Success */
                        it?.also {
                            genres = it
                            getMovies()
                        }?:run{
                            _homeViewState.postValue(HomeViewState.HideLoading)
                            _homeViewState.postValue(HomeViewState.ShowError)
                        }
                    },
                    {
                        /* Error */
                        _homeViewState.postValue(HomeViewState.HideLoading)
                        _homeViewState.postValue(HomeViewState.ShowError)
                    }
                )
        )
    }

    fun loadMovies(){
        getGenres()
    }
}