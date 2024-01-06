package com.govahan.com.fragment.loaderongoingtriphistory

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
import com.govahan.com.activities.loaderongoingbookingdetails.LoaderOngoingBookingDetailsActivity
import com.govahan.com.adapters.OngoingLoaderTripHistoryAdapter
import com.govahan.com.baseClasses.BaseFragment
import com.govahan.com.databinding.FragmentOngoingTripHistoryBinding
import com.govahan.com.model.ongoingloadertriphistorymodel.OngoingLoaderHistoryData
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class OngoingLoaderTripHistoryFragment : BaseFragment() , OngoingLoaderTripHistoryAdapter.OnClick{

    private lateinit var binding : FragmentOngoingTripHistoryBinding
    val progressBarStatus = MutableLiveData<Boolean>()
   private val viewModel : LoaderOngoingTripHistoryFragmentViewModel by viewModels()
    private var ongoingTripHistoryAdapter : OngoingLoaderTripHistoryAdapter?= null
    private var listData: ArrayList<OngoingLoaderHistoryData> = ArrayList()





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ongoing_trip_history, container, false)

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

        viewModel.getLoaderOngoingHistoryResponse.observe(requireActivity()) {
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
                    ongoingTripHistoryAdapter = OngoingLoaderTripHistoryAdapter(listData,this@OngoingLoaderTripHistoryFragment)
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





        viewModel.loaderOngoingBookingTripHistoryApi("Bearer " + userPref.user.apiToken)
        Log.d(TAG, "onCreateView: OngoingLoader"+userPref.user.apiToken)









        return binding.root    }






    override fun onDetailClicked(ongoingLoaderHistoryData: OngoingLoaderHistoryData) {
        startActivity(Intent(requireContext(), LoaderOngoingBookingDetailsActivity :: class.java).also {
            it.putExtra("loaderOngoingHistoryDetails", ongoingLoaderHistoryData)

        })

       Log.d("TAG++", "onProceedClicked: "+ongoingLoaderHistoryData.bookingId.toString())
    }


}