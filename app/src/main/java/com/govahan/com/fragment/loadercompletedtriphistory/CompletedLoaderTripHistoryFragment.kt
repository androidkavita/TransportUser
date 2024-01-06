package com.govahan.com.fragment.loadercompletedtriphistory

import android.content.ContentValues.TAG
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
import com.govahan.com.activities.loadercompletedbookingdetails.LoaderCompletedBookingDetailsActivity
import com.govahan.com.adapters.CompletedLoaderTripHistoryAdapter
import com.govahan.com.baseClasses.BaseFragment
import com.govahan.com.databinding.FragmentCompletedTripHistoryBinding
import com.govahan.com.model.completedloadertriphistorymodel.CompletedLoaderHistoryData
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class CompletedLoaderTripHistoryFragment : BaseFragment() , CompletedLoaderTripHistoryAdapter.OnClick {
    private lateinit var binding : FragmentCompletedTripHistoryBinding
    val progressBarStatus = MutableLiveData<Boolean>()
    private val viewModel : LoaderCompletedTripHistoryFragmentViewModel by viewModels()
    private var completedTripHistoryAdapter : CompletedLoaderTripHistoryAdapter?= null
    private var listData: ArrayList<CompletedLoaderHistoryData> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_completed_trip_history, container, false)




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

        viewModel.getLoaderCompletedHistoryResponse.observe(requireActivity()) {
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
                    completedTripHistoryAdapter = CompletedLoaderTripHistoryAdapter(listData,this@CompletedLoaderTripHistoryFragment)
                    binding.rvCompleted.apply {
                        adapter = completedTripHistoryAdapter
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





        viewModel.loaderCompletedBookingTripHistoryApi("Bearer " + userPref.user.apiToken)
        Log.d(TAG, "onCreateView: "+userPref.user.apiToken)



        return binding.root
    }



    override fun onCompletedDetailClicked(completedLoaderHistoryData: CompletedLoaderHistoryData) {
        startActivity(Intent(requireContext(), LoaderCompletedBookingDetailsActivity :: class.java).also {
            it.putExtra("loaderCompletedHistoryDetails", completedLoaderHistoryData)

        })

        Log.d("TAG++", "onProceedClicked: "+completedLoaderHistoryData.bookingId.toString())
    }


}