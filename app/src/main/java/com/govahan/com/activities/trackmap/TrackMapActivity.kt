package com.govahan.com.activities.trackmap

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.google.android.gms.maps.model.*
import com.govahan.com.R
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityTrackMapBinding

import java.util.*


class TrackMapActivity : BaseActivity() {
    private lateinit var binding : ActivityTrackMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_track_map)

        binding.ivBack.setOnClickListener(View.OnClickListener {
            finish()

        })

      //  startRideOnMap()

    }

    /*fun startRideOnMap(mLastLocation: Location) {
        if (isAdded) {
            mMap!!.clear()
            handlerStatusCheck.removeCallbacks(runnableStatusCheck!!)
            handlerUpComing.removeCallbacks(runnableUpComing!!);
            val latitude = 28.671246
            val longitude = 77.317654
            mMap!!.mapType = GoogleMap.MAP_TYPE_NORMAL
            mMap!!.isTrafficEnabled = false
            mMap!!.isIndoorEnabled = false
            mMap!!.isBuildingsEnabled = false
            mMap!!.uiSettings.isZoomControlsEnabled = true
            mMap!!.uiSettings.setAllGesturesEnabled(true)
            mMap!!.uiSettings.isZoomGesturesEnabled = true
            // Add a marker in Home and move the camera
            // Add a marker in Home and move the camera
            val sydneyUser = LatLng(
                Prefrences.getPrefrences(this, "user_pic_lat").toString().toDouble(),
                Prefrences.getPrefrences(this, "user_pic_lng").toString().toDouble()
            )
            val opt = BitmapFactory.Options();
            opt.inMutable = true;
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_rating_star, opt)
            val pinBitmap = Bitmap.createScaledBitmap(bitmap, 65, 140, true)
            mMap!!.addMarker(MarkerOptions().position(sydneyUser).icon(BitmapDescriptorFactory.fromBitmap(pinBitmap)).title("Marker in Home"))
            mMap!!.moveCamera(CameraUpdateFactory.newLatLng(sydneyUser))
            val operaDriver = LatLng(
                GPSTracker(this).getLatitude(), GPSTracker(this).getLongitude()
            )
            val optDriver = BitmapFactory.Options();
            optDriver.inMutable = true;
            val bitmapDriver = BitmapFactory.decodeResource(resources, R.drawable.ic_download, opt)
            val pinDriverBitmap = Bitmap.createScaledBitmap(bitmapDriver, 65, 140, true)
            mMap!!.addMarker(MarkerOptions().position(operaDriver).icon(BitmapDescriptorFactory.fromBitmap(pinDriverBitmap)))
            mMap!!.moveCamera(CameraUpdateFactory.newLatLng(operaDriver))

            val LatLongB = LatLngBounds.Builder()
            val options = PolylineOptions()
            options.color(Color.BLUE)
            options.width(20f)

            // build URL to call API
            val url = getURL(sydneyUser, operaDriver)

            async {
                // Connect to URL, download content and convert into string asynchronously
                val result = URL(url).readText()
                uiThread {
                    var points: JsonArray<com.beust.klaxon.JsonObject>? = null
                    // When API call is done, create parser and convert into JsonObjec
                    val parser: Parser = Parser()
                    val stringBuilder: StringBuilder = StringBuilder(result)
                    val json: com.beust.klaxon.JsonObject =
                        parser.parse(stringBuilder) as com.beust.klaxon.JsonObject
                    // get to the correct element in JsonObject
                    val routes = json.array<com.beust.klaxon.JsonObject>("routes")
                    for (i in 0 until routes!!.size) {
                        points =
                            routes["legs"]["steps"][i] as JsonArray<com.beust.klaxon.JsonObject>
                        val polypts =
                            points.flatMap { decodePoly(it.obj("polyline")?.string("points")!!) }
                        options.add(sydneyUser)
                        LatLongB.include(sydneyUser)
                        for (point in polypts) {
                            options.add(point)
                            LatLongB.include(point)
                        }
                        options.add(operaDriver)
                        LatLongB.include(operaDriver)
                        // build bounds
                        // add polyline to the map
                        mMap!!.addPolyline(options)

                        mMap!!.moveCamera(
                            CameraUpdateFactory.newCameraPosition(
                                CameraPosition.Builder()
                                    .target(operaDriver)
                                    .zoom(30f)
                                    .bearing(30f)
                                    .tilt(45f)
                                    .build()
                            )
                        )
                    }

                }
            }
        }
    }*/
}