package ai.akun.moviees.feature.tvshows.data

import ai.akun.core.network.request.Results
import ai.akun.core.network.request.TopRatedData
import ai.akun.moviees.feature.tvshows.domain.model.TopRatedTvShows
import ai.akun.moviees.feature.tvshows.domain.model.TvShow

fun TopRatedData.toTopRatedTvShows(): TopRatedTvShows = TopRatedTvShows(
    page,
    results.map {
        it.toTvShow()
    },
    total_pages,
    total_results
)

fun Results.toTvShow(): TvShow = TvShow(
    backdrop_path,
    first_air_date,
    genre_ids,
    id,
    name,
    origin_country,
    original_language,
    original_name,
    overview,
    popularity,
    poster_path,
    vote_average,
    vote_count
)
