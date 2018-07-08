package com.glee.aac.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.glee.aac.BR
import com.glee.aac.data.model.ArticleData

/**
 * Created with Android Studio.
 * OS: Arch Linux
 * Description:
 * User: Liji
 * Date: 2018-07-08
 * Time: 12:44 PM
 */
class MainAdapter(@LayoutRes private val itemLayoutId: Int) : PagedListAdapter<ArticleData, MainViewHolder>(DIFF) {
    lateinit var inflater: LayoutInflater
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        inflater = LayoutInflater.from(recyclerView.context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MainViewHolder(
            DataBindingUtil.inflate<ViewDataBinding>(inflater, itemLayoutId, parent, false))


    override fun onBindViewHolder(holder: MainViewHolder, position: Int) =
            with(holder.binding) {
                setVariable(BR.itemData, getItem(position))
                executePendingBindings()
            }


    companion object {
        val DIFF = object : DiffUtil.ItemCallback<ArticleData>() {
            override fun areItemsTheSame(oldItem: ArticleData, newItem: ArticleData): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: ArticleData, newItem: ArticleData): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }
}


class MainViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root){
    init {

    }
}


