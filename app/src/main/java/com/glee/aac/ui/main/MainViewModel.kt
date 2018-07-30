package com.glee.aac.ui.main

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Transformations
import com.glee.aac.base.BaseViewModel
import com.glee.aac.data.model.ArticleData
import com.glee.aac.net.RemoteRepo
import com.glee.aac.net.execute
import com.glee.aac.net.http
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
    val pagingData by lazy {


        PagingData<ArticleData>(20) { page, callback ->
            RemoteRepo.getMainList(page = page.toString()).execute({
                callback.invoke(it.datas)
            }, {
                callback.invoke(ArrayList())
                Log.e("glee9507", it.toString())
            })

        }.liveData
    }

    init {
        RemoteRepo.getMainList("1").http {
            retry = true
            success {

            }
            error {

            }
        }
    }
}