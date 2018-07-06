package com.glee.aac.base

import android.os.Bundle
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


interface IView<VM : IViewModel> {
    val layoutId: Int
    fun init()
    val viewModel: VM
}

interface IViewModel {

}

abstract class BaseViewModel : ViewModel(), IViewModel {

}

@Suppress("UNCHECKED_CAST")
abstract class BaseFragment<VM : BaseViewModel, B : ViewDataBinding> : Fragment(), com.glee.aac.base.IView<VM> {
    lateinit var binding: B

    override val viewModel: VM by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this)[getViewModelClazz()]
    }

    private var viewModelClazz: Class<VM>? = null

    private fun getViewModelClazz(): Class<VM> {
        if (viewModelClazz == null) {
            viewModelClazz = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>
        }
        return viewModelClazz as Class<VM>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelClazz = savedInstanceState?.get(VIEW_MODEL_KEY)
        if (viewModelClazz is Class<*>) {
            this.viewModelClazz = viewModelClazz as Class<VM>
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            DataBindingUtil.inflate<B>(inflater, layoutId, container, false).let {
                it.setLifecycleOwner(this)
                it.setVariable(BR.viewModel, viewModel)
                binding = it
                binding.root
            }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(VIEW_MODEL_KEY, viewModelClazz)
    }

    override fun init() {}

    companion object {
        private const val VIEW_MODEL_KEY = "viewModelClazz"
    }
}


