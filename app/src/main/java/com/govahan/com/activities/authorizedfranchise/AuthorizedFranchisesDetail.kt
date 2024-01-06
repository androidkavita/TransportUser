package com.govahan.com.activities.authorizedfranchise

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.govahan.com.R
import com.govahan.com.adapters.AuthorizedFranchiseDetailAdapter
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityAuthorizedFranchisesDetailBinding
import com.govahan.com.model.PostImage
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class AuthorizedFranchisesDetail : BaseActivity() {
    lateinit var binding: ActivityAuthorizedFranchisesDetailBinding
    private val viewModel : AuthorizedFranchisesViewModel by viewModels()
    private var authorizedFranchiseAdapter : AuthorizedFranchiseDetailAdapter?= null
    private var listData: ArrayList<PostImage> = ArrayList()
    var id=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_authorized_franchises_detail)

        binding.header.ivBack.setOnClickListener(View.OnClickListener {
            finish()
        })
         if (intent!=null){
             id=intent.getStringExtra("id").toString()
         }
        Log.d("TAG", "onCreate: "+id)
        binding.header.tvHeaderText.setText("Details")

        viewModel.progressBarStatus.observe(this) {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        }
        viewModel.AuthorisedFranchisesDetailResponse.observe(this) {
            if (it.status == 1) {
                listData.clear()
                binding.tvOwnerName.text=it.data.name
                binding.tvPhn.text=it.data.mobile_number
                binding.tvEmail.text=it.data.email
                binding.address.text=it.data.address
                binding.count.text=it.data.vehicle_count.toString()
                    listData.addAll(it.data.post_image)
                    binding.rvSearchAuthorizedFranchise.layoutManager = LinearLayoutManager(this)
                    authorizedFranchiseAdapter = AuthorizedFranchiseDetailAdapter(this, listData)
                    binding.rvSearchAuthorizedFranchise.adapter =authorizedFranchiseAdapter

            } else   {
                Log.d("Response", it.toString())
                toast(this,it.message!!)
            }
        }
        viewModel.vendor_number_vehicle_list("Bearer " + userPref.user.apiToken,id)

    }
}