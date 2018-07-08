package com.glee.aac.ui.main

import android.util.Log
import androidx.paging.ItemKeyedDataSource
import androidx.paging.PageKeyedDataSource
import com.glee.aac.data.model.ArticleData
import com.glee.aac.net.RemoteRepo
import com.glee.aac.net.check
import com.glee.aac.net.execute

/**
 * Created with Android Studio.
 * OS: Arch Linux
 * Description:
 * User: Liji
 * Date: 2018-07-08
 * Time: 1:17 PM
 */
class MainDataSource : PageKeyedDataSource<Int, ArticleData>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, ArticleData>) {
        RemoteRepo.getMainList(0.toString())
                .execute(success = {
                    callback.onResult(it.datas, null, 2)
                }, failure = {
                    Log.e("glee9507", it.errorMsg)
                })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ArticleData>) {
        RemoteRepo.getMainList(params.key.toString())
                .execute(success = {
                    callback.onResult(it.datas, params.key + 1)
                }, failure = {
                    Log.e("glee9507", it.errorMsg)

                })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ArticleData>) {
    }
}