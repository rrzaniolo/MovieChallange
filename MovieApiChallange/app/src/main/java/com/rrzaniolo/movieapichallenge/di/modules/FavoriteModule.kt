package com.rrzaniolo.movieapichallenge.di.modules

import com.rrzaniolo.movieapichallenge.domain.usecase.FavoriteUseCase
import com.rrzaniolo.movieapichallenge.presentation.main.favorite.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

/**
 * Created by Rodrigo Rodrigues Zaniolo on 9/28/2019.
 * All rights reserved.
 */
internal val favoriteModule = module {
    factory { FavoriteUseCase(get()) }
    viewModel { FavoriteViewModel(get()) }
}

internal val provideFavoriteModule = loadKoinModules(favoriteModule)
fun loadFavoriteModule() = provideFavoriteModule