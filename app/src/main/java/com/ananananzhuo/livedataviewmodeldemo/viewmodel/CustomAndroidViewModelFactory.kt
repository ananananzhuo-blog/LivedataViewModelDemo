package com.ananananzhuo.livedataviewmodeldemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.InvocationTargetException

/**
 * author  :mayong
 * function:
 * date    :2021/6/18
 **/
class CustomAndroidViewModelFactory(val app: Application, private val data: String) :
    ViewModelProvider.AndroidViewModelFactory(app) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (AndroidViewModel::class.java.isAssignableFrom(modelClass)) {
            return try {
                modelClass.getConstructor(Application::class.java, String::class.java)
                    .newInstance(app, data)
            } catch (e: NoSuchMethodException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            } catch (e: IllegalAccessException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            } catch (e: InstantiationException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            } catch (e: InvocationTargetException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            }
        }
        return super.create(modelClass)
    }
}