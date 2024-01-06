package com.govahan.com.activities.writereview

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.govahan.com.R
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityWriteAreviewBinding
import com.govahan.com.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WriteAreviewActivity : BaseActivity() {
    private lateinit var binding : ActivityWriteAreviewBinding
    var getRating = " "
   private val viewModel : WriteAReviewViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_write_areview)

        binding.ivBack.setOnClickListener(View.OnClickListener {
            finish()
        })

        getRating = intent.getStringExtra("putRating").toString()
      //  toast(getRating)
        binding.rating.setRating(getRating.toFloat());

        binding.btnSubmit.setOnClickListener {

                viewModel.addRatingApi("Bearer " + userPref.user.apiToken ,
                    userPref.getDriverId().toString(),
                    getRating,
                    binding.etFeedback.text.trim().toString()
                    )
        }

        viewModel.progressBarStatus.observe(this, androidx.lifecycle.Observer {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        })

        viewModel.addRatingResponse.observe(this, androidx.lifecycle.Observer {
            if (it.status == 1) {

                toast(it.message!!)
                finish()
            } else {
                hideProgressDialog()
                toast(it.message!!)
            }
        })


    }
}