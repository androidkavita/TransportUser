package com.govahan.com.activities.myoffers

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.govahan.com.R
import com.govahan.com.adapters.MyOffersAdapter
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityMyOffersBinding
import com.govahan.com.model.myoffersmodel.MyOffersData
import com.govahan.com.util.toast
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MyOffersActivity : BaseActivity() {
    private lateinit var binding : ActivityMyOffersBinding
    private val viewModel : MyOffersViewModel by viewModels()
    private var myOffersAdapter : MyOffersAdapter?= null
    private var listData: ArrayList<MyOffersData> = ArrayList()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_offers)

        binding.header.ivBack.setOnClickListener(View.OnClickListener {
            finish()
        })

        binding.header.tvHeaderText.setText("My Offers")


        /*val layoutManagerTopSongs: RecyclerView.LayoutManager = LinearLayoutManager(this,
            GridLayoutManager.VERTICAL, false)
        binding.rvOffers.setLayoutManager(layoutManagerTopSongs)
        val itemsTopSongs: List<String> = Arrays.asList("item", "item", "item", "item", "item", "item", "item", "item", "item", "item")
        binding.rvOffers.setAdapter(MyOffersAdapter(this, itemsTopSongs))
*/



        viewModel.progressBarStatus.observe(this) {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        }




        viewModel.getMyOffersResponse.observe(this) {
            /*if (it.status == 1) {
                loaderComplaintBoxAdapter =  LoaderComplaintBoxAdapter(it.data,
                    this)

                binding.rvComplaintBoxList.apply {
                    adapter = loaderComplaintBoxAdapter
                    layoutManager = LinearLayoutManager(requireContext())
                }
*/






            if (it.status == 1) {
                listData.clear()
                // listData!!.addAll(it.getFavLocdata)

                if (it.data.isEmpty() ) {
                    binding.idNouser.visibility = View.VISIBLE
                    binding.rvOffers.visibility = View.GONE

                }
                else {
                    binding.idNouser.visibility = View.GONE
                    binding.rvOffers.visibility = View.VISIBLE
                    listData.addAll(it.data)
                    myOffersAdapter = MyOffersAdapter(listData   )
                    binding.rvOffers.apply {
                        adapter = myOffersAdapter
                        layoutManager = LinearLayoutManager(this@MyOffersActivity)
                        // it.getFavLocdata?.let { notificationList?.addAll(it) }
                        //    favouriteLocationsAdapter?.notifyDataSetChanged()
                    }
                }


            } else {
                Log.d("Response", it.toString())
                toast(it.message!!)
            }
        }

        viewModel.getMyOffersApi("Bearer " + userPref.user.apiToken)


    }
}