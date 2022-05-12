package com.lbc_test.album.presentation.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.lbc_test.databinding.ActivityAlbumsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumListActivity : AppCompatActivity() {

    private val viewModel by viewModels<AlbumListViewModel>()
    private val binding by lazy {
        ActivityAlbumsBinding.inflate(layoutInflater)
    }
    private val albumAdapter = AlbumAdapter()
    private val pullToRefresh by lazy { binding.swipeRefreshLayout }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupView()
        observe()
    }


    fun observe() {
        viewModel.viewState.observe(this) { listUIState ->
            pullToRefresh.isRefreshing = false
            listUIState?.let {
                it.data?.let { data ->
                    albumAdapter.submitData(lifecycle, data.albums)
                }

                it.error?.let { event ->
                    event.getContent()?.let { stringResId ->
                        val snackbar = Snackbar.make(binding.root, stringResId, Snackbar.LENGTH_SHORT)
                        snackbar.show()
                    }
                }

                if (it.loading) {
                    binding.loadingLayout.visibility = View.VISIBLE
                } else {
                    binding.loadingLayout.visibility = View.GONE

                }
            }
        }
    }

    fun setupView() {
        binding.loadingLayout.visibility = View.VISIBLE
        pullToRefresh.setOnRefreshListener { viewModel.refresh() }
        binding.albumList.apply {
            adapter = albumAdapter
            layoutManager = LinearLayoutManager(this@AlbumListActivity)
        }
    }
}

