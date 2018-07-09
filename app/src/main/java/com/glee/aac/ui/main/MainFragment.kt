package com.glee.aac.ui.main

import com.glee.aac.R
import com.glee.aac.base.BaseFragment
import com.glee.aac.databinding.MainFragmentBinding
import androidx.lifecycle.Observer
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.glee.aac.data.model.ArticleData


/**
 * Created with Android Studio.
 * OS: Arch Linux
 * Description:
 * User: Liji
 * Date: 2018-07-08
 * Time: 12:38 PM
 */

class MainFragment : BaseFragment<MainViewModel, MainFragmentBinding>() {
    override val layoutId = R.layout.main_fragment
//    override fun init() {
//        val adapter = SimplePagingAdapter(R.layout.item_main)
//        binding.recycler.adapter = adapter
//
//
//        val config = PagedList.Config.Builder()
//                .setInitialLoadSizeHint(20)//首次加载10
//                .setPageSize(20)
//                .build()
//
//        val data = LivePagedListBuilder<Int, ArticleData>(object : DataSource.Factory<Int, ArticleData>() {
//            override fun create(): DataSource<Int, ArticleData> {
//                return MainDataSource()
//            }
//        }, config).setInitialLoadKey(0)
//                .build()
//        data.observe(this, Observer {
//            adapter.submitList(it)
//        })
//
//    }
}