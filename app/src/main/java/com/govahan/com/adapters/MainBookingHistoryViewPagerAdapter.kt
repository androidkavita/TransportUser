package com.govahan.com.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.govahan.com.fragment.loaderBookingHistory.LoaderBookingHistoryFragment
import com.govahan.com.fragment.passengerBookingHistory.PassengerBookingHistoryFragment

class MainBookingHistoryViewPagerAdapter(
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val titles =
        arrayOf("Loader", "Passenger")

    override fun getItem(position: Int): Fragment {

        val bundle = Bundle()
        val fragment : Fragment
        return when (position) {
            0 -> {
              //  fragment = OngoingLoaderTripHistoryFragment()
                fragment = LoaderBookingHistoryFragment()
                bundle.putInt("type",0)
                fragment.arguments = bundle
                fragment
            }
            else -> {
               // fragment = CompletedLoaderTripHistoryFragment()
                fragment = PassengerBookingHistoryFragment()
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