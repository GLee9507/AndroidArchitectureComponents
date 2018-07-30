package com.glee.aac.net

import retrofit2.Call
import retrofit2.Callback

class CallWrapper<T>(val call: Call<T>) {
    var retry = false
    var cache = false
    private var success: (retrofit2.Response<T>?) -> Unit = {}
    private var error: (Throwable?) -> Unit = {}

    fun success(function: (retrofit2.Response<T>?) -> Unit) {
        success = function
    }

    fun error(function: (Throwable?) -> Unit) {
        error = function
    }

}

//fun <T> retrofit2.Call<T>.http(block: CallWrapper<T>.() -> Unit) {
//    val callWrapper = CallWrapper(this)
//    callWrapper.block()
//    callWrapper.call.enqueue(object : Callback<T> {
//        override fun onFailure(call: Call<T>?, t: Throwable?) {
//            callWrapper.error.invoke(t)
//        }
//
//        override fun onResponse(call: Call<T>?, response: retrofit2.Response<T>?) {
//            callWrapper.success.invoke(response)
//        }
//    })
//}



