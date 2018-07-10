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
import com.glee.aac.data.model.DiffSupport

/**
 * Created with Android Studio.
 * OS: Arch Linux
 * Description:
 * User: Liji
 * Date: 2018-07-08
 * Time: 12:44 PM
 */
class SimplePagingAdapter(@LayoutRes private val itemLayoutId: Int) : PagedListAdapter<DiffSupport, SimpleViewHolder>(
        DIFF
//        object : DiffUtil.ItemCallback<D>() {
//            override fun areItemsTheSame(oldItem: D, newItem: D): Boolean {
//                return oldItem === newItem
//            }
//
//            override fun areContentsTheSame(oldItem: D, newItem: D): Boolean {
//                return oldItem.diffSupportKey == newItem.diffSupportKey
//            }
//
//        }


) {
    lateinit var inflater: LayoutInflater
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        inflater = LayoutInflater.from(recyclerView.context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SimpleViewHolder(
            DataBindingUtil.inflate<ViewDataBinding>(inflater, itemLayoutId, parent, false))


    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) =
            with(holder.binding) {
                setVariable(BR.itemData, getItem(position))
                executePendingBindings()
            }


    companion object {
        val DIFF = object : DiffUtil.ItemCallback<DiffSupport>() {
            override fun areItemsTheSame(oldItem: DiffSupport, newItem: DiffSupport): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: DiffSupport, newItem: DiffSupport): Boolean {
                return oldItem.diffSupportKey == newItem.diffSupportKey
            }

        }
    }
}


class SimpleViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root){
    init {

    }
}


