package ai.akun.moviees.feature.tvshows.presentation

import ai.akun.core.usecase.UseCaseResult
import ai.akun.moviees.feature.tvshows.data.TvShowsRepository
import ai.akun.moviees.feature.tvshows.domain.model.TopRatedTvShows
import ai.akun.moviees.feature.tvshows.domain.model.TvShow
import ai.akun.moviees.feature.tvshows.domain.usecases.GetTopRatedUseCase
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TopRatedViewModel(
    private val getTopRatedUseCase: GetTopRatedUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UIState>()
    val uiState: LiveData<UIState> = _uiState

    private val _topRated = MutableLiveData<TopRatedTvShows>()
    val topRated: LiveData<TopRatedTvShows> = _topRated

    private val _page = MutableLiveData<Int>(1)
    val page: LiveData<Int> = _page

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getTopRatedTvShows() {
        viewModelScope.launch {
            when (page.value) {
                1 -> _uiState.postValue(UIState.FirstLoading)
                else -> _uiState.postValue(UIState.MoreLoading)
            }
            getTopRatedUseCase(page.value!!)
                .collect { result ->
                    if (result is UseCaseResult.Success) {
                        _uiState.postValue(UIState.Success)
                        _topRated.postValue(result.data)
                        _page.postValue(result.data.page + 1)
                    } else if (result is UseCaseResult.Error) {
                        _uiState.postValue(UIState.Error)
                        _error.postValue(result.exception.message)
                    }
                }
        }
    }

    fun clearData(){
        _page.postValue(1)
        _topRated.postValue(TopRatedTvShows())
    }

    sealed class UIState {
        object FirstLoading : UIState()
        object MoreLoading : UIState()
        object Error : UIState()
        object Success : UIState()
    }
}