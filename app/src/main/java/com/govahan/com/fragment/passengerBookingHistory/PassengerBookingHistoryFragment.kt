package com.govahan.com.fragment.passengerBookingHistory

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.govahan.com.R
import com.govahan.com.activities.passengerCancelledBookingDetails.PassengerCancelledBookingDetailsActivity
import com.govahan.com.activities.passengerCompletedBookingDetails.PassengerCompletedBookingDetailsActivity
import com.govahan.com.activities.passengerOngoingBookingDetails.PassengerOngoingBookingDetailsActivity
import com.govahan.com.adapters.*
import com.govahan.com.baseClasses.BaseFragment
import com.govahan.com.databinding.FragmentPassengerBookingHistoryBinding
import com.govahan.com.fragment.passengercompletedtriphistory.PassengerCancelledTripHistoryFragmentViewModel
import com.govahan.com.fragment.passengercompletedtriphistory.PassengerCompletedTripHistoryFragmentViewModel
import com.govahan.com.fragment.passengerongoingtriphistory.PassengerOngoingTripHistoryFragmentViewModel
import com.govahan.com.model.cancelledpassengertriphistorymodel.CancelledPassengerTripHistoryData
import com.govahan.com.model.completedpassengertriphistorymodel.CompletedPassengerHistoryData
import com.govahan.com.model.ongoingPassengerTripHistoryModel.OngoingPassengerHistoryData
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class PassengerBookingHistoryFragment : BaseFragment() , OngoingPassengerTripHistoryAdapter.OnClick, CompletedPassengerTripHistoryAdapter.OnClick, CancelledPassengerTripHistoryAdapter.OnClick {

    lateinit var binding : FragmentPassengerBookingHistoryBinding
    private var passengerpagerAdapter: PassengerBookingHistoryViewPagerAdapter? = null
    var selectedItem = ""
    val progressBarStatus = MutableLiveData<Boolean>()
    private val viewModelPassengerOngoing : PassengerOngoingTripHistoryFragmentViewModel by viewModels()
    private var ongoingTripHistoryAdapter : OngoingPassengerTripHistoryAdapter?= null
    private var listDataPassengerOngoing: ArrayList<OngoingPassengerHistoryData> = ArrayList()
    private val viewModelPassengerCompleted : PassengerCompletedTripHistoryFragmentViewModel by viewModels()
    private var completedPassengerTripHistoryAdapter : CompletedPassengerTripHistoryAdapter?= null
    private var listDataPassengerCompleted: ArrayList<CompletedPassengerHistoryData> = ArrayList()
    private val viewModelCancelledPassenger : PassengerCancelledTripHistoryFragmentViewModel by viewModels()
    private var cancelledPassengerTripHistoryAdapter : CancelledPassengerTripHistoryAdapter?= null
    private var listDataCancelledPassenger: ArrayList<CancelledPassengerTripHistoryData> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_passenger_booking_history, container, false)


        /*viewModelPassengerOngoing.progressBarStatus.observe(requireActivity()) {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        }*/

      //  setTab()

        binding.spinnerLoaderHistory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedItem = parent.getItemAtPosition(position).toString()
                Log.d("PositionSelected",selectedItem)
                if (selectedItem == "Ongoing") {
                    toast(requireContext(),"Ongoing")
                    viewModelPassengerOngoing.passengerOngoingBookingTripHistoryApi("Bearer " + userPref.user.apiToken)
                    Log.d(ContentValues.TAG, "onCreateView: OngoingLoader"+userPref.user.apiToken)
                }
                else if (selectedItem == "Completed") {
                    toast(requireContext(),"Completed")
                    viewModelPassengerCompleted.passengerCompletedBookingTripHistoryApi("Bearer " + userPref.user.apiToken)
                    Log.d(ContentValues.TAG, "onCreateView: "+userPref.user.apiToken)
                }
                else if (selectedItem == "Cancelled") {
                    toast(requireContext(),"Cancelled")
                    viewModelCancelledPassenger.passengerCancelledBookingTripHistoryApi("Bearer " + userPref.user.apiToken)
                    Log.d(ContentValues.TAG, "onCreateView: r"+userPref.user.apiToken)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        viewModelPassengerOngoing.getPassengerOngoingHistoryResponse.observe(requireActivity()) {
            if (it.status == 1) {
                listDataPassengerOngoing.clear()
                // listData!!.addAll(it.getFavLocdata)

                if (it.data.isEmpty() ) {
                    binding.idNouser.visibility = View.VISIBLE
                    binding.rvOngoing.visibility = View.GONE

                }
                else {
                    binding.idNouser.visibility = View.GONE
                    binding.rvOngoing.visibility = View.VISIBLE
                    listDataPassengerOngoing.addAll(it.data)
                    ongoingTripHistoryAdapter = OngoingPassengerTripHistoryAdapter(listDataPassengerOngoing,this@PassengerBookingHistoryFragment)
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
                binding.idNouser.visibility = View.VISIBLE
                binding.rvOngoing.visibility = View.GONE
            }
        }
        viewModelPassengerOngoing.passengerOngoingBookingTripHistoryApi("Bearer " + userPref.user.apiToken)

        viewModelPassengerCompleted.getPassengerCompletedHistoryResponse.observe(requireActivity()) {
            if (it.status == 1) {
                listDataPassengerCompleted.clear()
                // listData!!.addAll(it.getFavLocdata)

                if (it.data.isEmpty() ) {
                    binding.idNouser.visibility = View.VISIBLE
                    binding.rvOngoing.visibility = View.GONE


                }
                else {
                    binding.idNouser.visibility = View.GONE
                    binding.rvOngoing.visibility = View.VISIBLE
                    listDataPassengerCompleted.addAll(it.data)
                    completedPassengerTripHistoryAdapter = CompletedPassengerTripHistoryAdapter(listDataPassengerCompleted,this@PassengerBookingHistoryFragment)
                    binding.rvOngoing.apply {
                        adapter = completedPassengerTripHistoryAdapter
                        layoutManager = LinearLayoutManager(requireActivity())
                        // it.getFavLocdata?.let { notificationList?.addAll(it) }
                        //    favouriteLocationsAdapter?.notifyDataSetChanged()
                    }
                }

            } else   {
                Log.d("Response", it.toString())
                toast(requireContext(),it.message!!)
                binding.idNouser.visibility = View.VISIBLE
                binding.rvOngoing.visibility = View.GONE
            }
        }





    //    viewModelPassengerCompleted.passengerCompletedBookingTripHistoryApi("Bearer " + userPref.user.apiToken)
        Log.d(ContentValues.TAG, "onCreateView: "+userPref.user.apiToken)








        viewModelCancelledPassenger.getPassengerCancelledHistoryResponse.observe(requireActivity()) {
            if (it.status == 1) {
                listDataCancelledPassenger.clear()
                // listData!!.addAll(it.getFavLocdata)

                if (it.data.isEmpty() ) {
                    binding.idNouser.visibility = View.VISIBLE
                    binding.rvOngoing.visibility = View.GONE

                }
                else {
                    binding.idNouser.visibility = View.GONE
                    binding.rvOngoing.visibility = View.VISIBLE
                    listDataCancelledPassenger.addAll(it.data)
                    cancelledPassengerTripHistoryAdapter = CancelledPassengerTripHistoryAdapter(listDataCancelledPassenger,this@PassengerBookingHistoryFragment)
                    binding.rvOngoing.apply {
                        adapter = cancelledPassengerTripHistoryAdapter
                        layoutManager = LinearLayoutManager(requireActivity())
                        // it.getFavLocdata?.let { notificationList?.addAll(it) }
                        //    favouriteLocationsAdapter?.notifyDataSetChanged()
                    }
                }
            } else   {
                Log.d("Response", it.toString())
                toast(requireContext(),it.message!!)
                binding.idNouser.visibility = View.VISIBLE
                binding.rvOngoing.visibility = View.GONE
            }
        }



        return binding.root
    }

    /*private fun setTab() {
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Ongoing"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Completed"))

        passengerpagerAdapter = PassengerBookingHistoryViewPagerAdapter(getParentFragmentManager())
        binding.viewPager.adapter = passengerpagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)


        binding.tabLayout.getChildAt(0)

        binding.viewPager.adapter = passengerpagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }*/



    override fun onDetailClicked(ongoingPassengerHistoryData: OngoingPassengerHistoryData) {
          startActivity(Intent(requireContext(), PassengerOngoingBookingDetailsActivity :: class.java).also {
             it.putExtra("passengerOngoingHistoryDetails", ongoingPassengerHistoryData)

         })

        Log.d("TAG++", "onProceedClicked: "+ongoingPassengerHistoryData.bookingId.toString())
    }




    override fun onCompletedDetailClicked(completedPassengerHistoryData: CompletedPassengerHistoryData) {
        startActivity(Intent(requireContext(), PassengerCompletedBookingDetailsActivity :: class.java).also {
             it.putExtra("passengerCompletedHistoryDetails", completedPassengerHistoryData)

         })

        Log.d("TAG++", "onProceedClicked: "+completedPassengerHistoryData.bookingId.toString())
    }

    override fun onCancelledPassDetailClicked(cancelledPassengerTripHistoryData: CancelledPassengerTripHistoryData) {
        startActivity(Intent(requireContext(), PassengerCancelledBookingDetailsActivity :: class.java).also {
            it.putExtra("passengerCancelledHistoryDetails", cancelledPassengerTripHistoryData)

        })
    }


}
