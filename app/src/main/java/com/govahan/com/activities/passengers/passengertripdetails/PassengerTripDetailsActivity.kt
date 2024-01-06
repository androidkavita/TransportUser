package com.govahan.com.activities.passengers.passengertripdetails

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.govahan.com.R
import com.govahan.com.activities.passengers.passengerRideCompleted.PassengerRideCompletedActivity
import com.govahan.com.adapters.PassengerCancelTripReasonAdapter
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityPassengerTripDetailsBinding
import com.govahan.com.model.passengercancelreasonmodel.PassengerCancelReasonData
import com.govahan.com.model.tripmanagementpassengermodel.PassengerTripManagementData
import com.govahan.com.util.toast
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class PassengerTripDetailsActivity : BaseActivity(), OnMapReadyCallback {


    private lateinit var binding: ActivityPassengerTripDetailsBinding
    private var selectedPassengerTripData: PassengerTripManagementData? = null
    private val viewModel: PassengerTripDetailsViewModel by viewModels()
    private var listData: ArrayList<PassengerCancelReasonData> = ArrayList()
    private var passengerCancelReasonAdapter: PassengerCancelTripReasonAdapter? = null
    private var listReasonType_id:ArrayList<String> = ArrayList()
    var reasontypevalue_id: String? = ""
    private lateinit var str: String
    var selectedDateFormat2 = ""
    var time = ""
    val mcurrentTime = Calendar.getInstance()
    var hour = mcurrentTime[Calendar.HOUR_OF_DAY]
    val minute = mcurrentTime[Calendar.MINUTE]
    val zone = mcurrentTime[Calendar.AM_PM]

    var crnNumber = ""
    var callDriverNumber = ""

    lateinit var bottomSheetPassTripDetails: BottomSheetDialog
    lateinit var bottomSheetPassCancelReason: BottomSheetDialog
    lateinit var bottomSheetPassRideCancelled: BottomSheetDialog




    // GeeksforGeeks coordinates
    private var originLatitude: Double = 28.5021359
    private var originLongitude: Double = 77.4054901
    private lateinit var mMap: GoogleMap






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_passenger_trip_details)

        val mapFragment = supportFragmentManager.findFragmentById(binding.passMap.id) as SupportMapFragment
        mapFragment.getMapAsync(this)


        binding.header.ivBack.setOnClickListener(View.OnClickListener {
            finish()
        })

        binding.header.tvHeaderText.setText("Trip Details")

        val data = intent.extras
        selectedPassengerTripData = data?.getParcelable<PassengerTripManagementData>("passengerTripDetails")

        viewModel.progressBarStatus.observe(this) {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        }


        viewModel.passengerTripManagementDetailApi("Bearer " + userPref.user.apiToken, selectedPassengerTripData?.bookingId!!)

        Log.d(TAG, "onCreate:bookingId "   +selectedPassengerTripData?.bookingId!!)
        viewModel.getPassengerCancelReasonListApi("Bearer " + userPref.user.apiToken)

        passengerTripDetailsDialog()

    }


