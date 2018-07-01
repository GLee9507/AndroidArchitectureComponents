package com.glee.aac.ui.main

import androidx.paging.PageKeyedDataSource
import com.glee.aac.data.model.ArticleData
import com.glee.aac.net.NET
import com.glee.aac.net.check

class MainListDataSource : PageKeyedDataSource<Int, ArticleData>() {

    private fun pageUrl(page: Int) = "http://www.wanandroid.com/article/list/$page/json"

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, ArticleData>) {
        NET.getMainList("1")
                .execute()
                .check(success = {
                    callback.onResult(it.datas, it.curPage, it.curPage)
                }, failure = {

                })

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ArticleData>) {
        NET.getMainList(params.key.toString())
                .execute()
                .check(success = {
                    callback.onResult(it.datas, params.key + 1)
                }, failure = {

                })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ArticleData>) {
    }
    
}