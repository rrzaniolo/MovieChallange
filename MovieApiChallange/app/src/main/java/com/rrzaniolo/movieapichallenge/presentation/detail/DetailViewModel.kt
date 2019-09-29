package com.rrzaniolo.movieapichallenge.presentation.detail

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.EmptyResultSetException
import com.rrzaniolo.movieapichallenge.data.models.MovieResponse
import com.rrzaniolo.movieapichallenge.domain.usecase.DetailUseCase
import com.rrzaniolo.movieapichallenge.presentation.base.BaseViewModel

/**
 * Created by Rodrigo Rodrigues Zaniolo on 9/28/2019.
 * All rights reserved.
 */
class DetailViewModel(private val useCase: DetailUseCase): BaseViewModel() {

    @Suppress("PropertyName")
    @VisibleForTesting
    private val _detailViewState by lazy { MutableLiveData<DetailViewStates>() }
    val detailViewState: LiveData<DetailViewStates>
        get() = _detailViewState


    fun saveOrRemoveMovie(movie: MovieResponse) {
        _detailViewState.postValue(DetailViewStates.ShowLoading)
        disposableTask.add(useCase.getMovie(movie).subscribe({
            deleteMovie(it)
        }, {
            if (it is EmptyResultSetException) {
                saveMovie(movie)
            } else {
                _detailViewState.postValue(DetailViewStates.GetMovieError)
            }
        }))
    }

    fun getMovieData(movie: MovieResponse?){
        if(movie == null)  _detailViewState.postValue(DetailViewStates.GetMovieError)
        else{
            _detailViewState.postValue(DetailViewStates.ShowLoading)
            disposableTask.add(useCase.getMovie(movie).subscribe(
                {
//                    _detailViewState.postValue(DetailViewStates.HideLoading)
                    _detailViewState.postValue(DetailViewStates.GetMovieSuccess(it))
                },
                {
//                    _detailViewState.postValue(DetailViewStates.HideLoading)
                    _detailViewState.postValue(DetailViewStates.GetMovieSuccess(movie))
                }
            ))
        }
    }

    private fun saveMovie(movie: MovieResponse) {
        disposableTask.add(useCase.saveMovie(movie).subscribe({
//            _detailViewState.postValue(DetailViewStates.HideLoading)
            _detailViewState.postValue(DetailViewStates.SaveSuccess)
        }, {
//            _detailViewState.postValue(DetailViewStates.HideLoading)
            _detailViewState.postValue(DetailViewStates.SaveError)
        }))
    }

    private fun deleteMovie(movie: MovieResponse) {
        disposableTask.addAll(useCase.removeMovie(movie).subscribe({
//            _detailViewState.postValue(DetailViewStates.HideLoading)
            _detailViewState.postValue(DetailViewStates.RemoveSuccess)
        }, {
//            _detailViewState.postValue(DetailViewStates.HideLoading)
            _detailViewState.postValue(DetailViewStates.RemoveError)
        }))
    }
}