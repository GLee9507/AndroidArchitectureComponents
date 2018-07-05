package com.glee.aac.base

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.glee.aac.BR
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

interface IView<VM : IViewModel> {
    val layoutId: Int
    fun init()
    val viewModel: VM
}

interface IViewModel {

}

abstract class BaseViewModel : ViewModel(), IViewModel {

}

abstract class BaseFragment<B : ViewDataBinding, VM : BaseViewModel> : Fragment(), com.glee.aac.base.IView<VM> {
    lateinit var binding: B

    @Suppress("UNCHECKED_CAST")
    override val viewModel: VM by lazy(LazyThreadSafetyMode.NONE) {
        val vmClass: Class<VM> = arguments!!["vmClass"] as Class<VM>
        ViewModelProviders.of(this)[vmClass]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            DataBindingUtil.inflate<B>(inflater, layoutId, container, false).let {
                it.setLifecycleOwner(this)
                it.setVariable(BR.viewModel, viewModel)
                binding = it
                binding.root

            }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }


}

//fun <T : BaseFragment<*, *>> newFragment(fragmentClazz: Class<out T>, vmClazz: Class<out BaseViewModel>): T {
//    Proxy.newProxyInstance(fragmentClazz.classLoader, fragmentClazz.interfaces) { p0, p1, p2 ->
//        p1.invoke(p0, vmClazz)
//    }
//    return T().apply {
//        arguments = Bundle().apply { putSerializable("vmClass", vmClazz) }
//    }
//}
