package com.fastcontech.weatherapp.ui.activity


import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.fastcontech.weatherapp.R
import com.fastcontech.weatherapp.core.models.Favourite
import com.fastcontech.weatherapp.core.models.ListElement
import com.fastcontech.weatherapp.data.local.db.DatabaseBuilder
import com.fastcontech.weatherapp.data.local.repository.DatabaseImpl
import com.fastcontech.weatherapp.data.network.NetworkConstants
import com.fastcontech.weatherapp.databinding.ActivityCityWeatherBinding
import com.fastcontech.weatherapp.ui._base.activity.BaseActivity
import com.fastcontech.weatherapp.ui._base.recycler_view.ItemClickListener
import com.fastcontech.weatherapp.ui.fragments.WeatherAdapter
import com.fastcontech.weatherapp.ui.view_model.FavouriteViewModel
import com.fastcontech.weatherapp.ui.view_model.WeatherViewModel
import com.fastcontech.weatherapp.core.models.Status
import org.koin.android.ext.android.inject


class CityWeatherDetails : BaseActivity<ViewDataBinding>(), ItemClickListener<ListElement> {
    private lateinit var binding: ActivityCityWeatherBinding

    private val weatherViewModel :WeatherViewModel by inject()

    private val weatherAdapter by lazy {
        WeatherAdapter(this, this)
    }

    private val favouriteViewModel by lazy {
        ViewModelProvider(this)[FavouriteViewModel::class.java]
    }
    private val databaseImpl by lazy {
       DatabaseImpl(DatabaseBuilder.getInstance(context = applicationContext))
    }

    private var favouriteSaved =  Favourite("","")

    override fun getLayoutId(): Int {
        return R.layout.activity_city_weather
    }

    override fun initializeViews() {
        binding = ActivityCityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val bundle = intent.extras?.getString("city")
        println("bundle $bundle")
        initRecyclerView()


        if (bundle != null) {
            weatherViewModel.getCityWeather(bundle)
            weatherViewModel.responseCitiesWeather.observe(
                this,
                Observer {
                    if (it.status != Status.ERROR) {
                        binding.localCity.text = it.data?.city?.name.toString()
                        binding.temperature.text =
                            "${it.data?.list?.firstOrNull()?.main?.temp.toString()} ${(0x00B0.toChar())} C"
                        binding.humidity.text =
                            "Humidity : ${it.data?.list?.firstOrNull()?.main?.humidity.toString()} %"
                        binding.wind.text =
                            "Wind speed : ${it.data?.list?.firstOrNull()?.wind?.speed.toString()} m/s"
                        Glide.with(this)
                            .load(NetworkConstants.getImageUrl(it.data?.list?.firstOrNull()?.weather?.firstOrNull()?.icon.toString()))
                            .centerCrop()
                            .skipMemoryCache(true)
                            .into(binding.weatherIcon)
                        favouriteSaved = Favourite( cityName = it.data?.city?.name.toString(), countryName =  it.data?.city?.country.toString())
                        it.data?.list?.let { list -> weatherAdapter.setData(list) }
                    } else {
                             binding.forecastTitle.text = "An error occurred"
                            Toast.makeText(this,"Error getting your local city",Toast.LENGTH_LONG).show()

                    }
                }
            )
        }
    }



    private fun initRecyclerView() {
        with(binding) {
            weatherRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
            weatherRecyclerView.adapter = weatherAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.save_favorite_menu, menu)
        return true
    }


    override fun onBackPressed() {
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.save_favourite) {

            favouriteViewModel.saveFavouritesLocally(databaseImpl, listOf(favouriteSaved))
            Toast.makeText(this,"Favourite location saved",Toast.LENGTH_LONG).show()
            return true
        }
        else if(id == R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onItemClick(data: ListElement?, position: Int, typeClick: Int) {
       //do nothing
    }
}
