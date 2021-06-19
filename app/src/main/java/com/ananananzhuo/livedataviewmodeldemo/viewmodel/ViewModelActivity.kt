package com.ananananzhuo.livedataviewmodeldemo.viewmodel

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.ananananzhuo.livedataviewmodeldemo.R
import com.ananananzhuo.livedataviewmodeldemo.logEE
import kotlinx.android.synthetic.main.activity_view_model.*

class ViewModelActivity : AppCompatActivity() {

    private val wtModel: EnTrustModel by viewModels<EnTrustModel> {
        ViewModelProvider.NewInstanceFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)
        btn_create_withsimplefactory.setOnClickListener {
            val model =
                ViewModelProvider.NewInstanceFactory().create(SimpleFactoryViewModel::class.java)
            model.print()

        }
        btn_create_withandroidfactory.setOnClickListener {
            val model = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
                .create(AndroidFactoryViewModel::class.java)
            model.print()
        }
        btn_create_withsimplefactory_custom.setOnClickListener {
            val model = ViewModelProvider(
                viewModelStore,
                CustomSimpleViewModelFactory(application, "自定义简单工厂创建viewmodel")
            ).get(CustomSimpleViewModel::class.java)
            model.print()
            logEE("model对象打印 ${model.toString()}")
        }
        btn_create_withandroidfactory_custom.setOnClickListener {
            val model = ViewModelProvider(
                viewModelStore,
                CustomAndroidViewModelFactory(application, "自定义安卓工厂创建viewmodel")
            ).get(CustomAndroidViewModel::class.java)
            model.print()
            logEE("model对象打印 ${model.toString()}")
        }

        btn_viewmodel_clear.setOnClickListener {
            startActivity(Intent(this, RotationActivity::class.java))
        }

        btn_create_with_entrustviewmodel.setOnClickListener {
            wtModel.print()
        }
    }
}