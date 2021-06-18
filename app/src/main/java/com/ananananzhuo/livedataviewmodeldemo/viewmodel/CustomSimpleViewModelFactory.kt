package com.ananananzhuo.livedataviewmodeldemo.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * author  :mayong
 * function:
 * date    :2021/6/18
 **/
class CustomSimpleViewModelFactory(app:Application,private val data:String) : ViewModelProvider.AndroidViewModelFactory(app) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return try {
            modelClass.getConstructor(String::class.java).newInstance(data)
        } catch (e: InstantiationException) {
            throw java.lang.RuntimeException("Cannot create an instance of $modelClass", e)
        } catch (e: IllegalAccessException) {
            throw java.lang.RuntimeException("Cannot create an instance of $modelClass", e)
        }
    }
}