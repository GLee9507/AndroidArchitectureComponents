package com.glee.aac.util

import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.glee.aac.data.model.DiffSupport
import com.glee.aac.ui.main.MainDataSource
import com.glee.aac.ui.main.SimplePagingAdapter
import com.glee.aac.ui.main.SimpleViewHolder

/**
 * Created with Android Studio.
 * OS: Arch Linux
 * Description:
 * User: Liji
 * Date: 2018-07-09
 * Time: 10:09 PM
 */
object Binding {
    @JvmStatic
    @BindingAdapter("itemLayoutId")
    fun recyclerAdapter(view: RecyclerView, @LayoutRes itemLayoutId: Int = 0) {
        val adapter = view.adapter
        if (adapter == null) {
            if (itemLayoutId == 0) {
                throw IllegalArgumentException()
            }
            view.adapter = SimplePagingAdapter(itemLayoutId)
        }

    }
    @JvmStatic
    @BindingAdapter("dataSource")
    fun update(view: RecyclerView, dataSource:LiveData<*>) {
        val adapter = view.adapter
//        if (adapter != null && adapter is SimplePagingAdapter) {
//            adapter.submitList(dataSource)
//        }
    }
}