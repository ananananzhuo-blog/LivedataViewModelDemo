package com.ananananzhuo.livedataviewmodeldemo.viewmodelsharedatawithfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.liveData
import com.ananananzhuo.livedataviewmodeldemo.R
import kotlinx.android.synthetic.main.activity_view_model_share_data.*

class ViewModelShareDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model_share_data)

        btn_showA.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.fl_container,FragmentA()).commit()
        }
        btn_showB.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.fl_container,FragmentB()).commit()
        }
    }
}