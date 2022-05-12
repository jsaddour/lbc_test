package com.lbc_test.album.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.lbc_test.album.domain.ObserveAlbumsUsecase
import com.lbc_test.album.domain.RefreshAlbumsUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumListViewModel @Inject constructor(
    private val observeAlbumsUsecase: ObserveAlbumsUsecase,
    private val refreshAlbumsUsecase: RefreshAlbumsUsecase
) : ViewModel() {
    private val _viewState = MutableLiveData(ListUIState(loading = true))
    val viewState: LiveData<ListUIState> = _viewState


    init {
        _viewState.postValue(viewState.value?.loadingStart())
        viewModelScope.launch {
            observeAlbumsUsecase.execute().cachedIn(viewModelScope).collectLatest { albums ->
                _viewState.postValue(viewState.value?.setData(albums))
            }
        }
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            val isSuccessful = refreshAlbumsUsecase.execute()
            _viewState.postValue(viewState.value?.loadingStop())
            if (!isSuccessful) {
                _viewState.postValue(viewState.value?.error())
            }
        }
    }
}