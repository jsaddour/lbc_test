package com.lbc_test.album.presentation.list

import androidx.paging.PagingData

data class ListUIState(
    val loading: Boolean,
    val data: Data? = null,
    val error: Event<Int>? = null
) {
    data class Data(val albums: PagingData<Album>) {
        data class Album(val id: Int, val title: String, val thumbnailUrl: String)
    }

    class Event<out T>(private val content: T) {
        private var consumed = false


        fun getContent(): T? {
            return if (consumed) {
                null
            } else {
                consumed = true
                content
            }
        }
    }
}