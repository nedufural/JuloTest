package com.fastcontech.weatherapp.ui._base.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity(), IBaseActivity {

    private var dataBinding: B? = null
    private var colour: Int = androidx.appcompat.R.color.material_blue_grey_800

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindViewLayout()
        initializeViews()
        statusBarColourChanger()
    }

    override fun bindViewLayout() {
        dataBinding = DataBindingUtil.setContentView(this, getLayoutId())
    }

    override fun statusBarColourChanger() {
        window.statusBarColor = resources.getColor(colour, theme)
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

    override fun showFragment(fragmentList: Fragment, container: Int) {

        val manager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()

        transaction.also {
            it.replace(container, fragmentList)
            it.detach(fragmentList)
            it.attach(fragmentList)
            it.addToBackStack(null)
            it.commit()
        }
    }

    abstract fun initializeViews()
}
