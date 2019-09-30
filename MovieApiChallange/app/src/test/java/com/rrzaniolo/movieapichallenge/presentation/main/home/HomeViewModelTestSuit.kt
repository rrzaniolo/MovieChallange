package com.rrzaniolo.movieapichallenge.presentation.main.home

import androidx.lifecycle.MutableLiveData
import com.rrzaniolo.movieapichallenge.domain.usecase.HomeUseCase
import com.rrzaniolo.movieapichallenge.mockedGenreResponse
import com.rrzaniolo.movieapichallenge.mockedGetMovieResponse
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
class HomeViewModelTestSuit: AutoCloseKoinTest() {

    lateinit var disposableTask: CompositeDisposable

    lateinit var useCase: HomeUseCase
    lateinit var viewModel: HomeViewModel

    @Before
    fun setUp(){
        disposableTask = mock(CompositeDisposable::class.java)
        useCase = mock(HomeUseCase::class.java)

        viewModel = HomeViewModel(useCase)
    }

    @Test
    fun `Assert onCleared`(){
        viewModel.disposableTask = disposableTask
        viewModel.onCleared()

        verify(viewModel.disposableTask, times(1)).clear()
    }

    @Test
    fun`Assert loadMovies success`(){
        viewModel._homeViewState = mock(MutableLiveData<HomeViewState>()::class.java)

        `when`(useCase.getGenres()).thenReturn(Flowable.just(mockedGenreResponse))
        `when`(useCase.getMovies()).thenReturn(Flowable.just(mockedGetMovieResponse))

        `when`(viewModel._homeViewState.postValue(ArgumentMatchers.any())).then{
            assert(
                it.arguments[0] is HomeViewState.ShowLoading ||
                        it.arguments[0] is HomeViewState.ShowSuccess
            )
        }
        viewModel.loadMovies()
    }

    @Test
    fun`Assert loadMovies error on getGenres`(){
        viewModel._homeViewState = mock(MutableLiveData<HomeViewState>()::class.java)

        `when`(useCase.getGenres()).thenReturn(Flowable.error(Throwable()))
        `when`(useCase.getMovies()).thenReturn(Flowable.just(mockedGetMovieResponse))

        `when`(viewModel._homeViewState.postValue(ArgumentMatchers.any())).then{
            assert(
                it.arguments[0] is HomeViewState.ShowLoading ||
                        it.arguments[0] is HomeViewState.ShowError
            )
        }
        viewModel.loadMovies()
    }

    @Test
    fun`Assert loadMovies error on getMovies`(){
        viewModel._homeViewState = mock(MutableLiveData<HomeViewState>()::class.java)

        `when`(useCase.getGenres()).thenReturn(Flowable.just(mockedGenreResponse))
        `when`(useCase.getMovies()).thenReturn(Flowable.error(Throwable()))

        `when`(viewModel._homeViewState.postValue(ArgumentMatchers.any())).then{
            assert(
                it.arguments[0] is HomeViewState.ShowLoading ||
                        it.arguments[0] is HomeViewState.ShowError
            )
        }
        viewModel.loadMovies()
    }
}