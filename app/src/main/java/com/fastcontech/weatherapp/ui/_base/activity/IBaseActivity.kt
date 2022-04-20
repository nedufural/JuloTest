package com.fastcontech.weatherapp.ui._base.activity

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment

interface IBaseActivity {
    fun getLayoutId(): Int
    fun bindViewLayout()
    fun statusBarColourChanger()

    fun showActivity(sourceContext: Context, destinationContext: Class<*>, bundle: Bundle)
    fun showFragment(fragmentList: Fragment, container: Int)
}
