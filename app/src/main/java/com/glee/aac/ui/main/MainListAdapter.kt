package com.glee.aac.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.glee.aac.R
import com.glee.aac.data.model.ArticleData

/**
 * Created with Android Studio.
 * OS: Arch Linux
 * Description:
 * User: Liji
 * Date: 2018-06-23
 * Time: 11:22 PM
 */
class MainListAdapter : PagedListAdapter<ArticleData, MainViewHolder>(diffCallback) {
    private lateinit var inflater: LayoutInflater
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        inflater = LayoutInflater.from(recyclerView.context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MainViewHolder(inflater.inflate(R.layout.item_test, parent, false))


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        if (getItem(position) != null) {
            holder.textView.text = getItem(position)!!.title + "---"+ getItem(position)!!.id
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<ArticleData>() {
            override fun areItemsTheSame(oldItem: ArticleData, newItem: ArticleData) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: ArticleData, newItem: ArticleData) = oldItem == newItem
        }
    }
}

class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textView = itemView.findViewById<TextView>(com.glee.aac.R.id.tv)
}


