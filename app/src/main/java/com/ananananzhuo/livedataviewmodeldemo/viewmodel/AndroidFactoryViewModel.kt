package com.ananananzhuo.livedataviewmodeldemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ananananzhuo.livedataviewmodeldemo.logEE

/**
 * author  :mayong
 * function:
 * date    :2021/6/18
 **/
class AndroidFactoryViewModel(app:Application): AndroidViewModel(app) {
    fun print(){
        logEE("使用安卓默认工厂创建viewmodel")
    }
}