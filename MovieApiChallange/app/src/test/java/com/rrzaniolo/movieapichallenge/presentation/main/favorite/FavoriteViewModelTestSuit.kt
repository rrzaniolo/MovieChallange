package com.rrzaniolo.movieapichallenge.presentation.main.favorite

import androidx.lifecycle.MutableLiveData
import com.rrzaniolo.movieapichallenge.data.models.MovieResponse
import com.rrzaniolo.movieapichallenge.domain.usecase.FavoriteUseCase
import com.rrzaniolo.movieapichallenge.mockedMovieResponse
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import org.junit.Before
import org.junit.Test
import org.koin.test.AutoCloseKoinTest
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*

/**
 * Created by Rodrigo Rodrigues Zaniolo on 9/30/2019.
 * All rights reserved.
 */
class FavoriteViewModelTestSuit: AutoCloseKoinTest() {

    lateinit var disposableTask: CompositeDisposable

    lateinit var useCase: FavoriteUseCase
    lateinit var viewModel: FavoriteViewModel

    @Before
    fun setUp(){
        disposableTask = mock(CompositeDisposable::class.java)
        useCase = mock(FavoriteUseCase::class.java)

        viewModel = FavoriteViewModel(useCase)
    }

    @Test
    fun `Assert onCleared`(){
        viewModel.disposableTask = disposableTask
        viewModel.onCleared()

        verify(viewModel.disposableTask, times(1)).clear()
    }

    @Test
    fun`Assert loadFavorites success`(){
        viewModel._favoriteViewState = mock(MutableLiveData<FavoriteViewState>()::class.java)

        val mockedResponse = ArrayList<MovieResponse>()
        mockedResponse.add(mockedMovieResponse)
        `when`(useCase.getFavorites()).thenReturn(Flowable.just(mockedResponse))

        `when`(viewModel._favoriteViewState.postValue(ArgumentMatchers.any())).then{
            assert(
                it.arguments[0] is FavoriteViewState.ShowLoading ||
                        it.arguments[0] is FavoriteViewState.ShowSuccess
            )
        }
        viewModel.loadFavorites()
    }

    @Test
    fun`Assert loadFavorites success empty`(){
        viewModel._favoriteViewState = mock(MutableLiveData<FavoriteViewState>()::class.java)

        val mockedResponse = ArrayList<MovieResponse>()
        `when`(useCase.getFavorites()).thenReturn(Flowable.just(mockedResponse))

        `when`(viewModel._favoriteViewState.postValue(ArgumentMatchers.any())).then{
            assert(
                it.arguments[0] is FavoriteViewState.ShowLoading ||
                        it.arguments[0] is FavoriteViewState.ShowEmpty
            )
        }
        viewModel.loadFavorites()
    }

    @Test
    fun`Assert loadFavorites error`(){
        viewModel._favoriteViewState = mock(MutableLiveData<FavoriteViewState>()::class.java)

        `when`(useCase.getFavorites()).thenReturn(Flowable.error(Throwable()))

        `when`(viewModel._favoriteViewState.postValue(ArgumentMatchers.any())).then{
            assert(
                it.arguments[0] is FavoriteViewState.ShowLoading ||
                        it.arguments[0] is FavoriteViewState.ShowError
            )
        }
        viewModel.loadFavorites()
    }

}