package com.glee.aac.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.glee.aac.BR

interface IView<VM : IViewModel> {
    val layoutId: Int
    fun init()
    val viewModel: VM
}

interface IViewModel {

}

abstract class BaseViewModel : ViewModel(), IViewModel {

}

abstract class BaseFragment<B : ViewDataBinding, VM : BaseViewModel>() : Fragment(), com.glee.aac.base.IView<VM> {
    lateinit var binding: B


    constructor(viewModelClazz: Class<VM>) : this() {
        val bundle = arguments
        if (bundle != null) {
            bundle.putSerializable(VIEW_MODEL_KEY, viewModelClazz)
        } else {
            arguments = Bundle().apply {
                putSerializable(VIEW_MODEL_KEY, viewModelClazz)
            }
        }
    }

    override fun setArguments(args: Bundle?) {
        val existArguments = arguments
        if (existArguments != null) {
            existArguments.putAll(args)
        } else {
            super.setArguments(args)
        }
    }

    @Suppress("UNCHECKED_CAST")
    override val viewModel: VM by lazy(LazyThreadSafetyMode.NONE) {
        val vmClass: Class<VM> = arguments!![VIEW_MODEL_KEY] as Class<VM>
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    companion object {
        private const val VIEW_MODEL_KEY = "viewModelClazz"
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
