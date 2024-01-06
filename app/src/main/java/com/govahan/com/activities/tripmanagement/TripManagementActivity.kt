package com.govahan.com.activities.tripmanagement

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.govahan.com.R
import com.govahan.com.adapters.ViewPagerTripManagementAdapter
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityTripManagementBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TripManagementActivity : BaseActivity() {
    private lateinit var binding : ActivityTripManagementBinding
    private var pagerAdapter: ViewPagerTripManagementAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_trip_management)

        binding.header.ivBack.setOnClickListener(View.OnClickListener {
            finish()

        })


        binding.header.tvHeaderText.setText("Trip Management")
        setTab()

        /*val layoutManagerTopSongs: RecyclerView.LayoutManager = LinearLayoutManager(this,
            GridLayoutManager.VERTICAL, false)
        binding.rvAvailableVehicles.setLayoutManager(layoutManagerTopSongs)
        val itemsTopSongs: List<String> = Arrays.asList("item", "item", "item", "item", "item", "item", "item", "item", "item", "item")
        binding.rvAvailableVehicles.setAdapter(TripManagementAdapter(this, itemsTopSongs))
        */

    }


    private fun setTab() {
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Loader"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Passenger"))

        pagerAdapter = ViewPagerTripManagementAdapter(supportFragmentManager)
        binding.viewPager.adapter = pagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)


        binding.tabLayout.getChildAt(0)

        binding.viewPager.adapter = pagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }


}