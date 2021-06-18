package com.ananananzhuo.livedataviewmodeldemo.viewmodel

import androidx.lifecycle.ViewModel
import com.ananananzhuo.livedataviewmodeldemo.logEE

/**
 * author  :mayong
 * function:
 * date    :2021/6/18
 **/
class SimpleFactoryViewModel: ViewModel() {
    fun print(){
        logEE("使用简单工厂创建viewmodel")
    }
}