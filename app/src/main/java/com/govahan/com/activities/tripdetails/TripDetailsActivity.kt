package com.govahan.com.activities.tripdetails

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
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
import com.govahan.com.activities.ridecompleted.RideCompletedActivity
import com.govahan.com.adapters.LoaderCancelTripReasonAdapter
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityTripDetailsBinding
import com.govahan.com.model.loadercancelreasonmodel.LoaderCancelReasonData
import com.govahan.com.util.toast
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.*

@AndroidEntryPoint
class TripDetailsActivity : BaseActivity(),OnMapReadyCallback {
    private lateinit var binding: ActivityTripDetailsBinding
    var selectedLoaderTripData=""
    private val viewModel: TripDetailsViewModel by viewModels()
    private var listData: ArrayList<LoaderCancelReasonData> = ArrayList()
    private var loaderCancelReasonAdapter: LoaderCancelTripReasonAdapter? = null
    private var listReasonType_id:ArrayList<String> = ArrayList()
    var reasontypevalue_id: String? = ""
     var str: String=""
    var selectedDateFormat2 = ""
    var time = ""
    val mcurrentTime = Calendar.getInstance()
    var hour = mcurrentTime[Calendar.HOUR_OF_DAY]
    val minute = mcurrentTime[Calendar.MINUTE]
    val zone = mcurrentTime[Calendar.AM_PM]

    var crnNumber = ""
    var callDriverNumber = ""

    lateinit var bottomSheetLoaderTripDetails: BottomSheetDialog
    lateinit var bottomSheetCancelReason: BottomSheetDialog
    lateinit var bottomSheetRideCancelled: BottomSheetDialog


    // GeeksforGeeks coordinates
    private var originLatitude: Double = 28.5021359
    private var originLongitude: Double = 77.4054901

    /* // Coordinates of a park nearby
     private var destinationLatitude: Double = 0.0
     private var destinationLongitude: Double = 0.0*/

