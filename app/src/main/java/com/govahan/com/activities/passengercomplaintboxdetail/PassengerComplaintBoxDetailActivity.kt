package com.govahan.com.activities.passengercomplaintboxdetail

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.govahan.com.R
import com.govahan.com.activities.loadercomplaintboxdetail.LoaderComplaintBoxDetailViewModel
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityPassengerComplaintBoxDetailBinding
import com.govahan.com.model.passengerComplaintlistmodel.PassengerComplaintData
import com.govahan.com.util.toast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PassengerComplaintBoxDetailActivity : BaseActivity() {

    private lateinit var binding : ActivityPassengerComplaintBoxDetailBinding
    private val viewModel : PassengerComplaintBoxDetailViewModel by viewModels()
    private var passengerComplaintData : PassengerComplaintData?= null
    private val viewModel1 : LoaderComplaintBoxDetailViewModel by viewModels()

    //activity_passenger_complaint_box_detail



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_passenger_complaint_box_detail)

        binding.header.ivBack.setOnClickListener(View.OnClickListener {
            finish()
        })

        binding.header.tvHeaderText.setText("Complaint Detail")


        /*val layoutManagerTopSongs: RecyclerView.LayoutManager = LinearLayoutManager(this,
            GridLayoutManager.VERTICAL, false)
        binding.rvComplaintBox.setLayoutManager(layoutManagerTopSongs)
        val itemsTopSongs: List<String> = Arrays.asList("item", "item", "item", "item", "item", "item", "item", "item", "item", "item")
        binding.rvComplaintBox.setAdapter(ComplaintBoxAdapter(this, itemsTopSongs))

*/



        val data = intent.extras
        passengerComplaintData = data?.getParcelable<PassengerComplaintData>("passengerComplaintData")

        viewModel.progressBarStatus.observe(this) {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        }

        viewModel.passengerComplaintListDetailResponse.observe(this) {
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

                binding.tvComplaintText.text = it.data[0].comMessage
                if (it.data[0].confirmation==1){
                    binding.tvAdminRemarks.text = "Resolved"
                }else if (it.data[0].confirmation==2){
                    binding.tvAdminRemarks.text = "Not Resolved"
                }
                else{
                    binding.tvAdminRemarks.text = "Pending"
                }

                if(it.data[0].paymentMode.equals("1"))
                {binding.tvPaymentMethod.setText("Cash")}
                else  if(it.data[0].paymentMode.equals("2"))
                {binding.tvPaymentMethod.setText("Online")}

                /*if(it.data[0].adminRemarks.equals(" "))
                { binding.btnResolved.visibility = View.GONE }
                else
                {binding.btnResolved.visibility = View.VISIBLE}*/





            } else {
                toast(it.message!!)
            }
        }




        viewModel.passengerComplaintListDetailApi("Bearer " + userPref.user.apiToken,
            passengerComplaintData?.bookingId.toString()
        )


        viewModel1.resolvedResponse.observe(this){
            if (it.status==1){
                toast(it.message!!)
            }else{
                toast(it.message!!)
            }
        }

        binding.btnResolved.setOnClickListener {
            viewModel1.complaint_resolved("Bearer " + userPref.user.apiToken,
                passengerComplaintData?.bookingId.toString()  ,"1")
        }
        binding.btnNotResolved.setOnClickListener {
            viewModel1.complaint_resolved("Bearer " + userPref.user.apiToken,
                passengerComplaintData?.bookingId.toString()  ,"2")
        }


    }
}