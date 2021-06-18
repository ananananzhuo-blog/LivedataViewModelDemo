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
class CustomSimpleViewModel(private val data: String) : ViewModel() {
    fun print() {
        logEE(data)
    }
}