//    private fun getDirectionURL(origin: LatLng, dest: LatLng, secret: String) : String{
//        return "https://maps.googleapis.com/maps/api/directions/json?origin=${origin.latitude},${origin.longitude}" +
//                "&destination=${dest.latitude},${dest.longitude}" +
//                "&sensor=false" +
//                "&mode=driving" +
//                "&key=$secret"
//    }


    private fun passengerTripDetailsDialog() {

        val dialogBinding = LayoutInflater
            .from(this@PassengerTripDetailsActivity).inflate(R.layout.bottom_sheet_passenger_trip_details, null)
        bottomSheetPassTripDetails = BottomSheetDialog(this@PassengerTripDetailsActivity)
        bottomSheetPassTripDetails.setContentView(dialogBinding)
        bottomSheetPassTripDetails.setCancelable(true)
        bottomSheetPassTripDetails.setCanceledOnTouchOutside(false)

        bottomSheetPassTripDetails.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        bottomSheetPassTripDetails.behavior.peekHeight = 340
        bottomSheetPassTripDetails.setOnShowListener { dia ->
            val bottomSheetDialog = dia as BottomSheetDialog
            val bottomSheetInternal: FrameLayout =
                bottomSheetDialog.findViewById(com.google.android.material.R.id.design_bottom_sheet)!!
            bottomSheetInternal.setBackgroundResource(R.drawable.rounded_top_corners)
        }

        val tvTripCode: TextView = bottomSheetPassTripDetails.findViewById(R.id.tv_trip_code)!!
        val tvRating: TextView = bottomSheetPassTripDetails.findViewById(R.id.tv_rating)!!
        val tvPickLocation: TextView = bottomSheetPassTripDetails.findViewById(R.id.tv_pickLocation)!!
        val tvDropLocation: TextView = bottomSheetPassTripDetails.findViewById(R.id.tv_dropLocation)!!
        val imgUser: ImageView = bottomSheetPassTripDetails.findViewById(R.id.imgUser)!!
        val llBtnCancel: LinearLayout = bottomSheetPassTripDetails.findViewById(R.id.ll_btnCancel)!!

        val btnCancelRide: Button = bottomSheetPassTripDetails.findViewById(R.id.btnCancelRide)!!
        val llCallDriver: LinearLayout = bottomSheetPassTripDetails.findViewById(R.id.llCallDriver)!!




        viewModel.passengerTripDetailResponse.observe(this) {
            if (it.status == 1) {
                toast(it.message!!)
                tvTripCode.text = it.data[0].startCode
                /*binding.tvBookingDate.text = it.data[0].bookingDate
                binding.tvBookingTime.text = it.data[0].bookingTime
                binding.vehicleName.text = it.data[0].vehicleName*/
                tvRating.text = it.data[0].rating.toString()

                tvPickLocation.text = it.data[0].dropLocation
                tvDropLocation.text = it.data[0].picupLocation
                //  binding.tvAmount.text = "₹" + it.data[0].fare
                Glide.with(this).load(it.data[0].mainImage).into(imgUser)
                Glide.with(this).load(it.data[0].mainImage).into(imgUser)
                userPref.setDriverId(it.data[0]!!.driverId.toString())
                callDriverNumber = it.data[0].driverMobileNumber.toString()
                if(it.rideCancelStatus.toString().equals("0")){
                    llBtnCancel.visibility = View.VISIBLE
                }
                else if(it.rideCancelStatus.toString().equals("1")){
                    llBtnCancel.visibility = View.GONE
                }
                val originLocation = LatLng(originLatitude, originLongitude)
                mMap.addMarker(MarkerOptions().position(originLocation))
                val destinationLocation = LatLng(it.data[0].dropLat!!.toDouble(), it.data[0].dropLong!!.toDouble())
                mMap.addMarker(MarkerOptions().position(destinationLocation))
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(originLocation, 14F))

            } else {
                toast(it.message!!)
            }
        }


        tvTripCode.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, PassengerRideCompletedActivity :: class.java).also {
                it.putExtra("passengerTripBookingId", selectedPassengerTripData?.bookingId!!)
                it.putExtra("passengerTripUserId", selectedPassengerTripData?.userId!!)
            })
        })


        btnCancelRide.setOnClickListener(View.OnClickListener {
            cancelReasonDialog()
        })

        llCallDriver.setOnClickListener(View.OnClickListener {
            // callDriverNumber.let { it1 -> listener.onCallNowClicked(it1) }
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$callDriverNumber")
            startActivity(intent)
        })








        bottomSheetPassTripDetails.show()

    }

    private fun cancelReasonDialog() {
        val dialogBinding = LayoutInflater
            .from(this).inflate(R.layout.bottom_sheet_cancel_trip, null)
        bottomSheetPassCancelReason = BottomSheetDialog(this)
        bottomSheetPassCancelReason.setContentView(dialogBinding)

        bottomSheetPassCancelReason.setOnShowListener { dia ->
            val bottomSheetDialog = dia as BottomSheetDialog
            val bottomSheetInternal: FrameLayout =
                bottomSheetDialog.findViewById(com.google.android.material.R.id.design_bottom_sheet)!!
            bottomSheetInternal.setBackgroundResource(R.drawable.ic_launcher_background)
        }
        bottomSheetPassCancelReason.setCancelable(true)


        val rvReasons: RecyclerView = bottomSheetPassCancelReason.findViewById(R.id.rv_reasons)!!
        val btnConfirmCancel: Button = bottomSheetPassCancelReason.findViewById(R.id.btnConfirmCancel)!!
        val etFeedback: TextView = bottomSheetPassCancelReason.findViewById(R.id.et_feedback)!!
        val ivClose: ImageView = bottomSheetPassCancelReason.findViewById(R.id.iv_close)!!







        viewModel.passengerCancelReasonResponse.observe(this) {
            if (it.status == 1) {
                toast(it.message!!)
                listData.clear()
                listData.addAll(it.data)
                passengerCancelReasonAdapter =
                    PassengerCancelTripReasonAdapter(this, listData)
                rvReasons.apply {
                    adapter = passengerCancelReasonAdapter
                    layoutManager = LinearLayoutManager(context)
                }
            } else {
                Log.d("Response", it.toString())
                toast(it.message!!)
            }
        }

        viewModel.passengerTripCancelResponse.observe(this) {
            if (it.status == 1) {
                toast(it.message!!)
                crnNumber = it.CRN.toString()
                bottomSheetPassCancelReason.dismiss()
                rideCancelledDialog()

                //   finish()
            } else {
                Log.d("Response", it.toString())
                toast(it.message!!)
            }
        }

        btnConfirmCancel.setOnClickListener {
            listReasonType_id.clear()
            etFeedback.text.toString()
            for (i in 0 until rvReasons.childCount){
                val cbReason =rvReasons.getChildAt(i).findViewById(R.id.cb_reason) as CheckBox
                if (cbReason.isChecked){
                    val id=listData[i].id
                    listReasonType_id.add(id.toString())

                    reasontypevalue_id =  listReasonType_id.toString()
                    str= android.text.TextUtils.join(",", listReasonType_id)
                    //   datetypevalue_id = android.text.TextUtils.join(",", listDateType_id);
                }
            }
            viewModel.passengerTripCancelApi(
                "Bearer " + userPref.user.apiToken,
                selectedPassengerTripData?.bookingId!!,
                str,
                etFeedback.text.toString()
            )
            Log.d("CheckBoxInfo",selectedPassengerTripData?.bookingId!!+str+etFeedback.text.toString())
        }
        ivClose.setOnClickListener {
            bottomSheetPassCancelReason.dismiss()
        }


        bottomSheetPassCancelReason.show()


    }


    private fun rideCancelledDialog() {
        val dialogBinding = LayoutInflater
            .from(this).inflate(R.layout.bottom_sheet_ridecancellation, null)
        bottomSheetPassRideCancelled = BottomSheetDialog(this)
        bottomSheetPassRideCancelled.setContentView(dialogBinding)

        bottomSheetPassRideCancelled.setOnShowListener { dia ->
            val bottomSheetDialog = dia as BottomSheetDialog
            val bottomSheetInternal: FrameLayout =
                bottomSheetDialog.findViewById(com.google.android.material.R.id.design_bottom_sheet)!!
            bottomSheetInternal.setBackgroundResource(R.drawable.ic_launcher_background)
        }
        bottomSheetPassRideCancelled.setCancelable(true)

        val tvCRN: TextView = bottomSheetPassRideCancelled.findViewById(R.id.tv_CRN)!!
        val ivClose: ImageView = bottomSheetPassRideCancelled.findViewById(R.id.iv_close)!!
        val btnOk: Button = bottomSheetPassRideCancelled.findViewById(R.id.btnOk)!!



        tvCRN.setText("Your booking with "+  crnNumber +" has been cancelled successfully.")
        bottomSheetPassRideCancelled.dismiss()
        this.finish()
        ivClose.setOnClickListener {
            bottomSheetPassRideCancelled.dismiss()
        }
        btnOk.setOnClickListener {
            bottomSheetPassRideCancelled.dismiss()
        }

    }




    override fun onMapReady(p0: GoogleMap?) {
        mMap = p0!!
        mMap.clear()

    }


    fun decodePolyline(encoded: String): List<LatLng> {
        val poly = java.util.ArrayList<LatLng>()
        var index = 0
        val len = encoded.length
        var lat = 0
        var lng = 0
        while (index < len) {
            var b: Int
            var shift = 0
            var result = 0
            do {
                b = encoded[index++].code - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lat += dlat
            shift = 0
            result = 0
            do {
                b = encoded[index++].code - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lng += dlng
            val latLng = LatLng((lat.toDouble() / 1E5),(lng.toDouble() / 1E5))
            poly.add(latLng)
        }
        return poly
    }
    class MapData {
        var routes = java.util.ArrayList<Routes>()
    }

    class Routes {
        var legs = java.util.ArrayList<Legs>()
    }

    class Legs {
        var distance = Distance()
        var duration = Duration()
        var end_address = ""
        var start_address = ""
        var end_location =Location()
        var start_location = Location()
        var steps = java.util.ArrayList<Steps>()
    }

    class Steps {
        var distance = Distance()
        var duration = Duration()
        var end_address = ""
        var start_address = ""
        var end_location =Location()
        var start_location = Location()
        var polyline = PolyLine()
        var travel_mode = ""
        var maneuver = ""
    }

    class Duration {
        var text = ""
        var value = 0
    }

    class Distance {
        var text = ""
        var value = 0
    }

    class PolyLine {
        var points = ""
    }

    class Location{
        var lat =""
        var lng =""
    }
    @SuppressLint("StaticFieldLeak")
    private inner class GetDirection(val url : String) : AsyncTask<Void, Void, List<List<LatLng>>>(){
        override fun doInBackground(vararg params: Void?): List<List<LatLng>> {

            val client = OkHttpClient()
            val request = Request.Builder().url(url).build()
            val response = client.newCall(request).execute()
            val data = response.body!!.string()

            val result = java.util.ArrayList<List<LatLng>>()
            try{
                val respObj = Gson().fromJson(data,MapData::class.java)
                val path = java.util.ArrayList<LatLng>()
                for (i in 0 until respObj.routes[0].legs[0].steps.size){
                    path.addAll(decodePolyline(respObj.routes[0].legs[0].steps[i].polyline.points))
                }
                result.add(path)
            }catch (e:Exception){
                e.printStackTrace()
            }
            return result
        }

        override fun onPostExecute(result: List<List<LatLng>>) {
            val lineoption = PolylineOptions()
            for (i in result.indices){
                lineoption.addAll(result[i])
                lineoption.width(10f)
                lineoption.color(Color.GREEN)
                lineoption.geodesic(true)
            }
            mMap.addPolyline(lineoption)
        }
    }
}