package com.govahan.com.fragment.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.govahan.com.R
import com.govahan.com.activities.authorizedfranchise.AuthorizedFranchisesActivity
import com.govahan.com.activities.bookvehicle.BookAVehicleActivity
import com.govahan.com.activities.complaintboxlist.ComplaintBoxListActivity
import com.govahan.com.activities.invoicelist.InvoiceListActivity
import com.govahan.com.activities.mainBookingHistory.MainBookingHistoryActivity
import com.govahan.com.activities.tripmanagement.TripManagementActivity
import com.govahan.com.adapters.SliderAdapter
import com.govahan.com.baseClasses.BaseFragment
import com.govahan.com.databinding.FragmentHomeBinding
import com.govahan.com.model.homebannermodel.HomeBannerData
import com.smarteist.autoimageslider.SliderView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment() {
    val sliderDataArrayList: ArrayList<HomeBannerData> = ArrayList()
    lateinit var binding : FragmentHomeBinding
    private val viewModel : HomeFragmentViewModel by viewModels()
    private var adapter: SliderAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)



        binding.llBookvehicle.setOnClickListener(View.OnClickListener {
            val intent = Intent(requireContext(), BookAVehicleActivity::class.java)
            startActivity(intent)
        })

        binding.llTripmanagement.setOnClickListener(View.OnClickListener {
            val intent = Intent(requireContext(), TripManagementActivity::class.java)
            startActivity(intent)
        })

        binding.llBookinghistory.setOnClickListener(View.OnClickListener {
           // val intent = Intent(requireContext(), LoaderBookingHistoryActivity::class.java)
            val intent = Intent(requireContext(), MainBookingHistoryActivity::class.java)
            startActivity(intent)
        })

        binding.llInvoices.setOnClickListener(View.OnClickListener {
            val intent = Intent(requireContext(), InvoiceListActivity::class.java)
            startActivity(intent)
        })

        binding.llFranchise.setOnClickListener(View.OnClickListener {
            val intent = Intent(requireContext(), AuthorizedFranchisesActivity::class.java)
            startActivity(intent)
        })

        binding.llComplaint.setOnClickListener(View.OnClickListener {
            val intent = Intent(requireContext(), ComplaintBoxListActivity::class.java)
            startActivity(intent)
        })

















        /*viewModel.progressBarStatus.observe(requireActivity()) {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        }*/

            viewModel.homeBannerResponseModel.observe(requireActivity()) {
                if (it.status == 1) {
                    sliderDataArrayList.clear()
                    //  listData.clear()

                    for (i in 0 until it.data.size){
                        sliderDataArrayList.add(it.data[i])
                    }
                    binding.imageSlider.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR
                    binding.imageSlider.scrollTimeInSec = 3
                    binding.imageSlider.isAutoCycle = true
                    binding.imageSlider.startAutoCycle()
                    // adapter = SliderAdapter(requireContext(), response.data.imagesAll)
                    adapter = SliderAdapter(sliderDataArrayList)
                    binding.imageSlider.setSliderAdapter(adapter!!)
                        /*listData.addAll(it.data)
                        authorizedFranchiseAdapter = AuthorizedFranchiseAdapter(listData)
                        binding.rvAuthorizedFranchise.apply {
                            adapter = authorizedFranchiseAdapter
                            layoutManager = LinearLayoutManager(this@AuthorizedFranchisesActivity)
                        }*/

                } else   {
                    Log.d("Response", it.toString())
                    toast(requireContext(),it.message!!)
                }
            }
            viewModel.getDashboardBannertApi("Bearer " + userPref.user.apiToken)













        return binding.root
    }



}