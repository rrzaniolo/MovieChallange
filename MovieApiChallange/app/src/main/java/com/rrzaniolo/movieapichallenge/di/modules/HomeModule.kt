package com.rrzaniolo.movieapichallenge.di.modules

import com.rrzaniolo.movieapichallenge.data.repositories.GenreRepositoryImp
import com.rrzaniolo.movieapichallenge.data.repositories.MovieRepositoryImp
import com.rrzaniolo.movieapichallenge.domain.repositories.GenreRepository
import com.rrzaniolo.movieapichallenge.domain.repositories.MovieRepository
import com.rrzaniolo.movieapichallenge.domain.usecase.HomeUseCase
import com.rrzaniolo.movieapichallenge.presentation.main.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

/**
 * Created by Rodrigo Rodrigues Zaniolo on 9/28/2019.
 * All rights reserved.
 */
internal val homeModule = module {
    factory<MovieRepository> { MovieRepositoryImp(get(), get()) }
    factory<GenreRepository> { GenreRepositoryImp(get()) }
    factory { HomeUseCase(get(), get()) }
    viewModel { HomeViewModel(get()) }
}

internal val provideHomeModule = loadKoinModules(homeModule)
fun loadHomeModule() = provideHomeModule