    private lateinit var mMap: GoogleMap

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_trip_details)
        val mapFragment = supportFragmentManager.findFragmentById(binding.map.id) as SupportMapFragment
        mapFragment.getMapAsync(this)
        binding.header.ivBack.setOnClickListener(View.OnClickListener {
            finish()
        })
        binding.header.tvHeaderText.text = "Trip Details"
        if (intent != null) {
            selectedLoaderTripData = intent.getStringExtra("loaderTripDetails").toString()
        }

        val options = PolylineOptions()
        options.color(Color.RED)
        options.width(5f)

        Log.d("TAG___", "onCreate: " + selectedLoaderTripData)

        viewModel.progressBarStatus.observe(this) {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        }

        viewModel.loaderRescheduleTripResponse.observe(this) {
            if (it.status == 1) {
                // toast("booking Successful")
                toast(it.message!!)
                finish()
            } else {
                toast(it.message!!)
            }
        }

        viewModel.loaderTripManagementDetailApi("Bearer " + userPref.user.apiToken,selectedLoaderTripData)
        viewModel.getLoaderCancelReasonListApi("Bearer " + userPref.user.apiToken)

        loaderTripDetailsDialog()




    }
    private fun getDirectionURL(origin:LatLng, dest:LatLng, secret: String) : String{
        return "https://maps.googleapis.com/maps/api/directions/json?origin=${origin.latitude},${origin.longitude}" +
                "&destination=${dest.latitude},${dest.longitude}" +
                "&sensor=false" +
                "&mode=driving" +
                "&key=$secret"
    }
    @RequiresApi(Build.VERSION_CODES.N)
    private fun loaderTripDetailsDialog() {
        val dialogBinding = LayoutInflater
            .from(this@TripDetailsActivity).inflate(R.layout.bottom_sheet_loader_trip_details, null)
        bottomSheetLoaderTripDetails = BottomSheetDialog(this@TripDetailsActivity)
        bottomSheetLoaderTripDetails.setContentView(dialogBinding)
        bottomSheetLoaderTripDetails.setCancelable(true)
        bottomSheetLoaderTripDetails.setCanceledOnTouchOutside(false)

        bottomSheetLoaderTripDetails.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        bottomSheetLoaderTripDetails.behavior.peekHeight = 340
        bottomSheetLoaderTripDetails.setOnShowListener { dia ->
            val bottomSheetDialog = dia as BottomSheetDialog
            val bottomSheetInternal: FrameLayout =
                bottomSheetDialog.findViewById(com.google.android.material.R.id.design_bottom_sheet)!!
            bottomSheetInternal.setBackgroundResource(R.drawable.rounded_top_corners)
        }

        val tvTripCode: TextView = bottomSheetLoaderTripDetails.findViewById(R.id.tv_trip_code)!!
        val tvBookingRDate: TextView= bottomSheetLoaderTripDetails.findViewById(R.id.tv_bookingRDate)!!
        val tvBookingRTime: TextView = bottomSheetLoaderTripDetails.findViewById(R.id.tv_bookingRTime)!!
        val vehicleName: TextView = bottomSheetLoaderTripDetails.findViewById(R.id.vehicle_name)!!
        val tvRating: TextView = bottomSheetLoaderTripDetails.findViewById(R.id.tv_rating)!!
        val tvBodytype: TextView = bottomSheetLoaderTripDetails.findViewById(R.id.tv_bodytype)!!
        val vehicleNumber: TextView = bottomSheetLoaderTripDetails.findViewById(R.id.vehicle_number)!!
        val tvCapacity: TextView = bottomSheetLoaderTripDetails.findViewById(R.id.tv_capacity)!!
        val tvDistance: TextView = bottomSheetLoaderTripDetails.findViewById(R.id.tv_distance)!!
        val tvDriverName: TextView = bottomSheetLoaderTripDetails.findViewById(R.id.tv_driverName)!!
        val tvOwner: TextView = bottomSheetLoaderTripDetails.findViewById(R.id.tv_owner)!!
        val tvFrom: TextView = bottomSheetLoaderTripDetails.findViewById(R.id.tv_from)!!
        val tvTo: TextView = bottomSheetLoaderTripDetails.findViewById(R.id.tv_to)!!
        val tvDriverNamee: TextView = bottomSheetLoaderTripDetails.findViewById(R.id.tv_driverNamee)!!
        val tvDriverRating: TextView = bottomSheetLoaderTripDetails.findViewById(R.id.tv_DriverRating)!!
        val tvPickLocation: TextView = bottomSheetLoaderTripDetails.findViewById(R.id.tv_pickLocation)!!
        val tvDropLocation: TextView = bottomSheetLoaderTripDetails.findViewById(R.id.tv_dropLocation)!!
        val tvAmount: TextView = bottomSheetLoaderTripDetails.findViewById(R.id.tv_amount)!!
        val llBtnCancel: LinearLayout = bottomSheetLoaderTripDetails.findViewById(R.id.ll_btnCancel)!!
        val btnCancel: Button = bottomSheetLoaderTripDetails.findViewById(R.id.btnCancel)!!
        val btnReschedule: Button = bottomSheetLoaderTripDetails.findViewById(R.id.btnReschedule)!!
        val cancel: TextView = bottomSheetLoaderTripDetails.findViewById(R.id.cancel)!!
        val llCallDriver: LinearLayout = bottomSheetLoaderTripDetails.findViewById(R.id.llCallDriver)!!

        viewModel.loaderTripDetailResponse.observe(this) {
            if (it.status == 1) {
                // toast("booking Successful")
                toast(it.message!!)

                userPref.setDriverId(it.data[0]!!.driverId.toString())

                callDriverNumber = it.data[0].driverMobileNumber.toString()

                if(it.rideCancelStatus.toString().equals("0")){
                    llBtnCancel.visibility = View.VISIBLE
                    btnReschedule.visibility = View.VISIBLE
                    cancel.visibility=View.GONE
                }
                else if(it.rideCancelStatus.toString().equals("1")){
                    llBtnCancel.visibility = View.GONE
                    btnReschedule.visibility=View.GONE
                    cancel.visibility=View.VISIBLE
                }
                val vehicleImage: ImageView = bottomSheetLoaderTripDetails.findViewById(R.id.vehicle_image)!!
                val ivDriverImage: ImageView = bottomSheetLoaderTripDetails.findViewById(R.id.iv_DriverImage)!!

                tvTripCode.text = it.data[0].startCode
                tvBookingRDate.text = it.data[0].bookingDate
                tvBookingRTime.text = it.data[0].bookingTime
                vehicleName.text = it.data[0].vehicleName
                tvRating.text = it.data[0].rating.toString()
                tvBodytype.text = it.data[0].bodyType
                vehicleNumber.text = it.data[0].vehicleNumber
                tvCapacity.text = it.data[0].capacity
                tvDistance.text = it.data[0].distance.toString()
                tvDriverName.text = it.data[0].driverName
                tvOwner.text = it.data[0].ownerName
                tvFrom.text = it.data[0].picupLocation
                tvTo.text = it.data[0].dropLocation
                tvDriverNamee.text = it.data[0].driverName
                tvDriverRating.text = it.data[0].rating.toString()
                //  binding.wheelerType.text = it.data[0]..toString()+" Wheeler"
                // binding.tvRidesNumber.text = it.data[0].r
                tvPickLocation.text = it.data[0].picupLocation
                tvDropLocation.text = it.data[0].dropLocation
                tvAmount.text = "₹${it.data[0].fare}"
                Glide.with(this).load(it.data[0].mainImage).into(vehicleImage)
                Glide.with(this).load(it.data[0].mainImage).into(ivDriverImage)


                /*destinationLatitude = it.data[0].dropLat!!.toDouble()
                destinationLongitude = it.data[0].dropLong!!.toDouble()
                Log.d(TAG, "loaderTripDetailsDialog: "+destinationLatitude+destinationLongitude)*/

                val originLocation = LatLng(originLatitude, originLongitude)
                mMap.addMarker(MarkerOptions().position(originLocation))
                val destinationLocation = LatLng(it.data[0].dropLat!!.toDouble(), it.data[0].dropLong!!.toDouble())

                mMap.addMarker(MarkerOptions().position(destinationLocation))
//                val urll = getDirectionURL(originLocation, destinationLocation, "AIzaSyCHl8Ff_ghqPjWqlT2BXJH5BOYH1q-sw0E")
//                GetDirection(urll).execute()
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(originLocation, 14F))

            } else {
                toast(it.message!!)
            }
        }

        tvTripCode.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, RideCompletedActivity :: class.java).also {
                it.putExtra("loaderTripBookingId", selectedLoaderTripData!!) })
        })

        btnCancel.setOnClickListener(View.OnClickListener {
            cancelReasonDialog()
        })


        btnReschedule.setOnClickListener(View.OnClickListener {
            clickDataPicker()
        })

        llCallDriver.setOnClickListener(View.OnClickListener {
            // callDriverNumber.let { it1 -> listener.onCallNowClicked(it1) }
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$callDriverNumber")
            startActivity(intent)
        })
        bottomSheetLoaderTripDetails.show()
    }

