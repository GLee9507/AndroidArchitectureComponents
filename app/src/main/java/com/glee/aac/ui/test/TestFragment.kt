package com.glee.aac.ui.test

import android.annotation.SuppressLint
import com.glee.aac.R
import com.glee.aac.base.BaseFragment
import com.glee.aac.databinding.TestFragmentBinding

@SuppressLint("ValidFragment")
/**
 * Created with Android Studio.
 * OS: Arch Linux
 * Description:
 * User: Liji
 * Date: 2018-07-05
 * Time: 10:46 PM
 */
class TestFragment : BaseFragment<TestViewModel,TestFragmentBinding>() {

    override val layoutId = R.layout.test_fragment
    override fun init() {
        binding.viewModel
    }
}