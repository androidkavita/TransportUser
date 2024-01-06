package com.govahan.com.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.govahan.com.fragment.passengercompletedtriphistory.CompletedPassengerTripHistoryFragment
import com.govahan.com.fragment.passengerongoingtriphistory.OngoingPassengerTripHistoryFragment

class PassengerBookingHistoryViewPagerAdapter(
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val titles =
        arrayOf("Ongoing", "Completed")

    override fun getItem(position: Int): Fragment {

        val bundle = Bundle()
        val fragment : Fragment
        return when (position) {
            0 -> {
                fragment = OngoingPassengerTripHistoryFragment()
                bundle.putInt("type",0)
                fragment.arguments = bundle
                fragment
            }
            else -> {
                fragment = CompletedPassengerTripHistoryFragment()
                bundle.putInt("type",1)
                fragment.arguments = bundle
                fragment
            }
        }
    }

    override fun getCount(): Int {
        return titles.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}