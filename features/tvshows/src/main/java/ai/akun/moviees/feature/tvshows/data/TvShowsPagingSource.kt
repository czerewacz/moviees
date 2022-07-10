package ai.akun.moviees.feature.tvshows.data

import ai.akun.core.network.clients.MovieDbApiClient
import ai.akun.core.network.error.NetworkFailure
import ai.akun.moviees.feature.tvshows.domain.model.TvShow
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.flow.single
import java.io.IOException

class TvShowsPagingSource(
    private val apiClient: MovieDbApiClient
) : PagingSource<Int, TvShow>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvShow> {
        return try {
            val pageNumber = params.key ?: 1

            val response = apiClient.getTopRatedTvShows(
                page = pageNumber
            ).single()

            //Handle initial load
            val prevKey = if (pageNumber > 1) pageNumber - 1 else null
            //Handle finish paging
            val nextKey = if (pageNumber < response.total_pages) pageNumber + 1 else null

            LoadResult.Page(
                data = response.results.map {
                    it.toTvShow()
                },
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: NetworkFailure) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, TvShow>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.nextKey
        }
    }
}