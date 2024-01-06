package com.govahan.com.activities.complaintboxlist

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.govahan.com.R
import com.govahan.com.adapters.ViewPagerComplaintBoxAdapter
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityComplaintBoxListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComplaintBoxListActivity : BaseActivity() {

    private lateinit var binding : ActivityComplaintBoxListBinding
    private var pagerAdapter: ViewPagerComplaintBoxAdapter? = null

//activity_complaint_box_list
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

    binding = DataBindingUtil.setContentView(this, R.layout.activity_complaint_box_list)

    binding.header.ivBack.setOnClickListener(View.OnClickListener {
        finish()

    })


    binding.header.tvHeaderText.setText("Compalint Box")
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

        pagerAdapter = ViewPagerComplaintBoxAdapter(supportFragmentManager)
        binding.idViewPager.adapter = pagerAdapter
        binding.tabLayout.setupWithViewPager(binding.idViewPager)
        binding.tabLayout.getChildAt(0)

        binding.idViewPager.adapter = pagerAdapter
        binding.tabLayout.setupWithViewPager(binding.idViewPager)
    }


}