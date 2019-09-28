package com.rrzaniolo.movieapichallenge.presentation.base

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Rodrigo Rodrigues Zaniolo on 9/28/2019.
 * All rights reserved.
 */
abstract class BaseViewModel: ViewModel() {

    @VisibleForTesting val disposableTask = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposableTask.clear()
    }
}