//    private fun getURL(from : LatLng, to : LatLng) : String {
//        val origin = "origin=" + from.latitude + "," + from.longitude
//        val dest = "destination=" + to.latitude + "," + to.longitude
//        val sensor = "sensor=false"
//        val params = "$origin&$dest&$sensor"
//        return "https://maps.googleapis.com/maps/api/directions/json?$params"
//    }



    private fun cancelReasonDialog() {
        val dialogBinding = LayoutInflater
            .from(this).inflate(R.layout.bottom_sheet_cancel_trip, null)
        bottomSheetCancelReason = BottomSheetDialog(this)
        bottomSheetCancelReason.setContentView(dialogBinding)

        bottomSheetCancelReason.setOnShowListener { dia ->
            val bottomSheetDialog = dia as BottomSheetDialog
            val bottomSheetInternal: FrameLayout =
                bottomSheetDialog.findViewById(com.google.android.material.R.id.design_bottom_sheet)!!
            bottomSheetInternal.setBackgroundResource(R.drawable.ic_launcher_background)
        }
        bottomSheetCancelReason.setCancelable(true)

        val rvReasons: RecyclerView = bottomSheetCancelReason.findViewById(R.id.rv_reasons)!!
        val btnConfirmCancel: Button = bottomSheetCancelReason.findViewById(R.id.btnConfirmCancel)!!
        val etFeedback: TextView = bottomSheetCancelReason.findViewById(R.id.et_feedback)!!
        val ivClose: ImageView = bottomSheetCancelReason.findViewById(R.id.iv_close)!!

        viewModel.loaderCancelReasonResponse.observe(this) {
            if (it.status == 1) {
                toast(it.message!!)
                listData.clear()
                listData.addAll(it.data)
                loaderCancelReasonAdapter =
                    LoaderCancelTripReasonAdapter(this, listData)
                rvReasons.apply {
                    adapter = loaderCancelReasonAdapter
                    layoutManager = LinearLayoutManager(context)
                }
            } else {
                Log.d("Response", it.toString())
                toast(it.message!!)
            }
        }

        viewModel.loaderTripCancelResponse.observe(this) {
            if (it.status == 1) {
                toast(it.message!!)
                crnNumber = it.CRN.toString()
                bottomSheetCancelReason.dismiss()
                rideCancelledDialog()
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

            viewModel.loaderTripCancelApi(
                "Bearer " + userPref.user.apiToken,
                selectedLoaderTripData!!,
                str,
                etFeedback.text.toString()
            )
            Log.d("CheckBoxInfo",selectedLoaderTripData!!+str+etFeedback.text.toString())

        }
        ivClose.setOnClickListener {
            bottomSheetCancelReason.dismiss()
        }

        bottomSheetCancelReason.show()

    }

    private fun rideCancelledDialog() {

        val dialogBinding = LayoutInflater
            .from(this).inflate(R.layout.bottom_sheet_ridecancellation, null)
        bottomSheetRideCancelled = BottomSheetDialog(this)
        bottomSheetRideCancelled.setContentView(dialogBinding)

        bottomSheetRideCancelled.setOnShowListener { dia ->
            val bottomSheetDialog = dia as BottomSheetDialog
            val bottomSheetInternal: FrameLayout =
                bottomSheetDialog.findViewById(com.google.android.material.R.id.design_bottom_sheet)!!
            bottomSheetInternal.setBackgroundResource(R.drawable.ic_launcher_background)
        }
        bottomSheetRideCancelled.setCancelable(true)

        val tvCRN: TextView = bottomSheetRideCancelled.findViewById(R.id.tv_CRN)!!
        val ivClose: ImageView = bottomSheetRideCancelled.findViewById(R.id.iv_close)!!
        val btnOk: Button = bottomSheetRideCancelled.findViewById(R.id.btnOk)!!

        tvCRN.setText("Your booking with "+  crnNumber +" has been cancelled successfully.")
        bottomSheetRideCancelled.dismiss()
        this.finish()

        ivClose.setOnClickListener {
            bottomSheetRideCancelled.dismiss()
        }
        btnOk.setOnClickListener {
            bottomSheetRideCancelled.dismiss()
        }

        bottomSheetRideCancelled.show()

    }

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.N)
    private fun clickDataPicker() {
        val cal = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
        val simpleDateFormat2 = SimpleDateFormat("yyyy-MM-dd")
        cal.timeZone = TimeZone.getTimeZone("UTC")
        val tvBookingReDate: TextView= bottomSheetLoaderTripDetails.findViewById(R.id.tv_bookingRDate)!!
        //  val tvBookingReTime: TextView = bottomSheetLoaderTripDetails.findViewById(R.id.tv_bookingRTime)!!
        val datePickerDialog = DatePickerDialog(
            this,R.style.DatePickerTheme, { view, year, monthOfYear, dayOfMonth ->
                cal.set(year, monthOfYear, dayOfMonth)
                tvBookingReDate.text = simpleDateFormat.format(cal.time)
                selectedDateFormat2 = simpleDateFormat2.format(cal.time)
                clickTimePicker()
            },
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000
        datePickerDialog.show()
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.N)
    private fun clickTimePicker() {
        val mTimePicker = TimePickerDialog(
            this,R.style.DatePickerTheme, { timePicker, selectedHour, selectedMinute ->

                time = "$selectedHour:$selectedMinute "
                // bottomSheet.findViewById<Button>(R.id.tvTime).text = time
                val tvBookingReTime: TextView = bottomSheetLoaderTripDetails.findViewById(R.id.tv_bookingRTime)!!
                tvBookingReTime.text = time

                viewModel.loaderRescheduleTripApi("Bearer " + userPref.user.apiToken,
                    selectedLoaderTripData!!,
                    selectedDateFormat2,
                    time)

                Log.d(TAG, "clickTimePicker: "+selectedLoaderTripData!!+
                        selectedDateFormat2+time)
            },
            hour,
            minute + zone,
            false
        ) //Yes 24 hour time
        mTimePicker.setTitle("Select Time")
        mTimePicker.show()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val sydney = LatLng(-34.0, 151.0)
        val opera = LatLng(-33.9320447,151.1597271)
        mMap!!.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap!!.addMarker(MarkerOptions().position(opera).title("Opera House"))
    }


    fun decodePolyline(encoded: String): List<LatLng> {
        val poly = ArrayList<LatLng>()
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
        var routes = ArrayList<Routes>()
    }

    class Routes {
        var legs = ArrayList<Legs>()
    }

    class Legs {
        var distance = Distance()
        var duration = Duration()
        var end_address = ""
        var start_address = ""
        var end_location =Location()
        var start_location = Location()
        var steps = ArrayList<Steps>()
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

            val result =  ArrayList<List<LatLng>>()
            try{
                val respObj = Gson().fromJson(data,MapData::class.java)
                val path =  ArrayList<LatLng>()
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
                lineoption.width(5f)
                lineoption.color(Color.GREEN)
                lineoption.geodesic(true)
            }
            mMap.addPolyline(lineoption)
        }
    }
}