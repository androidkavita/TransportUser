package com.govahan.com.activities.notification

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.govahan.com.R
import com.govahan.com.adapters.NotificationAdapter
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityNotificationBinding
import com.govahan.com.model.notificationmodel.NotificationData
import com.govahan.com.util.toast
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class NotificationActivity : BaseActivity() {
    private lateinit var binding : ActivityNotificationBinding
    private val viewModel : NotificationListViewModel by viewModels()
    private var notificationAdapter : NotificationAdapter?= null
    private var listData: ArrayList<NotificationData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_notification)

        binding.header.ivBack.setOnClickListener(View.OnClickListener {
            finish()
        })

        binding.header.tvHeaderText.setText("Notification")

        viewModel.getNotificationListApi("Bearer " + userPref.user.apiToken)

        viewModel.progressBarStatus.observe(this) {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        }

        viewModel.getNotificationResponse.observe(this) {
            if (it.status == 1) {
                listData.clear()
                // listData!!.addAll(it.getFavLocdata)

                if (it.data.isEmpty() ) {
                    binding.idNouser.visibility = View.VISIBLE
                    binding.rvNotification.visibility = View.GONE
                }
                else {
                    binding.idNouser.visibility = View.GONE
                    binding.rvNotification.visibility = View.VISIBLE
                    listData.addAll(it.data)
                    notificationAdapter = NotificationAdapter(listData, this)
                    binding.rvNotification.apply {
                        adapter = notificationAdapter
                        layoutManager = LinearLayoutManager(this@NotificationActivity)
                        // it.getFavLocdata?.let { notificationList?.addAll(it) }
                        //    favouriteLocationsAdapter?.notifyDataSetChanged()
                    }
                }
            } else   {
                Log.d("Response", it.toString())
                toast(it.message!!)
            }
        }
    }
}