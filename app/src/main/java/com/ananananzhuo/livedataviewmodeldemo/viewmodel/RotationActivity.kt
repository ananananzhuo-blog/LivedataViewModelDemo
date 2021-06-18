package com.ananananzhuo.livedataviewmodeldemo.viewmodel

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ananananzhuo.livedataviewmodeldemo.R
import com.ananananzhuo.livedataviewmodeldemo.logEE
import kotlinx.android.synthetic.main.activity_rotation.*

class RotationActivity : AppCompatActivity() {
    val create: RotationViewModel by viewModels<RotationViewModel> {
        ViewModelProvider.NewInstanceFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rotation)
        create.println("创建成功")
        btn_rotate.setOnClickListener {
            requestedOrientation =
                if (requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
                    ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                else
                    ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        logEE(create.toString())
    }
}

class RotationViewModel : ViewModel() {

    override fun onCleared() {
        super.onCleared()
        logEE("回收viewmodel")
    }

    fun println(s: String) {
        logEE(s)
    }
}