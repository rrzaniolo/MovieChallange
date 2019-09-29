package com.rrzaniolo.movieapichallenge.di.modules

import com.rrzaniolo.movieapichallenge.domain.usecase.DetailUseCase
import com.rrzaniolo.movieapichallenge.presentation.detail.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

/**
 * Created by Rodrigo Rodrigues Zaniolo on 9/28/2019.
 * All rights reserved.
 */
internal val detailModule = module {
    factory { DetailUseCase(get()) }
    viewModel { DetailViewModel(get()) }
}

private val provideDetailModule = loadKoinModules(detailModule)
fun loadDetailModule() = provideDetailModule