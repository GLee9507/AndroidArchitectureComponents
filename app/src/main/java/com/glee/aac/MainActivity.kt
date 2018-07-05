package com.glee.aac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.glee.aac.databinding.MainActivityBinding
import com.glee.aac.ui.main.MainFragment
import com.glee.aac.ui.test.TestFragment
import com.glee.aac.ui.test.TestViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<MainActivityBinding>(this, R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, TestFragment.newInstance(TestViewModel::class.java))
                    .commitNow()
        }
    }

}
