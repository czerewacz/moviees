package ai.akun.moviees.feature.tvshows.di

import ai.akun.moviees.feature.tvshows.data.TvShowsRepository
import ai.akun.moviees.feature.tvshows.domain.ITvShowsRepository
import ai.akun.moviees.feature.tvshows.domain.usecases.GetTopRatedUseCase
import ai.akun.moviees.feature.tvshows.presentation.DetailViewModel
import ai.akun.moviees.feature.tvshows.presentation.TopRatedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val tvShowsModule = module {
    single<ITvShowsRepository> { TvShowsRepository(get()) }
    factory { GetTopRatedUseCase(get()) }
    viewModel { TopRatedViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}