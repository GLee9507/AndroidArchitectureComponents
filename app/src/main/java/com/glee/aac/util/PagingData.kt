package com.glee.aac.util

import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import com.glee.aac.data.model.DiffSupport


/**
 * Created with Android Studio.
 * OS: Arch Linux
 * Description:
 * User: Liji
 * Date: 2018-07-09
 * Time: 9:52 PM
 */
class PagingData<D : DiffSupport>(pageSize: Int, load: (page: Int, callback: (result: MutableList<D>) -> Unit) -> Unit) {
    val liveData by lazy(LazyThreadSafetyMode.NONE) {
        LivePagedListBuilder<Int, D>(object : DataSource.Factory<Int, D>() {
            override fun create(): DataSource<Int, D> {
                return object : PageKeyedDataSource<Int, D>() {
                    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, D>) {
                        load.invoke(params.key) {
                            callback.onResult(it, params.key + 1)
                        }
//                        callback.onResult(load.invoke(params.key, this@), params.key + 1)
                    }

                    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, D>) {
//                        callback.onResult(load.invoke(1), null, 2)
                        load.invoke(0) {
                            callback.onResult(it, null, 2)
                        }
                    }

                    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, D>) {
                    }
                }
            }
        }, pageSize).build()
    }
}

