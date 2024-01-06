package com.govahan.com.fragment.passengertripmanagement

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.govahan.com.R
import com.govahan.com.activities.passengers.passengertripdetails.PassengerTripDetailsActivity
import com.govahan.com.adapters.PassengerTripManagementAdapter
import com.govahan.com.baseClasses.BaseFragment
import com.govahan.com.databinding.FragmentPassengerTripManagementBinding
import com.govahan.com.model.tripmanagementpassengermodel.PassengerTripManagementData
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class PassengerTripManagementFragment : BaseFragment(), PassengerTripManagementAdapter.OnClick {

    private lateinit var binding : FragmentPassengerTripManagementBinding
    private val viewModel : PassengerTripManagementFragmentViewModel by viewModels()
    private var tripManagementAdapter : PassengerTripManagementAdapter?= null
    private var listData: ArrayList<PassengerTripManagementData> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_passenger_trip_management, container, false)
        viewModel.getPassengerTripManagementApi("Bearer " + userPref.user.apiToken)

        viewModel.progressBarStatus.observe(requireActivity()) {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        }

        viewModel.getPassengerTripManagementResponse.observe(requireActivity()) {
            if (it.status == 1) {
                listData.clear()
                // listData!!.addAll(it.getFavLocdata)
                if (it.data.isEmpty() ) {
                    binding.idNouser.visibility = View.VISIBLE
                    binding.rvPassengerTrip.visibility = View.GONE
                }
                else {
                    binding.idNouser.visibility = View.GONE
                    binding.rvPassengerTrip.visibility = View.VISIBLE
                    listData.addAll(it.data)
                    tripManagementAdapter = PassengerTripManagementAdapter(listData,this@PassengerTripManagementFragment)
                    binding.rvPassengerTrip.apply {
                        adapter = tripManagementAdapter
                        layoutManager = LinearLayoutManager(requireActivity())
                        // it.getFavLocdata?.let { notificationList?.addAll(it) }
                        //    favouriteLocationsAdapter?.notifyDataSetChanged()
                    }
                }
            } else   {
                Log.d("Response", it.toString())
                toast(requireContext(),it.message!!)
            }
        }

        return binding.root
    }







    override fun onResume() {
        super.onResume()
        viewModel.getPassengerTripManagementApi("Bearer " + userPref.user.apiToken)

    }

    override fun onProceedPassengerClicked(passengerTripListModelData: PassengerTripManagementData) {
        startActivity(Intent(requireContext(), PassengerTripDetailsActivity :: class.java).also {
            it.putExtra("passengerTripDetails", passengerTripListModelData)

        })

        Log.d("TAG++", "onProceedClicked: "+passengerTripListModelData.bookingId.toString())
    }


}