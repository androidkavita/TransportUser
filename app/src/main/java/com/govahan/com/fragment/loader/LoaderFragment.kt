package com.govahan.com.fragment.loader

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.icu.text.SimpleDateFormat
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
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.govahan.com.R
import com.govahan.com.activities.availablevehicles.AvailableVehiclesActivity
import com.govahan.com.activities.bookvehicle.BookAVehicleActivity
import com.govahan.com.baseClasses.BaseFragment
import com.govahan.com.databinding.FragmentLoaderBinding
import com.govahan.com.fragment.passenger.PassengerFragmentViewModel
import com.govahan.com.model.noOfTyrePModel.NoOfTyrePData
import com.govahan.com.model.truckbodytypeget.TruckBodyTypeData
import com.govahan.com.model.truckcapacityget.TruckCapacityData
import com.govahan.com.model.trucknooftyreget.TruckNoOfTyreData
import com.govahan.com.model.truckpricefor_get.PriceForData
import com.govahan.com.model.vehicletypemodel.VehicleTypeData
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class LoaderFragment : BaseFragment() {
    val truckTypeListData = ArrayList<VehicleTypeData>()
    var datePicker: DatePickerDialog? = null
    private val viewModel1 : PassengerFragmentViewModel by viewModels()
    private lateinit var binding: FragmentLoaderBinding
    private val viewModel: LoaderFragmentViewModel by viewModels()
    private var truckTypeList = ArrayList<String>()
    private var truckCapacityList = ArrayList<String>()
    private var truckBodyTypeList = ArrayList<String>()
    private var truckNumberOfTyreList = ArrayList<String>()
    private var truckPriceForList = ArrayList<String>()
//    private var truckTypeListData = ArrayList<TruckTypeData>()
    private var truckCapacityListData = ArrayList<TruckCapacityData>()
    private var truckBodyTypeListData = ArrayList<TruckBodyTypeData>()
    private var truckNumberOfTyreListData = ArrayList<NoOfTyrePData>()
    private var truckPriceForListData = ArrayList<PriceForData>()
    //  private var tonListData = ArrayList<Ton>()
    //  private var feetListData = ArrayList<Feet>()
    private var tonList = ArrayList<String>()
    private var feetList = ArrayList<String>()
    private val AUTOCOMPLETE_FROM_REQUEST_CODE = 1
    private val AUTOCOMPLETE_TO_REQUEST_CODE = 2
    var latLng: LatLng? = null
    var pickupLongitude = 0.0
    var pickupLatitude = 0.0
    var dropLongitude = 0.0
    var dropLatitude = 0.0
    var selectedTruckType = ""
    var selectedCapacityType = ""
    var selectedBodyType = ""
    var selectedNoOfTyre = ""
    var selectedPriceFor = ""
    var placesClient: PlacesClient? = null
    lateinit var pickedTime: Date

    //  lateinit var selectedDateFormat2 : String
    var selectedDateFormat2 = ""
    val mcurrentTime = Calendar.getInstance()
    var hour = mcurrentTime[Calendar.HOUR_OF_DAY]
    val minute = mcurrentTime[Calendar.MINUTE]
    val zone = mcurrentTime[Calendar.AM_PM]

    var date = ""
    var time = ""

    var minutes = mcurrentTime[Calendar.MINUTE]


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_loader, container, false)
        /*binding.btnSearch.setOnClickListener(View.OnClickListener {
            val intent = Intent(requireContext(), AvailableVehiclesActivity::class.java)
            startActivity(intent)
        })*/

        viewModel.vehicleTypeApi("Bearer " + userPref.user.apiToken,"2")
        //  GetTruckType
//        viewModel.truckTypeApi("Bearer " + userPref.user.apiToken)
        binding.spinnerTrucktype.setOnSpinnerItemSelectedListener<String> { oldIndex, oldItem, newIndex, newText ->
            toast(requireContext(), truckTypeListData[newIndex].id.toString())
            selectedTruckType = truckTypeListData[newIndex].id.toString()
            //  viewModel.truckFeetAndTonApi("Bearer " + userPref.user.api_token, truckTypeListData[newIndex].id.toString())
        }

        viewModel.vehicleTypeListResponse.observe(requireActivity()) {
            if (it.status == 1) {
                truckTypeList.clear()
                truckTypeListData.clear()
                truckTypeListData.addAll(it.data)

                for (i in truckTypeListData) {
                    i.vType?.let { it1 -> truckTypeList.add(it1) }
                }

                binding.spinnerTrucktype.setItems(truckTypeList)
            } else {
                it.message?.let { it1 -> toast(requireContext(), it1) }
            }
        }
