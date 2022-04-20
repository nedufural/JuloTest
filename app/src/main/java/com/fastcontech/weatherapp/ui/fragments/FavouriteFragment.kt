package com.fastcontech.weatherapp.ui.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fastcontech.weatherapp.R
import com.fastcontech.weatherapp.domain.SwipeToDelete
import com.fastcontech.weatherapp.core.models.Favourite
import com.fastcontech.weatherapp.data.local.db.DatabaseBuilder
import com.fastcontech.weatherapp.data.local.repository.DatabaseImpl
import com.fastcontech.weatherapp.databinding.FragmentFavouriteBinding
import com.fastcontech.weatherapp.ui._base.fragment.BaseFragment
import com.fastcontech.weatherapp.ui._base.recycler_view.ItemClickListener
import com.fastcontech.weatherapp.ui.activity.CityWeatherDetails
import com.fastcontech.weatherapp.ui.view_model.FavouriteViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouriteFragment : BaseFragment<FragmentFavouriteBinding>(FragmentFavouriteBinding::inflate),
    ItemClickListener<Favourite> {

    private val favouriteViewModel: FavouriteViewModel by viewModel()
    private lateinit var databaseImpl: DatabaseImpl

    private lateinit var favouriteAdapter: FavouriteAdapter

    override fun getLayoutId(): Int {
        return R.layout.fragment_favourite
    }


    override fun initData() {

        initRecyclerView()
        databaseImpl = DatabaseImpl(DatabaseBuilder.getInstance(requireContext()))
        favouriteViewModel.getFavouritesLocally(databaseImpl)
        favouriteViewModel.favouriteResponse.observe(
            this,
            Observer {
                favouriteAdapter.setData(it)
            }
        )
    }


    override fun initEvent() {
        super.initEvent()
        enableSwipeToDeleteAndUndo()
    }

    private fun enableSwipeToDeleteAndUndo() {
        val swipeToDeleteCallback: SwipeToDelete = object : SwipeToDelete(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {

                val position = viewHolder.absoluteAdapterPosition
                val item: Favourite = favouriteAdapter.getData()[position]
                favouriteAdapter.remove(position)
                deleteCreditCard(Favourite(item.cityName, item.countryName))
                val snackbar = Snackbar
                    .make(
                        requireView(),
                        getString(R.string.item_deleted_undo),
                        Snackbar.LENGTH_LONG
                    )
                snackbar.setAction(getString(R.string.item_deleted_undo)) {
                    favouriteAdapter.restoreItem(item, position)
                    binding.favouriteRecyclerview.scrollToPosition(position)
                    insertCreditCard(Favourite(item.cityName, item.countryName))
                }
                snackbar.setActionTextColor(Color.YELLOW)
                snackbar.show()
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(binding.favouriteRecyclerview)
    }

    private fun deleteCreditCard(favourite: Favourite) {
        favouriteViewModel.deleteFavourites(databaseImpl, listOf(favourite))
    }

    private fun insertCreditCard(favourite: Favourite) {
        favouriteViewModel.saveFavouritesLocally(databaseImpl, listOf(favourite))
    }


    override fun onItemClick(data: Favourite?, position: Int, typeClick: Int) {
        println("cityName ${data?.cityName}")
        val bundle = Bundle()
        bundle.putString("city", data?.cityName)
        showActivity(requireContext(), CityWeatherDetails::class.java, bundle)
    }


    private fun initRecyclerView() {
        println("#initialing recyclerview")
        favouriteAdapter = FavouriteAdapter(this)
        with(binding) {
            favouriteRecyclerview.layoutManager = LinearLayoutManager(context)
            favouriteRecyclerview.adapter = favouriteAdapter
        }
    }
}
