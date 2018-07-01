package com.glee.aac.ui.main

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.glee.aac.data.model.ArticleData
import com.glee.aac.net.RemoteRepo
import com.glee.aac.net.check

class MainListDataSource : PageKeyedDataSource<Int, ArticleData>() {


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, ArticleData>) {
        val execute = RemoteRepo.getMainList("1").execute()
        execute.check(success = {
            callback.onResult(it.datas, it.curPage, it.curPage)
        }, failure = {

        })
//                .check(success = {
//                    callback.onResult(it.datas, it.curPage, it.curPage)
//                }, failure = {
//
//                })

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ArticleData>) {
        Log.d("glee9507", Thread.currentThread().name)
        RemoteRepo.getMainList(params.key.toString())
                .execute()
                .check(success = {
                    callback.onResult(it.datas, params.key + 1)
                }, failure = {
                    Log.d("glee9507", it.errorMsg + "--" + it.errorCode)

                })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ArticleData>) {
    }

}