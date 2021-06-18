package com.ananananzhuo.livedataviewmodeldemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.ananananzhuo.livedataviewmodeldemo.logEE

/**
 * author  :mayong
 * function:
 * date    :2021/6/18
 **/
class CustomAndroidViewModel(private val app: Application, private val data: String) :
    AndroidViewModel(app) {
    fun print() {
        logEE(data)
    }
}