//        viewModel.truckTypeListResponse.observe(requireActivity()) {
//            if (it.status == 1) {
//                truckTypeList.clear()
//                truckTypeListData.clear()
//                truckTypeListData.addAll(it.data)
//
//                for (i in truckTypeListData) {
//                    i.name?.let { it1 -> truckTypeList.add(it1) }
//                }
//
//                binding.spinnerTrucktype.setItems(truckTypeList)
//            } else {
//                it.message?.let { it1 -> toast(requireContext(), it1) }
//            }
//        }


        //   GetTruckCapacity
        viewModel.truckCapacityApi("Bearer " + userPref.user.apiToken)


        // GetBodyType
        viewModel.truckBodyTypeApi("Bearer " + userPref.user.apiToken)
        binding.spinnerBodytype.setOnSpinnerItemSelectedListener<String> { oldIndex, oldItem, newIndex, newText ->
            toast(requireContext(), truckBodyTypeListData[newIndex].id.toString())
            selectedBodyType = truckBodyTypeListData[newIndex].id.toString()
            //  viewModel.truckFeetAndTonApi("Bearer " + userPref.user.api_token, truckTypeListData[newIndex].id.toString())
        }
        viewModel.truckBodyTypeListResponse.observe(requireActivity()) {
            if (it.status == 1) {
                truckBodyTypeList.clear()
                truckBodyTypeListData.clear()
                truckBodyTypeListData.addAll(it.data)

                for (i in truckBodyTypeListData) {
                    i.name?.let { it1 -> truckBodyTypeList.add(it1) }
                }

                binding.spinnerBodytype.setItems(truckBodyTypeList)
            } else {
                it.message?.let { it1 -> toast(requireContext(), it1) }
            }
        }


        //  GetNoOfTyre
        viewModel1.noOfTyrePApi("Bearer " + userPref.user.apiToken,"2")
        binding.spinnerNumberoftyres.setOnSpinnerItemSelectedListener<String> { oldIndex, oldItem, newIndex, newText ->
            selectedNoOfTyre = truckNumberOfTyreListData[newIndex].id.toString()
            //  viewModel.truckFeetAndTonApi("Bearer " + userPref.user.api_token, truckTypeListData[newIndex].id.toString())
        }
        viewModel1.noOfTyresPListResponse.observe(requireActivity()) {
            if (it.status == 1) {
                truckNumberOfTyreList.clear()
                truckNumberOfTyreListData.clear()
                truckNumberOfTyreListData.addAll(it.data)

                for (i in truckNumberOfTyreListData) {
                    i.wheel?.let { it1 -> truckNumberOfTyreList.add(it1) }
                }
                binding.spinnerNumberoftyres.setItems(truckNumberOfTyreList)
            } else {
                it.message?.let { it1 -> toast(requireContext(), it1) }
            }
        }

        //  GetPriceFor
        viewModel.truckPriceForApi("Bearer " + userPref.user.apiToken)
        binding.spinnerPricefor.setOnSpinnerItemSelectedListener<String> { oldIndex, oldItem, newIndex, newText ->
            toast(requireContext(), truckPriceForListData[newIndex].id.toString())
            selectedPriceFor = truckPriceForListData[newIndex].id.toString()
            //  viewModel.truckFeetAndTonApi("Bearer " + userPref.user.api_token, truckTypeListData[newIndex].id.toString())
        }
        viewModel.truckPriceForListResponse.observe(requireActivity()) {
            if (it.status == 1) {
                truckPriceForList.clear()
                truckPriceForListData.clear()
                truckPriceForListData.addAll(it.data)

                for (i in truckPriceForListData) {
                    i.wheel?.let { it1 -> truckPriceForList.add(it1) }
                }

                binding.spinnerPricefor.setItems(truckPriceForList)
            } else {
                it.message?.let { it1 -> toast(requireContext(), it1) }
            }
        }

        val apiKey = getString(R.string.api_key)
        if (!Places.isInitialized()) {
            Places.initialize(requireContext(), apiKey)
        }
        // Create a new Places client instance.
        placesClient = Places.createClient(requireContext())


        if (BookAVehicleActivity.selectedPassengerTripData != null) {
            binding.fromLocation.text =
                BookAVehicleActivity.selectedPassengerTripData!!.pickAddress.toString()
            binding.dropLocation.text =
                BookAVehicleActivity.selectedPassengerTripData!!.dropAddress.toString()
        }

        binding.rlFromLocation.setOnClickListener {
            placesAPiCall(AUTOCOMPLETE_FROM_REQUEST_CODE)
        }
        binding.rlToLocation.setOnClickListener {
            placesAPiCall(AUTOCOMPLETE_TO_REQUEST_CODE)
        }

        viewModel.progressBarStatus.observe(requireActivity()) {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        }

        binding.btnSearch.setOnClickListener {
            if (binding.etTriptask.text.toString() == "") {
                toast(requireContext(), "Please enter your trip task")
            } else if (binding.fromLocation.text == "") {
                toast(requireContext(), "Please enter your pickup location")
            } else if (binding.dropLocation.text == "") {
                toast(requireContext(), "Please enter your drop location")
            }
            /*else if (selectedTruckType == ""){
                toast(requireContext() , "Please Select Truck Type")
            }
            else if (binding.spinnerTrucktype.text == ""){
                toast(requireContext() , "Please Select Truck Weight in Ton")
            }
            else if (binding.spinnerBodytype.text == ""){
                toast(requireContext() , "Please Select Truck height")
            }*/
            else if (binding.tvSelectedDate.text == "") {
                toast(requireContext(), "Please select date.")
            } else {
                startActivity(Intent(requireContext(), AvailableVehiclesActivity::class.java).also {
                    it.putExtra("triptask", binding.etTriptask.text.toString())
                    it.putExtra("pickupLocation", binding.fromLocation.text.toString())
                    it.putExtra("pickupLatitude", pickupLatitude.toString())
                    it.putExtra("pickupLongitude", pickupLongitude.toString())
                    it.putExtra("dropLatitude", dropLatitude.toString())
                    it.putExtra("dropLongitude", dropLongitude.toString())
                    it.putExtra("dropLocation", binding.dropLocation.text.toString())
                    it.putExtra("truckType", selectedTruckType)
                    it.putExtra("capacity", binding.spinnerCapacity.text.toString())
                    it.putExtra("body_type", selectedBodyType)
                    it.putExtra("wheel", selectedNoOfTyre)
                    it.putExtra("price_for", selectedPriceFor)
                    it.putExtra("booking_date", binding.tvSelectedDate.text.toString())
                    //  it.putExtra("booking_time", time)
                    it.putExtra("booking_time", binding.spinnerTimeslots.selectedItem.toString())
                })
            }
            Log.d(TAG, "token:" + userPref.user.apiToken)

            /*System.out.print("pickupLocation"+binding.fromLocation.text.toString()+
                    "pickupLatitude"+ pickupLatitude.toString()+
                    "pickupLatitude"+ pickupLatitude.toString()+
                    "dropLatitude"+ dropLatitude.toString()+
                    "dropLongitude"+ dropLongitude.toString()+
                    "dropLocation"+ binding.dropLocation.text.toString()+
                    "truckType"+ selectedTruckType+
                    "ton"+ selectedTon+
                    "feet"+ selectedFeet)*/

//               Toast.makeText(requireContext(), "selectedDateFormat2"+selectedDateFormat2, Toast.LENGTH_SHORT).show()

        }

        binding.llDate.setOnClickListener {
            clickDataPicker()
        }



        return binding.root
    }


    private fun placesAPiCall(requestCode: Int) {
        val fields = listOf(
            Place.Field.ID,
            Place.Field.NAME,
            Place.Field.ADDRESS,
            Place.Field.LAT_LNG
        )
        val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
            .build(requireActivity())
        startActivityForResult(intent, requestCode)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == AUTOCOMPLETE_FROM_REQUEST_CODE) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    val place = Autocomplete.getPlaceFromIntent(data!!)
                    Log.i("TAG", "Place: " + place.name + ", " + place.id)
                    latLng = place.latLng
                    pickupLongitude = latLng!!.longitude
                    pickupLatitude = latLng!!.latitude
                    binding.fromLocation.text = place.name
                }
                AutocompleteActivity.RESULT_ERROR -> {
                    val status = Autocomplete.getStatusFromIntent(data!!)
                    Log.i("TAG", status.statusMessage!!)
                }
                Activity.RESULT_CANCELED -> {
                }
            }
            return
        } else if (requestCode == AUTOCOMPLETE_TO_REQUEST_CODE) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    val place = Autocomplete.getPlaceFromIntent(data!!)
                    Log.i("TAG", "Place: " + place.name + ", " + place.id)
                    latLng = place.latLng
                    dropLongitude = latLng!!.longitude
                    dropLatitude = latLng!!.latitude
                    binding.dropLocation.text = place.name
                }
                AutocompleteActivity.RESULT_ERROR -> {
                    val status = Autocomplete.getStatusFromIntent(data!!)
                    Log.i("TAG", status.statusMessage!!)
                }
                Activity.RESULT_CANCELED -> {
                    // The user canceled the operation.
                }
            }
            return
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.N)
    fun clickDataPicker() {
        val cal = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
        val simpleDateFormat2 = SimpleDateFormat("yyyy-MM-dd")
        cal.timeZone = TimeZone.getTimeZone("UTC")

        val datePickerDialog = DatePickerDialog(
            requireContext(),R.style.DatePickerTheme, { view, year, monthOfYear, dayOfMonth ->
                cal.set(year, monthOfYear, dayOfMonth)

                binding.tvSelectedDate.text = simpleDateFormat.format(cal.time)
            },
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.datePicker.minDate = System.currentTimeMillis()
        datePickerDialog.show()
    }


}