package com.example.speechtotextandanswerapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.speechtotextandanswerapp.datasource.SharedPreferencesManager

abstract class BaseActivity<VB: ViewDataBinding> : AppCompatActivity() {

    protected lateinit var viewBinding : VB

    abstract val layout:Int
    protected lateinit var sharedPreferencesManager: SharedPreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this,layout)
        sharedPreferencesManager = SharedPreferencesManager(this)
    }
}