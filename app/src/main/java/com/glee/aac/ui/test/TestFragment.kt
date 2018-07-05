package com.glee.aac.ui.test

import android.os.Bundle
import com.glee.aac.R
import com.glee.aac.base.BaseFragment
import com.glee.aac.base.BaseViewModel
import com.glee.aac.databinding.TestFragmentBinding

/**
 * Created with Android Studio.
 * OS: Arch Linux
 * Description:
 * User: Liji
 * Date: 2018-07-05
 * Time: 10:46 PM
 */
class TestFragment : BaseFragment<TestFragmentBinding, TestViewModel>() {
    override val layoutId = R.layout.test_fragment

    override fun init() {
    }

    companion object {
        fun newInstance(clazz: Class<out BaseViewModel>): TestFragment {
            return TestFragment().apply {
                arguments = Bundle().apply { putSerializable("vmClass", clazz) }
            }
        }
    }
}