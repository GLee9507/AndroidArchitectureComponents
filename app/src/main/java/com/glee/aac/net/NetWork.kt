package com.glee.aac.net

import androidx.annotation.MainThread
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * Created with Android Studio.
 * OS: Arch Linux
 * Description:
 * User: Liji
 * Date: 2018-06-02
 * Time: 10:51 AM
 */


val RemoteRepo: IApi by lazy(LazyThreadSafetyMode.PUBLICATION) {
    Retrofit.Builder()
            .baseUrl("http://www.wanandroid.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(OkHttpClient.Builder()
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .build())
            .build()
            .create(IApi::class.java)
}


data class HttpError(val errorCode: Int, val errorMsg: String = "")

fun <T> retrofit2.Call<Response<T>>.execute(success: (T) -> Unit, failure: (HttpError) -> Unit) {
    try {
        execute().check(success, failure)

    } catch (e: IOException) {
        failure.invoke(HttpError(-996, e.toString()))
    }
}


fun <T> retrofit2.Response<Response<T>>.check(success: (T) -> Unit, failure: (HttpError) -> Unit) {
    if (isSuccessful) {
        val body = body()
        if (body != null) {
            if (body.errorCode >= 0) {
                success.invoke(body.data)
            } else {
                failure.invoke(HttpError(body.errorCode, body.errorMsg))
            }
        } else {
            failure.invoke(HttpError(-100, "body is null"))
        }
    } else {
        failure.invoke(HttpError(-999, "Http error ${code()}"))
    }
}



