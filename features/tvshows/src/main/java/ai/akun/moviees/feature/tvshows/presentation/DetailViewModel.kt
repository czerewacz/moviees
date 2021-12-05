package ai.akun.moviees.feature.tvshows.presentation

import ai.akun.core.usecase.UseCaseResult
import ai.akun.moviees.feature.tvshows.domain.model.TopRatedTvShows
import ai.akun.moviees.feature.tvshows.domain.model.TvShow
import ai.akun.moviees.feature.tvshows.domain.usecases.GetSimilarTVUseCase
import androidx.lifecycle.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailViewModel(
    private val state: SavedStateHandle,
    private val getSimilarTVUseCase: GetSimilarTVUseCase
) : ViewModel() {

    val data: LiveData<TvShow> = state.getLiveData<TvShow>("tvShow")

    private val _uiState = MutableLiveData<UIState>()
    val uiState: LiveData<UIState> = _uiState

    private val _tvShow = MutableLiveData<TvShow>()
    val tvShow: LiveData<TvShow> = _tvShow

    private val _topRated = MutableLiveData<TopRatedTvShows>()
    val topRated: LiveData<TopRatedTvShows> = _topRated

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getSimilarTvShows(genreId: Int) {
        viewModelScope.launch {
            _uiState.postValue(UIState.Loading)
            getSimilarTVUseCase(genreId = genreId).collect { result ->
                if (result is UseCaseResult.Success) {
                    _uiState.postValue(UIState.Success)
                    _topRated.postValue(updateTvList(result.data))
                } else if (result is UseCaseResult.Error) {
                    _uiState.postValue(UIState.Error)
                    _error.postValue(result.exception.message)
                }
            }
        }
    }

    fun setTvShow(tvShow: TvShow){
        _tvShow.postValue(tvShow)
    }



    private fun updateTvList(topRated: TopRatedTvShows): TopRatedTvShows {
        val list: MutableList<TvShow> = topRated.results.toMutableList()
        list.add(0, tvShow.value!!)
        return when (topRated.page) {
            1 -> topRated.copy(results = list.toList())
            else -> topRated
        }
    }

    sealed class UIState {
        object Loading : UIState()
        object Error : UIState()
        object Success : UIState()
    }
}
