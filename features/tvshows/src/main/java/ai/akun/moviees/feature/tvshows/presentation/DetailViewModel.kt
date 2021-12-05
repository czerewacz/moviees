package ai.akun.moviees.feature.tvshows.presentation

import ai.akun.moviees.feature.tvshows.domain.model.TvShow
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class DetailViewModel(
    private val state: SavedStateHandle
) : ViewModel() {

    val data : LiveData<TvShow> = state.getLiveData<TvShow>("tvShow")

    private val _tvShow = MutableLiveData<TvShow>()
    val tvShow: LiveData<TvShow> = _tvShow

    init {
        state.get<TvShow>("tvShow")?.let { setTvShow(it) }
    }


    private fun setTvShow(tvShow: TvShow) {
        _tvShow.postValue(tvShow)
    }

    fun getSimilarTvShows(){

    }

}