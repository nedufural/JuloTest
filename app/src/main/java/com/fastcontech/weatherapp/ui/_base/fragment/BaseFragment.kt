package com.fastcontech.weatherapp.ui._base.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import io.fastcontech.weatherapp.ui._base.fragment.IBaseFragment

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<V : ViewBinding>(private val inflate: Inflate<V>) :
    Fragment(),
    IBaseFragment {

    private lateinit var _binding: V
    val binding get() = _binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initEvent()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun initData() {}

    override fun initEvent() {}

    override fun showToast(activity: Context, msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
    }

    override fun showActivity(
        sourceContext: Context,
        destinationContext: Class<*>,
        bundle: Bundle
    ) {
        val nextScreen = Intent(sourceContext, destinationContext)
        if (!bundle.isEmpty) {
            nextScreen.putExtras(bundle)
            startActivity(nextScreen)
        } else {
            startActivity(nextScreen)
        }
    }
}
