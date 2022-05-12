package com.lbc_test.album.presentation.list

import androidx.paging.PagingData
import androidx.paging.map
import com.lbc_test.R
import com.lbc_test.album.domain.Album
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.assertj.core.api.Assertions.assertThat


@ExperimentalCoroutinesApi
class ListUIStateMapperTest {
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun startLoadingTest() {
        val state = ListUIState(false)
        assertEquals(state.loadingStart(), state.copy(loading = true))
    }

    @Test
    fun stopLoadingTest() {
        val state = ListUIState(true)
        assertEquals(state.loadingStop(), state.copy(loading = false))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun setDataTest() {
        runTest {
            val state = ListUIState(false)
            val data = PagingData.from(listOf(Album(1, 2, "title", "url", "thumbnail")))

            assertThat(state.setData(data)).usingRecursiveComparison().isEqualTo(state.copy(
                data = ListUIState.Data(
                    albums = data.map { ListUIState.Data.Album(it.id, it.title, it.thumbnailUrl) })
            ))
        }
    }

    @Test
    fun errorTest() {
        val state = ListUIState(false)
        assertThat(state.error()).usingRecursiveComparison().isEqualTo(
            state.copy(error = ListUIState.Event(R.string.error))
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}