package com.govahan.com.activities.mainBookingHistory

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.govahan.com.R
import com.govahan.com.adapters.MainBookingHistoryViewPagerAdapter
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityMainBookingHistoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainBookingHistoryActivity : BaseActivity() {
    lateinit var binding : ActivityMainBookingHistoryBinding

    private var pagerAdapter: MainBookingHistoryViewPagerAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)



        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_booking_history)

        binding.header.ivBack.setOnClickListener(View.OnClickListener {
            finish()

        })
        binding.header.tvHeaderText.setText("Booking History")
        setTab()
    }


    private fun setTab() {
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Loader"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Passenger"))

        pagerAdapter = MainBookingHistoryViewPagerAdapter(supportFragmentManager)
        binding.viewPager.adapter = pagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        binding.tabLayout.getChildAt(0)

        binding.viewPager.adapter = pagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }


}