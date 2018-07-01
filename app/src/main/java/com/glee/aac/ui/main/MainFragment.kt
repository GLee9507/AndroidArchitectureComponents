package com.glee.aac.ui.main

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.glee.aac.data.model.ArticleData
import com.glee.aac.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View =
            with(MainFragmentBinding.inflate(inflater, container, false)) {
                binding = this
                root
            }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = MainListAdapter()
        binding.recycler.adapter = adapter

        val pagedList = LivePagedListBuilder(object : DataSource.Factory<Int, ArticleData>() {
            override fun create(): DataSource<Int, ArticleData> =
                    MainListDataSource()
        }, PagedList.Config.Builder()
                .setInitialLoadSizeHint(20)
                .setPageSize(20)
                .build()).build()
        pagedList.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

}
