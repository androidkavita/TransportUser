package com.govahan.com.fragment.passengerinvoices

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
import com.govahan.com.activities.invoicesummarypassenger.PassengerInvoiceSummaryActivity
import com.govahan.com.adapters.PassengerInvoiceListAdapter
import com.govahan.com.baseClasses.BaseFragment
import com.govahan.com.databinding.FragmentPassengerInvoiceListBinding
import com.govahan.com.model.passengerinvoicelistmodel.PassengerInvoiceData
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class PassengerInvoiceListFragment : BaseFragment(), PassengerInvoiceListAdapter.OnClick {

    private lateinit var binding : FragmentPassengerInvoiceListBinding
    private val viewModel : PassengerInvoiceListFragmentViewModel by viewModels()
    private var passengerInvoiceListAdapter : PassengerInvoiceListAdapter?= null



    private var listData: ArrayList<PassengerInvoiceData> = ArrayList()



    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_passenger_invoice_list, container, false)




        viewModel.progressBarStatus.observe(requireActivity()) {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        }

        viewModel.getPassengerInvoiceListResponse.observe(requireActivity()) {
            if (it.status == 1) {
                listData.clear()
                if (it.data.isEmpty() ) {
                    binding.idNouser.visibility = View.VISIBLE
                    binding.rvInvoicelist.visibility = View.GONE
                }
                else {
                    binding.idNouser.visibility = View.GONE
                    binding.rvInvoicelist.visibility = View.VISIBLE
                    listData.addAll(it.data)
                    passengerInvoiceListAdapter = PassengerInvoiceListAdapter(listData,this)
                    binding.rvInvoicelist.apply {
                        adapter = passengerInvoiceListAdapter
                        layoutManager = LinearLayoutManager(requireContext())
                    }


                }
            } else   {
                Log.d("Response", it.toString())
                toast(requireContext(),it.message!!)
            }
        }
        viewModel.passengerInvoiceListApi("Bearer " + userPref.user.apiToken)

        Log.d(TAG, "onCreateView: kbmb" +userPref.user.apiToken)



        return binding.root
    }

    override fun onInvoiceClicked(passengerInvoiceData: PassengerInvoiceData) {
        startActivity(Intent(requireContext(), PassengerInvoiceSummaryActivity :: class.java).also {
            it.putExtra("loaderInvoiceDetails", passengerInvoiceData.invoiceNumber.toString())
            it.putExtra("passengerInvoiceBookingId", passengerInvoiceData.bookingId.toString())
        })
        Log.d("TAG++", "onProceedClicked: "+passengerInvoiceData.invoiceNumber.toString())
    }


}