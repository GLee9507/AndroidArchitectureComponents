package com.glee.aac.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

interface View {
    val layoutId: Int
}

abstract class BaseFragment<T : ViewDataBinding> : Fragment(), com.glee.aac.base.View {
    lateinit var binding: T

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            DataBindingUtil.inflate<T>(inflater, layoutId, container, false).let {
                binding = it
                binding.root
            }


}