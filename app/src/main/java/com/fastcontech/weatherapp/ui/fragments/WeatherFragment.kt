import android.Manifest
import android.content.Context.LOCATION_SERVICE
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.fastcontech.weatherapp.R
import com.fastcontech.weatherapp.domain.Util
import com.fastcontech.weatherapp.core.models.Status
import com.fastcontech.weatherapp.core.models.ViewConstants
import com.fastcontech.weatherapp.core.models.WeatherEntity
import com.fastcontech.weatherapp.data.network.NetworkConstants
import com.fastcontech.weatherapp.data.network.NetworkStatus
import com.fastcontech.weatherapp.databinding.FragmentWeatherBinding
import com.fastcontech.weatherapp.ui._base.fragment.BaseFragment
import com.fastcontech.weatherapp.ui._base.recycler_view.ItemClickListener
import com.fastcontech.weatherapp.ui.activity.CityWeatherDetails
import com.fastcontech.weatherapp.ui.view_model.WeatherViewModel
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class WeatherFragment :
    BaseFragment<FragmentWeatherBinding>(FragmentWeatherBinding::inflate),
    LocationListener,
    ItemClickListener<WeatherEntity> {


    private val weatherViewModel :WeatherViewModel by viewModel()
    private val networkStatus:NetworkStatus by inject()


    private lateinit var util: Util
    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener

    override fun initData() {
        super.initData()

        locationListener = this

        util = Util(requireContext(),requireActivity())
        if(networkStatus.networkStatus(requireContext())) {
            util.checkPermission(Manifest.permission.ACCESS_FINE_LOCATION, 101)
            locationManager = context?.getSystemService(LOCATION_SERVICE) as LocationManager
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                0L,
                0F,
                locationListener
            )

            val degreeTemp = StringBuilder()
            val icon = StringBuilder()


            weatherViewModel.getWeather("37", "122" )
            weatherViewModel.responseWeather.observe(
                this,
                Observer { weather ->
                    if (weather.status != Status.ERROR) {
                        icon.append(weather?.data?.daily?.first()?.weather?.first()?.icon)
                        degreeTemp.append(
                            weather.data?.daily?.first()?.temp?.day?.toInt().toString()
                        )
                        binding.temperature.text =
                            degreeTemp.append((0x00B0.toChar()).toString() + "C")
                        Glide.with(this)
                            .load(NetworkConstants.getImageUrl(icon.toString()))
                            .centerCrop()
                            .skipMemoryCache(true)
                            .into(binding.weatherIcon)
                        binding.humidity.text =
                            "Humidity : ${weather.data?.daily?.firstOrNull()?.humidity} %"
                        binding.wind.text =
                            "Wind speed : ${weather.data?.daily?.firstOrNull()?.wind_speed} m/s"
                    } else {
                        Toast.makeText(context, "Error getting your local city", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            )
            binding.search.setOnClickListener {
                binding.search.setOnClickListener {
                    val city = binding.editTextCity.text.toString()
                    val bundle = Bundle()
                    bundle.putString("city", city)
                    showActivity(requireContext(), CityWeatherDetails::class.java, bundle)
                }
            }
        } else{
            binding.localCity.text = ViewConstants.AN_ERROR_OCCURRED
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        util.onRequestPermissionsResult(grantResults)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_weather
    }

    override fun onLocationChanged(location: Location) {
        println("getUserLocation")
        getUserLocation(location)
    }

    override fun onDestroy() {
        try {
            locationManager.removeUpdates(this)
        }catch (e: Exception){
            e.message
        }
        super.onDestroy()
    }

    private fun getUserLocation(p0: Location) {

        val geocoder = Geocoder(context, Locale.getDefault())
        val addresses = geocoder.getFromLocation(p0.latitude, p0.longitude, 10)
        println("address ${addresses[0].getAddressLine(0)}")
        if (addresses != null && addresses.size > 0) {
            val city = addresses[0].locality
            val country = addresses[0].countryName
            println("$city, $country")
            binding.localCity.text = "$city, $country"
        }
    }

    override fun onItemClick(data: WeatherEntity?, position: Int, typeClick: Int) {
        // do nothing
    }
}
