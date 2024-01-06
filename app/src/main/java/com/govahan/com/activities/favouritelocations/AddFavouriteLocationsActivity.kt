package com.govahan.com.activities.favouritelocations

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.govahan.com.R
import com.govahan.com.activities.bookvehicle.BookAVehicleActivity
import com.govahan.com.adapters.FavouriteLocationAdapter
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityFavouriteLocationsBinding
import com.govahan.com.model.getfavouritelocation.GetFavouriteLocationData
import com.govahan.com.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddFavouriteLocationsActivity : BaseActivity(), FavouriteLocationAdapter.OnClick {
    lateinit var binding : ActivityFavouriteLocationsBinding
    private val viewModel : AddFavouriteLocationViewModel by viewModels()

    var pickupLongitude = 0.0
    var pickupLatitude = 0.0
    var dropLongitude = 0.0
    var dropLatitude = 0.0
    private val AUTOCOMPLETE_FROM_REQUEST_CODE = 1
    private val AUTOCOMPLETE_TO_REQUEST_CODE = 2
    var latLng: LatLng? = null
    var placesClient: PlacesClient? = null
    private var favouriteLocationsAdapter : FavouriteLocationAdapter ?= null

    private var listData: ArrayList<GetFavouriteLocationData> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_favourite_locations)

        binding.header.ivBack.setOnClickListener(View.OnClickListener {
            finish()
        })

        binding.header.tvHeaderText.text = "Favourite Locations"


        val apiKey = getString(R.string.api_key)
        if (!Places.isInitialized()) {
            Places.initialize(this, apiKey)
        }
        // Create a new Places client instance.
        placesClient = Places.createClient(this)

        binding.rlFromLocation.setOnClickListener {
            placesAPiCall(AUTOCOMPLETE_FROM_REQUEST_CODE)
        }
        binding.rlToLocation.setOnClickListener {
            placesAPiCall(AUTOCOMPLETE_TO_REQUEST_CODE)
        }


        viewModel.getFavouriteLocationApi("Bearer " + userPref.user.apiToken)

        viewModel.progressBarStatus.observe(this) {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        }

        viewModel.getFavouriteResponse.observe(this) {
            if (it.status == 1) {
                listData.clear()
               // listData!!.addAll(it.getFavLocdata)

                if (it.getFavLocdata.isEmpty() ) {
                    binding.idNouser.visibility = View.VISIBLE
                    binding.rvFavourite.visibility = View.GONE

                }
                else {
                    binding.idNouser.visibility = View.GONE
                    binding.rvFavourite.visibility = View.VISIBLE
                    listData.addAll(it.getFavLocdata)
                    favouriteLocationsAdapter = FavouriteLocationAdapter(listData, this)
                    binding.rvFavourite.apply {
                        adapter = favouriteLocationsAdapter
                        layoutManager = LinearLayoutManager(this@AddFavouriteLocationsActivity)

                       // it.getFavLocdata?.let { notificationList?.addAll(it) }
                    //    favouriteLocationsAdapter?.notifyDataSetChanged()

                    }
                 //   favouriteLocationsAdapter?.notifyDataSetChanged()
                }

            } else   {
                Log.d("Response", it.toString())
                toast(it.message!!)
            }
        }

         /*____________get favourite location ends___________________*/

        /*____________add booking starts___________________*/

        viewModel.addFavouriteLocationListResponse.observe(this) {
            if (it.status == 1) {

                binding.fromLocation.text = " "
                binding.dropLocation.text = " "






                /*____________get favourite location starts___________________*/


                viewModel.getFavouriteLocationApi("Bearer " + userPref.user.apiToken)

                viewModel.getFavouriteResponse.observe(this) {
                    if (it.status == 1) {
                        listData.clear()
                        // listData!!.addAll(it.getFavLocdata)
                        if (it.getFavLocdata.isEmpty()||it.getFavLocdata.equals(null) ) {
                            binding.idNouser.visibility = View.VISIBLE
                            binding.rvFavourite.visibility = View.GONE

                        }
                        else {
                            binding.idNouser.visibility = View.GONE
                            binding.rvFavourite.visibility = View.VISIBLE
                            listData.addAll(it.getFavLocdata)
                            favouriteLocationsAdapter = FavouriteLocationAdapter(listData, this)
                            binding.rvFavourite.apply {
                                adapter = favouriteLocationsAdapter
                                layoutManager = LinearLayoutManager(this@AddFavouriteLocationsActivity)

                            }
                        }
                    } else   {
                        Log.d("Response", it.toString())
                        toast(it.message!!)
                    }
                }

                /*____________get favourite location ends___________________*/

            } else {
                Log.d("Response", it.toString())
                toast(it.message!!)
            }
        }

        /*____________add booking ends___________________*/


        viewModel.deleteFavLocationResponse.observe(this) {
            if (it.status == 1) {
                toast(it.message!!)
                //viewModel.getFavouriteLocationApi("Bearer " + userPref.user.apiToken)
                finish();
                overridePendingTransition(0, 0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent);
                overridePendingTransition(0, 0);

            } else {
                toast(it.message!!)
            }
        }

        binding.btnAddToFav.setOnClickListener {
            if(binding.fromLocation.text.toString().isBlank()){
                toast(this , "Please enter your pickup location")
            }else if (binding.dropLocation.text.toString().isBlank()){
                toast(this , "Please enter your drop location")
            }else{
                binding.fromLocation.text.isBlank()
                binding.dropLocation.text.isBlank()
                viewModel.addFavouriteLocationApi("Bearer " + userPref.user.apiToken,
                    pickupLatitude.toString(),
                    pickupLongitude.toString(),
                    dropLatitude.toString(),
                    dropLongitude.toString()
                )
            }

        }

    }


    private fun placesAPiCall(requestCode : Int) {
        val fields = listOf(
            Place.Field.ID,
            Place.Field.NAME,
            Place.Field.ADDRESS,
            Place.Field.LAT_LNG
        )
        val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
            .build(this)
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
        }
        else if (requestCode == AUTOCOMPLETE_TO_REQUEST_CODE) {
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


    override fun onRemoveClicked(id : String) {
        viewModel.deleteFavLocationApi("Bearer " + userPref.user.apiToken, id)
    }

    override fun onBookClicked(getFavouriteLocationData: GetFavouriteLocationData) {

        /*val intent = Intent(this, BookAVehicleActivity::class.java)
        startActivity(intent)*/

        startActivity(Intent(this, BookAVehicleActivity :: class.java).also {
            it.putExtra("favouriteLocation", getFavouriteLocationData)

        })

    }


    /*
    override fun onProceedClicked(passengerTripListModelData: PassengerTripManagementData) {
        startActivity(Intent(requireContext(), PassengerTripDetailsActivity :: class.java).also {
            it.putExtra("passengerTripDetails", passengerTripListModelData.bookingId.toString())

        })

        Log.d("TAG++", "onProceedClicked: "+passengerTripListModelData.bookingId.toString())
    }*/




}