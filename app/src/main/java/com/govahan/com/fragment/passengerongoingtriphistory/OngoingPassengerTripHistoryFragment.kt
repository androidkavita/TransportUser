package com.govahan.com.fragment.passengerongoingtriphistory

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.govahan.com.R
import com.govahan.com.activities.passengerOngoingBookingDetails.PassengerOngoingBookingDetailsActivity
import com.govahan.com.adapters.OngoingPassengerTripHistoryAdapter
import com.govahan.com.baseClasses.BaseFragment
import com.govahan.com.databinding.FragmentOngoingPassengerTripHistoryBinding
import com.govahan.com.model.ongoingPassengerTripHistoryModel.OngoingPassengerHistoryData
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class OngoingPassengerTripHistoryFragment : BaseFragment() , OngoingPassengerTripHistoryAdapter.OnClick {

    private lateinit var binding : FragmentOngoingPassengerTripHistoryBinding
    val progressBarStatus = MutableLiveData<Boolean>()
    private val viewModel : PassengerOngoingTripHistoryFragmentViewModel by viewModels()
    private var ongoingTripHistoryAdapter : OngoingPassengerTripHistoryAdapter?= null
    private var listData: ArrayList<OngoingPassengerHistoryData> = ArrayList()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ongoing_passenger_trip_history, container, false)

        // Inflate the layout for this fragment

        /* val layoutManagerTopSongs: RecyclerView.LayoutManager = LinearLayoutManager(requireContext(),
             GridLayoutManager.VERTICAL, false)
         binding.rvOngoing.setLayoutManager(layoutManagerTopSongs)
         val itemsTopSongs: List<String> = Arrays.asList("item", "item", "item", "item", "item", "item", "item", "item", "item", "item")
         binding.rvOngoing.setAdapter(OngoingTripAdapter(requireContext(), itemsTopSongs))
 */






        viewModel.progressBarStatus.observe(requireActivity()) {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        }

        viewModel.getPassengerOngoingHistoryResponse.observe(requireActivity()) {
            if (it.status == 1) {
                listData.clear()
                // listData!!.addAll(it.getFavLocdata)

                if (it.data.isEmpty() ) {
                    binding.idNouser.visibility = View.VISIBLE
                    binding.rvOngoing.visibility = View.GONE

                }
                else {
                    binding.idNouser.visibility = View.GONE
                    binding.rvOngoing.visibility = View.VISIBLE
                    listData.addAll(it.data)
                    ongoingTripHistoryAdapter = OngoingPassengerTripHistoryAdapter(listData,this@OngoingPassengerTripHistoryFragment)
                    binding.rvOngoing.apply {
                        adapter = ongoingTripHistoryAdapter
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
        viewModel.passengerOngoingBookingTripHistoryApi("Bearer " + userPref.user.apiToken)









        return binding.root    }






    override fun onDetailClicked(ongoingPassengerHistoryData: OngoingPassengerHistoryData) {
       startActivity(Intent(requireContext(), PassengerOngoingBookingDetailsActivity :: class.java).also {
           it.putExtra("loaderOngoingHistoryDetails", ongoingPassengerHistoryData)

        })

        Log.d("TAG++", "onProceedClicked: "+ongoingPassengerHistoryData.bookingId.toString())
    }


}