package com.glee.aac.net


import com.glee.aac.data.model.MainArticleBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
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
    @GET("http://www.wanandroid.com/article/list/{page}/json")
    fun getMainList(@Path("page") page: String): Call<Response<MainArticleBean>>
}

data class Response<T>(
                       val errorMsg: String = "",
                       val errorCode: Int,
                       val data: T? = null,
                       val isSuccessful: Boolean=false)