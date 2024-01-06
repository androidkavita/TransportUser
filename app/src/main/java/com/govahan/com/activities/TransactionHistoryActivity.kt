package com.govahan.com.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.govahan.com.R
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityTransactionHistoryBinding
import com.govahan.com.fragment.loadercomplaintboxlist.LoaderComplaintBoxListViewModel
import com.govahan.com.util.toast
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class TransactionHistoryActivity : BaseActivity() {
    private val viewModel: LoaderComplaintBoxListViewModel by viewModels()
    private lateinit var binding : ActivityTransactionHistoryBinding
    private var adapter : TransactionAdapter?= null
    private var listData: ArrayList<TransactionReportData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_transaction_history)

        binding.tvHeaderText.setText("Transaction History")
        viewModel.user_online_transaction_historyApi("Bearer " + userPref.user.apiToken)
        viewModel.transactionreportResponse.observe(this) {
            if (it.status == 1) {
                listData.clear()

                    listData.addAll(it.data)
                    adapter = TransactionAdapter(listData)
                binding.rvTransaction.adapter = adapter
                adapter!!.notifyDataSetChanged()



            } else   {
                Log.d("Response", it.toString())
                toast(it.message!!)
            }
        }
        viewModel.progressBarStatus.observe(this) {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        }
    }
}