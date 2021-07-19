package com.ananananzhuo.livedataviewmodeldemo

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ananananzhuo.livedataviewmodeldemo.livedata.LiveDataActivity
import com.ananananzhuo.livedataviewmodeldemo.viewmodel.ViewModelActivity
import com.ananananzhuo.livedataviewmodeldemo.viewmodelsharedatawithfragment.ViewModelShareDataActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.reflect.InvocationTargetException

fun logEE(msg: String) {
    Log.e("安安安安卓", msg)
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_tolivedata.setOnClickListener {
            startActivity(Intent(this, LiveDataActivity::class.java))
        }
        btn_toviewmodel.setOnClickListener {
            startActivity(Intent(this, ViewModelActivity::class.java))
        }

        btn_fragment_sharedata.setOnClickListener {
            startActivity(Intent(this, ViewModelShareDataActivity::class.java))
        }
        btn_tosame_activity.setOnClickListener {
            startActivity(Intent(this, SameViewModelActivity::class.java))
        }
    }
}