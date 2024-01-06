package com.govahan.com.activities.loadercomplaintboxdetail

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.govahan.com.R
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityLoaderComplaintBoxDetailBinding
import com.govahan.com.model.loaderComplaintlistmodel.LoaderComplaintData
import com.govahan.com.util.toast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoaderComplaintBoxDetailActivity : BaseActivity() {
    private lateinit var binding : ActivityLoaderComplaintBoxDetailBinding
    private val viewModel : LoaderComplaintBoxDetailViewModel by viewModels()
    private var loaderComplaintData : LoaderComplaintData?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_loader_complaint_box_detail)

        binding.header.ivBack.setOnClickListener(View.OnClickListener {
            finish()
        })

        binding.header.tvHeaderText.setText("Complaint Detail")






        val data = intent.extras
        loaderComplaintData = data?.getParcelable<LoaderComplaintData>("vehicleDetails")

        viewModel.progressBarStatus.observe(this) {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        }

        viewModel.loaderComplaintListDetailResponse.observe(this) {
            if (it.status == 1) {
                // toast("booking Successful")
                toast(it.message!!)
                binding.tvCompalintNumber.text = it.data[0].comMessage
                binding.tvDate.text =  it.data[0].createdAt
                binding.tvBookingid.text =  it.data[0].bookingId
                binding.tvFrom.text = it.data[0].picupLocation
                binding.tvTo.text = it.data[0].dropLocation
                binding.tvTotalfare.text ="₹" + it.data[0].fare

                binding.tvBookingDate.text = it.data[0].bookingDate
                binding.tvBookingTime.text = it.data[0].bookingTime
                binding.tvVehicleType.text = it.data[0].vehicleNumbers
                binding.tvBodyType.text = it.data[0].bodyType
                binding.tvVehicleNumber.text = it.data[0].vehicleNumbers
                binding.tvAdminRemarks.text = it.data[0].adminReply
//                if (it.data[0].confirmation==1){
//                    binding.tvAdminRemarks.text = "Resolved"
//                }else if (it.data[0].confirmation==2){
//                    binding.tvAdminRemarks.text = "Not Resolved"
//                }
//                else{
//                    binding.tvAdminRemarks.text = "Pending"
//                }


                binding.tvComplaintText.text = it.data[0].comMessage
                binding.tvPaymentMethod.text = it.data[0].paymentMode


            } else {
                toast(it.message!!)
            }
        }


      viewModel.resolvedResponse.observe(this){
          if (it.status==1){
              toast(it.message!!)
          }else{
              toast(it.message!!)
          }
      }

        viewModel.loaderComplaintListDetailApi("Bearer " + userPref.user.apiToken,
            loaderComplaintData?.bookingId.toString()
        )


        binding.btnResolved.setOnClickListener {
            viewModel.complaint_resolved("Bearer " + userPref.user.apiToken,
                loaderComplaintData?.bookingId.toString()  ,"1")
        }
        binding.btnNotResolved.setOnClickListener {
            viewModel.complaint_resolved("Bearer " + userPref.user.apiToken,
                loaderComplaintData?.bookingId.toString()  ,"2")
        }



    }
}