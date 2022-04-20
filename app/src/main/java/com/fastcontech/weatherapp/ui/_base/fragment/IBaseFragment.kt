package io.fastcontech.weatherapp.ui._base.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

interface IBaseFragment {
    fun onViewCreated(view: View, savedInstanceState: Bundle?)
    fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?

    fun getLayoutId(): Int
    fun initData()
    fun initEvent()
    fun showToast(activity: Context, msg: String)
    fun showActivity(sourceContext: Context, destinationContext: Class<*>, bundle: Bundle)
}
