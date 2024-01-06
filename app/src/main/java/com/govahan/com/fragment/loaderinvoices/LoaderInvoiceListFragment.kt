package com.govahan.com.fragment.loaderinvoices

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
import com.govahan.com.activities.invoicesummaryloader.LoaderInvoiceSummaryActivity
import com.govahan.com.adapters.LoaderInvoiceListAdapter
import com.govahan.com.baseClasses.BaseFragment
import com.govahan.com.databinding.FragmentLoaderInvoiceListBinding
import com.govahan.com.model.loaderinvoicelistmodel.LoaderInvoiceData
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class LoaderInvoiceListFragment : BaseFragment(), LoaderInvoiceListAdapter.OnClick  {

    private lateinit var binding : FragmentLoaderInvoiceListBinding
    private val viewModel : LoaderInvoiceListFragmentViewModel by viewModels()
    private var loaderInvoiceListAdapter : LoaderInvoiceListAdapter?= null
    private var listData: ArrayList<LoaderInvoiceData> = ArrayList()


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_loader_invoice_list, container, false)


        viewModel.progressBarStatus.observe(requireActivity()) {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        }

        viewModel.getLoaderInvoiceListResponse.observe(requireActivity()) {
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
                    loaderInvoiceListAdapter = LoaderInvoiceListAdapter(listData,this)
                    binding.rvInvoicelist.apply {
                        adapter = loaderInvoiceListAdapter
                        layoutManager = LinearLayoutManager(requireContext())
                    }
                }
            } else   {
                Log.d("Response", it.toString())
                toast(requireContext(),it.message!!)
            }
        }
        viewModel.loaderInvoiceListApi("Bearer " + userPref.user.apiToken)

        return binding.root
    }

    override fun onInvoiceClicked(loaderInvoiceData: LoaderInvoiceData) {
        startActivity(Intent(requireContext(), LoaderInvoiceSummaryActivity :: class.java).also {
            it.putExtra("loaderInvoiceDetails", loaderInvoiceData.invoiceNumber.toString())
            it.putExtra("loaderInvoiceBookingId", loaderInvoiceData.bookingId.toString())

        })

        Log.d("TAG++", "onProceedClicked: "+loaderInvoiceData.invoiceNumber.toString())

    }


}