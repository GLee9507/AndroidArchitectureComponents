package com.glee.aac.net

import retrofit2.Call
import retrofit2.Callback


data class CallDSL<T>(val call: Call<Response<T>>) {
    internal var success: ((T) -> Unit)? = null
    internal var error: ((Throwable) -> Unit)? = null
    internal var cache: Cache? = null
    fun success(block: (T) -> Unit) {
        success = block
    }

    fun error(block: (Throwable) -> Unit) {
        error = block
    }

    inline fun cache(block: Cache.() -> Unit) = Cache().apply(block)

}

class Cache {
    var cachePath: String? = null

    constructor(cachePath: String) {
        this.cachePath = cachePath
    }

    constructor()

    fun <T> bindCall(callDSL: CallDSL<T>) {
        callDSL.cache = this
    }
}

fun <T> Call<Response<T>>.http(block: CallDSL<T>.() -> Unit) {
    val callDSL = CallDSL(this)
    block.invoke(callDSL)
    enqueue(object : Callback<Response<T>> {
        override fun onFailure(call: Call<Response<T>>?, t: Throwable?) {

            if (t != null) {
                callDSL.error?.invoke(t)
            } else {

                callDSL.error?.invoke(Throwable("null"))
            }
        }

        override fun onResponse(call: Call<Response<T>>?, response: retrofit2.Response<Response<T>>?) {
            if (response != null) {
                val body = response.body()
                if (body != null) {
                    val data = body.data
                    if (data != null) {
                        callDSL.success?.invoke(data)
                        return
                    }
                }
            }
            callDSL.error?.invoke(Throwable("null"))
        }
    })
}



