package com.ananananzhuo.livedataviewmodeldemo.viewmodelsharedatawithfragment

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ananananzhuo.livedataviewmodeldemo.logEE

/**
 * author  :mayong
 * function:
 * date    :2021/6/18
 **/
class ShareDataModel: ViewModel() {
    val liveData = MutableLiveData<String>()
    var total = 2000L
    init {
        /**
         * 实现倒计时，一秒钟倒计时一次
         */
        val countDownTimer = object :CountDownTimer(1000 * total, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                liveData.postValue("剩余倒计时时间${--total}")
            }
            override fun onFinish() {
                logEE("倒计时完成")
            }
        }
        countDownTimer.start()
    }
}