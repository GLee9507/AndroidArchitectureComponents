package com.glee.aac.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.ItemKeyedDataSource
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.glee.aac.R
import com.glee.aac.data.model.ArticleData
import kotlinx.android.synthetic.main.item_test.view.*

/**
 * Created with Android Studio.
 * OS: Arch Linux
 * Description:
 * User: Liji
 * Date: 2018-06-23
 * Time: 11:22 PM
 */
class MainListAdapter : PagedListAdapter<ArticleData, RecyclerView.ViewHolder>(diffCallback) {
    private lateinit var inflater: LayoutInflater
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        inflater = LayoutInflater.from(recyclerView.context)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MainViewHolder(inflater.inflate(R.layout.item_test,parent,false))




    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<ArticleData>() {
            override fun areItemsTheSame(oldItem: ArticleData, newItem: ArticleData) = oldItem.id == oldItem.id
            override fun areContentsTheSame(oldItem: ArticleData, newItem: ArticleData) = oldItem.title == oldItem.title
        }
    }
}

class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textView = itemView.findViewById<TextView>(com.glee.aac.R.id.tv)
}


class MainListDataSource : ItemKeyedDataSource<Int, ArticleData>() {
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<ArticleData>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<ArticleData>) {
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<ArticleData>) {
    }


    override fun getKey(item: ArticleData): Int {
      return  item.id
    }
}