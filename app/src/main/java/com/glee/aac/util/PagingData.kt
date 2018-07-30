package com.glee.aac.util

import android.util.Log
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.glee.aac.data.model.DiffSupport


/**
 * Created with Android Studio.
 * OS: Arch Linux
 * Description:
 * User: Liji
 * Date: 2018-07-09
 * Time: 9:52 PM
 */
class PagingData<D : DiffSupport>(pageSize: Int, load: (page: Int, callback: (result: List<D>) -> Unit) -> Unit) {
    val liveData = LivePagedListBuilder<Int, D>(object : DataSource.Factory<Int, D>() {
        override fun create(): DataSource<Int, D> {
            return object : PageKeyedDataSource<Int, D>() {
                override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, D>) {
                    
                }

                override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, D>) {
                    load.invoke(0) {
                        callback.onResult(it, null, 1)
                    }

                }

                override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, D>) {
                    load.invoke(params.key) {
                        callback.onResult(it, params.key + 1)
                    }
                }
            }
        }
    }, pageSize).apply {
        setBoundaryCallback(object : PagedList.BoundaryCallback<D>() {
            override fun onZeroItemsLoaded() {
                super.onZeroItemsLoaded()
                Log.d("glee9507", "onZeroItemsLoaded")
            }

            override fun onItemAtEndLoaded(itemAtEnd: D) {
                super.onItemAtEndLoaded(itemAtEnd)
                Log.d("glee9507", "onItemAtEndLoaded" + itemAtEnd.toString())
            }

            override fun onItemAtFrontLoaded(itemAtFront: D) {
                super.onItemAtFrontLoaded(itemAtFront)
                Log.d("glee9507", "onItemAtFrontLoaded" + itemAtFront.toString())
            }
        })
    }.build()


//    val liveData by lazy(LazyThreadSafetyMode.NONE) {
//        LivePagedListBuilder<Int, D>(object : DataSource.Factory<Int, D>() {
//            override fun create(): DataSource<Int, D> {
//                return object : PageKeyedDataSource<Int, D>() {
//                    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, D>) {
//
//                    }
//
//                    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, D>) {
//                        load.invoke(0) {
//                            callback.onResult(it, null, 2)
//                        }
//                    }
//
//                    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, D>) {
//                        load.invoke(params.key) {
//                            callback.onResult(it, params.key + 1)
//                        }
//                    }
//                }
//            }
//        }, pageSize).build()
}


