package com.ananananzhuo.livedataviewmodeldemo.viewmodel

import androidx.lifecycle.ViewModel
import com.ananananzhuo.livedataviewmodeldemo.logEE

/**
 * author  :mayong
 * function:
 * date    :2021/6/18
 **/
class EnTrustModel : ViewModel() {
    fun print(){
        logEE("关注公众号 \"安安安安卓\" 免费学知识")
    }
}