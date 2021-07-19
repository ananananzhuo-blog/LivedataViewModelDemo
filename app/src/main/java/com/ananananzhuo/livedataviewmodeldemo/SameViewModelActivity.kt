package com.ananananzhuo.livedataviewmodeldemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.ananananzhuo.livedataviewmodeldemo.viewmodel.ViewModelModel

/**
 * 启动同一个activity多次，并不会共用ViewModel
 */
class SameViewModelActivity : AppCompatActivity() {
    val model by viewModels<ViewModelModel> {
        ViewModelProvider.NewInstanceFactory()
    }
    val btn by lazy {
        findViewById<Button>(R.id.btn_viewmodel_model)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model2)
        model.count = model.count + 1
        btn.text = (model.count).toString()
        btn.setOnClickListener {
            startActivity(Intent(this, SameViewModelActivity::class.java))
        }
    }
}