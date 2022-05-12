package com.lbc_test.album.presentation.list

import androidx.paging.PagingData
import androidx.paging.map
import com.lbc_test.R
import com.lbc_test.album.domain.Album


fun ListUIState.loadingStart(): ListUIState {
    return this.copy(loading = true)
}

fun ListUIState.loadingStop(): ListUIState {
    return this.copy(loading = false)
}

fun ListUIState.setData(pagedAlbums: PagingData<Album>): ListUIState {
    return this.copy(
        data = ListUIState.Data(albums = pagedAlbums.map { ListUIState.Data.Album(it.id, it.title, it.thumbnailUrl) })
    )
}

fun ListUIState.error(): ListUIState {
    return this.copy(loading = false, error = ListUIState.Event(R.string.error))
}