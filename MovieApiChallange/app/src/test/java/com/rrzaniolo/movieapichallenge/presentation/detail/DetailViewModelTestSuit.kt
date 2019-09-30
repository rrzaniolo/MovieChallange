package com.rrzaniolo.movieapichallenge.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.room.EmptyResultSetException
import com.rrzaniolo.movieapichallenge.domain.usecase.DetailUseCase
import com.rrzaniolo.movieapichallenge.mockedMovieResponse
import io.reactivex.Completable
import io.reactivex.Single
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
class DetailViewModelTestSuit: AutoCloseKoinTest() {

    lateinit var disposableTask: CompositeDisposable

    lateinit var useCase: DetailUseCase
    lateinit var viewModel: DetailViewModel

    @Before
    fun setUp(){
        disposableTask = mock(CompositeDisposable::class.java)
        useCase = mock(DetailUseCase::class.java)

        viewModel = DetailViewModel(useCase)
    }

    @Test
    fun `Assert onCleared`(){
        viewModel.disposableTask = disposableTask
        viewModel.onCleared()

        verify(viewModel.disposableTask, times(1)).clear()
    }

    @Test
    fun`Assert getMovieData success`(){
        viewModel._detailViewState = mock(MutableLiveData<DetailViewStates>()::class.java)

        `when`(useCase.getMovie(mockedMovieResponse)).thenReturn(Single.just(mockedMovieResponse))

        `when`(viewModel._detailViewState.postValue(ArgumentMatchers.any())).then{
            assert(
                it.arguments[0] is DetailViewStates.ShowLoading ||
                        it.arguments[0] is DetailViewStates.GetMovieSuccess
            )
        }
        viewModel.getMovieData(mockedMovieResponse)
    }

    @Test
    fun`Assert getMovieData error`(){
        viewModel._detailViewState = mock(MutableLiveData<DetailViewStates>()::class.java)

        `when`(useCase.getMovie(mockedMovieResponse)).thenReturn(Single.error(Throwable()))

        `when`(viewModel._detailViewState.postValue(ArgumentMatchers.any())).then{
            assert(
                it.arguments[0] is DetailViewStates.ShowLoading ||
                        it.arguments[0] is DetailViewStates.GetMovieSuccess
            )
        }
        viewModel.getMovieData(mockedMovieResponse)
    }

    @Test
    fun`Assert saveOrRemoveMovie save success`(){
        viewModel._detailViewState = mock(MutableLiveData<DetailViewStates>()::class.java)

        `when`(useCase.saveMovie(mockedMovieResponse)).thenReturn(Completable.complete())
        `when`(useCase.getMovie(mockedMovieResponse)).thenReturn(Single.error(EmptyResultSetException("")))

        `when`(viewModel._detailViewState.postValue(ArgumentMatchers.any())).then{
            assert(
                it.arguments[0] is DetailViewStates.ShowLoading ||
                        it.arguments[0] is DetailViewStates.SaveSuccess
            )
        }
        viewModel.saveOrRemoveMovie(mockedMovieResponse)
    }

    @Test
    fun`Assert saveOrRemoveMovie save error onGetMovie`(){
        viewModel._detailViewState = mock(MutableLiveData<DetailViewStates>()::class.java)

        `when`(useCase.saveMovie(mockedMovieResponse)).thenReturn(Completable.complete())
        `when`(useCase.getMovie(mockedMovieResponse)).thenReturn(Single.error(Throwable()))

        `when`(viewModel._detailViewState.postValue(ArgumentMatchers.any())).then{
            assert(
                it.arguments[0] is DetailViewStates.ShowLoading ||
                        it.arguments[0] is DetailViewStates.GetMovieError
            )
        }
        viewModel.saveOrRemoveMovie(mockedMovieResponse)
    }

    @Test
    fun`Assert saveOrRemoveMovie save error`(){
        viewModel._detailViewState = mock(MutableLiveData<DetailViewStates>()::class.java)

        `when`(useCase.saveMovie(mockedMovieResponse)).thenReturn(Completable.error(Throwable()))
        `when`(useCase.getMovie(mockedMovieResponse)).thenReturn(Single.error(EmptyResultSetException("")))

        `when`(viewModel._detailViewState.postValue(ArgumentMatchers.any())).then{
            assert(
                it.arguments[0] is DetailViewStates.ShowLoading ||
                        it.arguments[0] is DetailViewStates.SaveError
            )
        }
        viewModel.saveOrRemoveMovie(mockedMovieResponse)
    }

    @Test
    fun`Assert saveOrRemoveMovie remove success`(){
        viewModel._detailViewState = mock(MutableLiveData<DetailViewStates>()::class.java)

        `when`(useCase.saveMovie(mockedMovieResponse)).thenReturn(Completable.complete())
        `when`(useCase.getMovie(mockedMovieResponse)).thenReturn(Single.error(EmptyResultSetException("")))

        `when`(viewModel._detailViewState.postValue(ArgumentMatchers.any())).then{
            assert(
                it.arguments[0] is DetailViewStates.ShowLoading ||
                        it.arguments[0] is DetailViewStates.SaveSuccess
            )
        }
        viewModel.saveOrRemoveMovie(mockedMovieResponse)
    }

    @Test
    fun`Assert saveOrRemoveMovie remove error onGetMovie`(){
        viewModel._detailViewState = mock(MutableLiveData<DetailViewStates>()::class.java)

        `when`(useCase.saveMovie(mockedMovieResponse)).thenReturn(Completable.complete())
        `when`(useCase.getMovie(mockedMovieResponse)).thenReturn(Single.error(Throwable()))

        `when`(viewModel._detailViewState.postValue(ArgumentMatchers.any())).then{
            assert(
                it.arguments[0] is DetailViewStates.ShowLoading ||
                        it.arguments[0] is DetailViewStates.GetMovieError
            )
        }
        viewModel.saveOrRemoveMovie(mockedMovieResponse)
    }

    @Test
    fun`Assert saveOrRemoveMovie remove error`(){
        viewModel._detailViewState = mock(MutableLiveData<DetailViewStates>()::class.java)

        `when`(useCase.saveMovie(mockedMovieResponse)).thenReturn(Completable.error(Throwable()))
        `when`(useCase.getMovie(mockedMovieResponse)).thenReturn(Single.error(EmptyResultSetException("")))

        `when`(viewModel._detailViewState.postValue(ArgumentMatchers.any())).then{
            assert(
                it.arguments[0] is DetailViewStates.ShowLoading ||
                        it.arguments[0] is DetailViewStates.SaveError
            )
        }
        viewModel.saveOrRemoveMovie(mockedMovieResponse)
    }
}