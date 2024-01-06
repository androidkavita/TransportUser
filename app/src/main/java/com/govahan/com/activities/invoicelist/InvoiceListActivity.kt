package com.govahan.com.activities.invoicelist

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.govahan.com.R
import com.govahan.com.adapters.ViewPagerInvoiceListAdapter
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityInvoiceListBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class InvoiceListActivity : BaseActivity()  {
    private lateinit var binding : ActivityInvoiceListBinding
    private var pagerAdapter: ViewPagerInvoiceListAdapter?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_invoice_list)

        binding.header.ivBack.setOnClickListener(View.OnClickListener {
            finish()
        })

        binding.header.tvHeaderText.setText("Invoice List")
        setTab()


    }


    private fun setTab() {
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Loader"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Passenger"))

        pagerAdapter = ViewPagerInvoiceListAdapter(supportFragmentManager)
        binding.viewPager.adapter = pagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)


        binding.tabLayout.getChildAt(0)

        binding.viewPager.adapter = pagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }


}