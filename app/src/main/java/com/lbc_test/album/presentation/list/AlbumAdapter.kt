package com.lbc_test.album.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.lbc_test.R

class AlbumAdapter : PagingDataAdapter<ListUIState.Data.Album, AlbumAdapter.AlbumViewHolder>(USER_COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.row_album,
            parent,
            false
        )
        return AlbumViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val thumbnail: ImageView = itemView.findViewById(R.id.thumbnail)
        val title: TextView = itemView.findViewById(R.id.title)

        fun bind(item: ListUIState.Data.Album?) {
            item?.let {
                val glideUrl = GlideUrl(
                    item.thumbnailUrl, LazyHeaders.Builder()
                        .addHeader("User-Agent", "any-user-agent")
                        .build()
                )
                Glide.with(itemView.context).load(glideUrl).into(thumbnail)
                title.text = item.title
            }

        }
    }


    companion object {
        val USER_COMPARATOR = object : DiffUtil.ItemCallback<ListUIState.Data.Album>() {
            override fun areItemsTheSame(oldItem: ListUIState.Data.Album, newItem: ListUIState.Data.Album): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ListUIState.Data.Album, newItem: ListUIState.Data.Album): Boolean =
                oldItem == newItem
        }
    }
}