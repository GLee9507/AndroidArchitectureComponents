package com.glee.aac.net

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
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

val RETROFIT: Retrofit by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
    Retrofit.Builder()
            .baseUrl("http://www.wanandroid.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .callbackExecutor {
                it.run()
            }
            .client(OkHttpClient.Builder()
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .build())
            .build()
}
val RemoteRepo: IApi by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
    RETROFIT.create(IApi::class.java)
}


data class HttpError(val errorCode: Int, val errorMsg: String = "") {
    override fun toString(): String {
        return "HttpError(errorCode=$errorCode, errorMsg='$errorMsg')"
    }
}

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



//fun <T> retrofit2.Call<Response<T>>.kExecute(): Response<T> {
//    try {
//        val response = execute()
//        val body = response.body()
//        return when {
//            response == null -> body
//            !response.isSuccessful -> Response(response.message(), response.code())
//            body == null -> Response("body is null", -98)
//            body.data == null -> Response("data is null", -97, , true)
//            else -> body
//        }
//
//
//        if (!response.isSuccessful) {
//            return Response(response.message(), response.code())
//        } else {
//            val body = response.body()
//            if (body == null) {
//                return Response("body is null", -99)
//            } else {
//                val data = body.data
//                if (data == null) {
//                    return Response("data is null", -98, null, true)
//                } else {
//                    return body
//                }
//            }
//        }
//    } catch (e: IOException) {
//        return Response(e.toString(), -100)
//    }
//}



