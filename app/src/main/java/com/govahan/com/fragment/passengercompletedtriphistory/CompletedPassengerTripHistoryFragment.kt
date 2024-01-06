package com.govahan.com.fragment.passengercompletedtriphistory

import android.content.ContentValues
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
import com.govahan.com.adapters.CompletedPassengerTripHistoryAdapter
import com.govahan.com.baseClasses.BaseFragment
import com.govahan.com.databinding.FragmentCompletedPassengerTripHistoryBinding
import com.govahan.com.model.completedpassengertriphistorymodel.CompletedPassengerHistoryData
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class CompletedPassengerTripHistoryFragment : BaseFragment(), CompletedPassengerTripHistoryAdapter.OnClick {

    private lateinit var binding : FragmentCompletedPassengerTripHistoryBinding
    val progressBarStatus = MutableLiveData<Boolean>()
    private val viewModel : PassengerCompletedTripHistoryFragmentViewModel by viewModels()
    private var completedPassengerTripHistoryAdapter : CompletedPassengerTripHistoryAdapter?= null
    private var listData: ArrayList<CompletedPassengerHistoryData> = ArrayList()





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_completed_passenger_trip_history, container, false)




        /*val layoutManagerTopSongs: RecyclerView.LayoutManager = LinearLayoutManager(requireContext(),
            GridLayoutManager.VERTICAL, false)
        binding.rvCompleted.setLayoutManager(layoutManagerTopSongs)
        val itemsTopSongs: List<String> = Arrays.asList("item", "item", "item", "item", "item", "item", "item", "item", "item", "item")
        binding.rvCompleted.setAdapter(CompletedLoaderTripHistoryAdapter(requireContext(), itemsTopSongs))
*/


        viewModel.progressBarStatus.observe(requireActivity()) {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        }

        viewModel.getPassengerCompletedHistoryResponse.observe(requireActivity()) {
            if (it.status == 1) {
                listData.clear()
                // listData!!.addAll(it.getFavLocdata)

                if (it.data.isEmpty() ) {
                    binding.idNouser.visibility = View.VISIBLE
                    binding.rvCompleted.visibility = View.GONE

                }
                else {
                    binding.idNouser.visibility = View.GONE
                    binding.rvCompleted.visibility = View.VISIBLE
                    listData.addAll(it.data)
                    completedPassengerTripHistoryAdapter = CompletedPassengerTripHistoryAdapter(listData,this@CompletedPassengerTripHistoryFragment)
                    binding.rvCompleted.apply {
                        adapter = completedPassengerTripHistoryAdapter
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





        viewModel.passengerCompletedBookingTripHistoryApi("Bearer " + userPref.user.apiToken)
        Log.d(ContentValues.TAG, "onCreateView: "+userPref.user.apiToken)



        return binding.root
    }



    override fun onCompletedDetailClicked(completedPassengerHistoryData: CompletedPassengerHistoryData) {
       /* startActivity(Intent(requireContext(), PassengerCompletedBookingDetailsActivity :: class.java).also {
            it.putExtra("passengerCompletedHistoryDetails", completedPassengerHistoryData)

        })*/

        Log.d("TAG++", "onProceedClicked: "+completedPassengerHistoryData.bookingId.toString())
    }


}