package com.glee.aac.ui.main

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.glee.aac.base.BaseViewModel
import com.glee.aac.data.model.ArticleData
import com.glee.aac.net.RemoteRepo
import com.glee.aac.net.execute
import com.glee.aac.util.PagingData

/**
 * Created with Android Studio.
 * OS: Arch Linux
 * Description:
 * User: Liji
 * Date: 2018-07-08
 * Time: 12:39 PM
 */
class MainViewModel : BaseViewModel() {
    public val pagingData: LiveData<PagedList<ArticleData>>by lazy(LazyThreadSafetyMode.NONE) {
        PagingData<ArticleData>(20) { page, callback ->
            RemoteRepo.getMainList(page.toString())
                    .execute(success = {
                        callback.invoke(it.datas.toMutableList())
                    }, failure = {

                    })
        }.liveData

    }
}