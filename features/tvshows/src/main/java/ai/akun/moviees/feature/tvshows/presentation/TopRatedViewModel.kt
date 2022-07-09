package ai.akun.moviees.feature.tvshows.presentation

import ai.akun.moviees.feature.tvshows.domain.model.TopRatedTvShows
import ai.akun.moviees.feature.tvshows.domain.model.TvShow
import ai.akun.moviees.feature.tvshows.domain.usecases.GetTopRatedUseCase
import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TopRatedViewModel(
    private val getTopRatedUseCase: GetTopRatedUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UIState>()
    val uiState: LiveData<UIState> = _uiState

    private val _topRated = MutableLiveData<PagingData<TvShow>>()
    val topRated: LiveData<PagingData<TvShow>> = _topRated

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getTopRatedTvShows() {
        viewModelScope.launch {
            try {
                getTopRatedUseCase().cachedIn(viewModelScope).collectLatest {
                    _topRated.postValue(it)
                }
            } catch (e: Exception) {
                e.localizedMessage?.let { Log.d("TopRatedViewModel", it) }
            }
        }
    }

    sealed class UIState {
        object Loading : UIState()
        object Error : UIState()
        object Success : UIState()
    }
}