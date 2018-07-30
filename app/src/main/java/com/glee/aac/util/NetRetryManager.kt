package com.glee.aac.util

import android.content.Context
import android.os.Handler
import android.os.HandlerThread
import androidx.asynclayoutinflater.view.AsyncLayoutInflater
import androidx.fragment.app.Fragment
import com.glee.aac.net.RETROFIT
import com.glee.aac.net.RemoteRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetRetryManager {
    lateinit var handlerThread: HandlerThread
    private val retryHandler by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        Handler(HandlerThread("RetryThread").let {
            handlerThread = it
            it.looper
        }) {
            when (it.what) {

            }
            true
        }.apply {
            handlerThread.start()
        }
    }
    fun <T> addRetryRunnable(call: Call<T>) {
        call.enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<T>?, response: Response<T>?) {
            }
        })
    }
}