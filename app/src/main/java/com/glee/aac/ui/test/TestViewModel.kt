package com.glee.aac.ui.test

import androidx.lifecycle.MutableLiveData
import com.glee.aac.base.BaseViewModel

/**
 * Created with Android Studio.
 * OS: Arch Linux
 * Description:
 * User: Liji
 * Date: 2018-07-05
 * Time: 10:48 PM
 */
class TestViewModel : BaseViewModel() {
    val test by lazy {
        MutableLiveData<String>()
    }

    init {
        Thread {
            for (i in 1..10) {
                Thread.sleep(1000)
                test.postValue(i.toString())
            }
        }.start()
    }
}