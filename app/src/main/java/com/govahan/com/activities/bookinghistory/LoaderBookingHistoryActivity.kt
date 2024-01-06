package com.govahan.com.activities.bookinghistory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.govahan.com.R
import com.govahan.com.adapters.LoaderBookingHistoryViewPagerAdapter
import com.govahan.com.databinding.ActivityLoaderBookingHistoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoaderBookingHistoryActivity : AppCompatActivity() {
    lateinit var binding : ActivityLoaderBookingHistoryBinding

    private var pagerAdapter: LoaderBookingHistoryViewPagerAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_loader_booking_history)

        binding.header.ivBack.setOnClickListener(View.OnClickListener {
            finish()

        })
        binding.header.tvHeaderText.setText("Booking History")
        setTab()
    }


    private fun setTab() {
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Ongoing"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Completed"))

        pagerAdapter = LoaderBookingHistoryViewPagerAdapter(supportFragmentManager)
        binding.viewPager.adapter = pagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)


        binding.tabLayout.getChildAt(0)

        binding.viewPager.adapter = pagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }


}