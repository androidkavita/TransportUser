package com.govahan.com.fragment.passengercomplaintboxlist

import android.content.ContentValues.TAG
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
import com.govahan.com.activities.passengercomplaintboxdetail.PassengerComplaintBoxDetailActivity
import com.govahan.com.adapters.PassengerComplaintBoxAdapter
import com.govahan.com.baseClasses.BaseFragment
import com.govahan.com.databinding.ActivityPassengerComplaintBoxListBinding
import com.govahan.com.model.passengerComplaintlistmodel.PassengerComplaintData
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class PassengerComplaintBoxListFragment : BaseFragment(),PassengerComplaintBoxAdapter.OnClick {


        private lateinit var binding : ActivityPassengerComplaintBoxListBinding
        private val viewModel : PassengerComplaintBoxListViewModel by viewModels()



        private var passengerComplaintBoxAdapter : PassengerComplaintBoxAdapter?= null
    private var listData: ArrayList<PassengerComplaintData> = ArrayList()


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_passenger_complaint_box_list, container, false)

            viewModel.progressBarStatus.observe(requireActivity()) {
                if (it) {
                    showProgressDialog()
                } else {
                    hideProgressDialog()
                }
            }



            viewModel.getPassengerComplaintListResponse.observe(requireActivity()) {
               /* if (it.status == 1) {
                    passengerComplaintBoxAdapter =  PassengerComplaintBoxAdapter(it.data,
                        this)

                    binding.rvComplaintBoxList.apply {
                        adapter = passengerComplaintBoxAdapter
                        layoutManager = LinearLayoutManager(requireContext())
                    }*/


                if (it.status == 1) {
                    listData.clear()
                    // listData!!.addAll(it.getFavLocdata)

                    if (it.data.isEmpty() ) {
                        binding.idNouser.visibility = View.VISIBLE
                        binding.rvComplaintBoxList.visibility = View.GONE
                        toast(requireContext(),"1")

                    }
                    else {
                        binding.idNouser.visibility = View.GONE
                        binding.rvComplaintBoxList.visibility = View.VISIBLE
                        listData.addAll(it.data)
                        passengerComplaintBoxAdapter = PassengerComplaintBoxAdapter(listData,this)
                        binding.rvComplaintBoxList.apply {
                            adapter = passengerComplaintBoxAdapter
                            layoutManager = LinearLayoutManager(requireActivity())
                            // it.getFavLocdata?.let { notificationList?.addAll(it) }
                            //    favouriteLocationsAdapter?.notifyDataSetChanged()


                            toast(requireContext(),"2")
                        }
                        toast(requireContext(),"3")
                    }


                } else {
                    Log.d("Response", it.toString())
                    toast(requireContext(),it.message!!)
                }
            }

           viewModel.passengerComplaintListApi("Bearer " + userPref.user.apiToken)
        Log.d(TAG, "PgjghP: "+userPref.user.apiToken)

        return binding.root

        }


        override fun onViewDetail(passengerComplaintData: PassengerComplaintData) {
            startActivity(Intent(requireContext(), PassengerComplaintBoxDetailActivity::class.java).also {
                it.putExtra("passengerComplaintData", passengerComplaintData)

            })
        }

    }