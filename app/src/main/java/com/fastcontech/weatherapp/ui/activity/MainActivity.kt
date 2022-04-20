package com.fastcontech.weatherapp.ui.activity

import WeatherFragment
import androidx.databinding.ViewDataBinding
import com.fastcontech.weatherapp.R
import com.fastcontech.weatherapp.databinding.ActivityMainBinding
import com.fastcontech.weatherapp.ui._base.activity.BaseActivity
import com.fastcontech.weatherapp.ui.fragments.FavouriteFragment

class MainActivity : BaseActivity<ViewDataBinding>() {

    private lateinit var binding: ActivityMainBinding

    private val cityFragment = WeatherFragment()
    private val favouriteFragment = FavouriteFragment()

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initializeViews() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initEvent()
        navigateFragments()
    }

    private fun initEvent() {
        binding.swiperefresh.setOnRefreshListener {
            showFragment(favouriteFragment, R.id.container)
            showFragment(cityFragment, R.id.container)
            binding.swiperefresh.isRefreshing = false
        }

    }

    private fun navigateFragments() {
        showFragment(cityFragment, R.id.container)
        binding.navigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.temp -> showFragment(cityFragment, R.id.container)
                R.id.favourite -> showFragment(favouriteFragment, R.id.container)
            }
            true
        }
    }

    override fun onResume() {
        super.onResume()
        showFragment(favouriteFragment, R.id.container)
        showFragment(cityFragment, R.id.container)
    }
}
