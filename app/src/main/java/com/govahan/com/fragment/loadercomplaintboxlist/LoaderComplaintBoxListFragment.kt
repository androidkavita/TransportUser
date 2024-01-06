package com.govahan.com.fragment.loadercomplaintboxlist

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.govahan.com.R
import com.govahan.com.activities.loadercomplaintboxdetail.LoaderComplaintBoxDetailActivity
import com.govahan.com.adapters.LoaderComplaintBoxAdapter
import com.govahan.com.baseClasses.BaseFragment
import com.govahan.com.databinding.ActivityLoaderComplaintBoxListBinding
import com.govahan.com.model.loaderComplaintlistmodel.LoaderComplaintData
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class LoaderComplaintBoxListFragment : BaseFragment(), LoaderComplaintBoxAdapter.OnClick {

    private lateinit var binding : ActivityLoaderComplaintBoxListBinding
    private val viewModel : LoaderComplaintBoxListViewModel by viewModels()



    private var loaderComplaintBoxAdapter : LoaderComplaintBoxAdapter ?= null
    private var listData: ArrayList<LoaderComplaintData> = ArrayList()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
       // binding = DataBindingUtil.setContentView(requireActivity(), R.layout.activity_loader_complaint_box_list)
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_loader_complaint_box_list, container, false)

        viewModel.progressBarStatus.observe(requireActivity()) {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        }
        viewModel.getLoaderComplaintListResponse.observe(requireActivity()) {
            /*if (it.status == 1) {
                loaderComplaintBoxAdapter =  LoaderComplaintBoxAdapter(it.data,
                    this)

                binding.rvComplaintBoxList.apply {
                    adapter = loaderComplaintBoxAdapter
                    layoutManager = LinearLayoutManager(requireContext())
                }
*/






            if (it.status == 1) {
                listData.clear()
                // listData!!.addAll(it.getFavLocdata)

                if (it.data.isEmpty() ) {
                    binding.idNouser.visibility = View.VISIBLE
                    binding.rvComplaintBoxList.visibility = View.GONE

                }
                else {
                    binding.idNouser.visibility = View.GONE
                    binding.rvComplaintBoxList.visibility = View.VISIBLE
                    listData.addAll(it.data)
                    loaderComplaintBoxAdapter = LoaderComplaintBoxAdapter(listData,this)
                    binding.rvComplaintBoxList.apply {
                        adapter = loaderComplaintBoxAdapter
                        layoutManager = LinearLayoutManager(requireActivity())
                        // it.getFavLocdata?.let { notificationList?.addAll(it) }
                        //    favouriteLocationsAdapter?.notifyDataSetChanged()
                    }
                }











            } else {
                Log.d("Response", it.toString())
                toast(requireContext(),it.message!!)
            }
        }

        viewModel.loaderComplaintListApi("Bearer " + userPref.user.apiToken)


        return binding.root
    }


    override fun onViewDetail(loaderComplaintData: LoaderComplaintData) {
        startActivity(Intent(requireContext(), LoaderComplaintBoxDetailActivity::class.java).also {
            it.putExtra("vehicleDetails", loaderComplaintData)

        })
    }

}