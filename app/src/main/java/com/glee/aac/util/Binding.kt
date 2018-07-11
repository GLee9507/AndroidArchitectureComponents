package com.glee.aac.util

import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.glee.aac.data.model.DiffSupport
import com.glee.aac.ui.main.SimplePagingAdapter

/**
 * Created with Android Studio.
 * OS: Arch Linux
 * Description:
 * User: Liji
 * Date: 2018-07-09
 * Time: 10:09 PM
 */
@Suppress("UNCHECKED_CAST")
object Binding {

    @JvmStatic
    @BindingAdapter("itemLayoutId", "dataSource", requireAll = true)
    fun recyclerAdapter(view: RecyclerView, @LayoutRes itemLayoutId: Int = 0, dataSource: PagedList<out DiffSupport>?) {
        val adapter = view.adapter
        if (adapter == null || itemLayoutId == 0) {
            if (itemLayoutId == 0) {
                throw IllegalArgumentException()
            }
            view.adapter = SimplePagingAdapter(itemLayoutId)
        }

        if (dataSource != null && adapter is SimplePagingAdapter) {
            val pagedList = dataSource as PagedList<DiffSupport>
            adapter.submitList(pagedList)
        }

    }
}