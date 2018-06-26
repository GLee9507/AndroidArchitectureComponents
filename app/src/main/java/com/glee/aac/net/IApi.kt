package com.glee.aac.net


import com.glee.aac.data.model.MainArticleBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Created with Android Studio.
 * OS: Arch Linux
 * Description:
 * User: Liji
 * Date: 2018-05-20
 * Time: 9:38 PM
 */
interface IApi {
    @GET
    fun getMainList(@Url url: String): Observable<Response<MainArticleBean>>
}

data class Response<T>(val data: T,
                       val errorMsg: String = "",
                       val errorCode: Int)