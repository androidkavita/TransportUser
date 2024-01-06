package com.govahan.com.activities.myprofile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.govahan.com.R
import com.govahan.com.activities.editprofile.EditProfileActivity
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityMyProfileBinding
import com.govahan.com.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class
MyProfileActivity : BaseActivity() {
    private lateinit var binding : ActivityMyProfileBinding
    private val viewModel : MyProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_profile)

        binding.ivBack.setOnClickListener(View.OnClickListener {
            finish()
        })

        binding.btnUpdate.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        })

        viewModel.userprofileResponse.observe(this) {
            Log.d("Response", it.toString())
            if (it.status == 1) {
                binding.tvFullname.text =  it.data!!.name
                binding.tvPhone.text =  it.data!!.mobileNumber
                binding.tvEmail.text =  it.data!!.email
                binding.tvAddress.text =  it.data!!.address
                Glide.with(this).load(it.data!!.profileImage)
                    .apply(RequestOptions.placeholderOf(R.drawable.user_image_place_holder))
                    .apply(RequestOptions.errorOf(R.drawable.user_image_place_holder))
                    .into(binding.ivDriver)


                userPref.setToken(it.data!!.deviceToken)
                userPref.setSubUserName(it.data!!.name)
                userPref.setEmail(it.data!!.email)
                userPref.setMobile(it.data!!.mobileNumber)
                userPref.setAddress(it.data!!.address)

                userPref.setSubUserId(it.data!!.id.toString())
                if (it.data!!.profileImage != null) {
                    userPref.setProfileImage(it.data!!.profileImage)
                    Log.e("@@image", userPref.getUserProfileImage().toString())
                }else{
                    userPref.setProfileImage("")
                }


//                binding.tvTotalLoads.text =  it.data.to
//                binding.tvUsername.text =  it.data.name

            } else {
                it.message?.let { it1 -> toast(it1) }
            }
        }

        viewModel.progressBarStatus.observe(this) {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        }


    }  override fun onResume() {
        super.onResume()
        viewModel.userProfileApi("Bearer " + userPref.user.apiToken)
    }


}