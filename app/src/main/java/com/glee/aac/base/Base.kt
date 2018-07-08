package com.glee.aac.base

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.glee.aac.BR
import java.lang.reflect.ParameterizedType
import java.time.LocalDate

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            Log.e("glee9507",throwable.toString())
        }
    }
}

interface IView<VM : IViewModel> {
    val layoutId: Int
    fun init()
    val viewModel: VM
}

interface IViewModel {

}

abstract class BaseViewModel : ViewModel(), IViewModel {

}




