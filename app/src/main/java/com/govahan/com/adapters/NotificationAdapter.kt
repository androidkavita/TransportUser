package com.govahan.com.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.govahan.com.R
import com.govahan.com.activities.notification.NotificationActivity
import com.govahan.com.databinding.RowNotificationLayoutBinding
import com.govahan.com.model.notificationmodel.NotificationData

class NotificationAdapter(
    val list: List<NotificationData>,
    private val listener: NotificationActivity
) : RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    inner  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var binding: RowNotificationLayoutBinding = DataBindingUtil.bind(itemView)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_notification_layout, parent, false)
        return ViewHolder(itemView)    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val data = list[position]

        holder.binding.tvHeadnotification.text = data.title.toString()
        holder.binding.tvDetail.text = data.message

        holder.binding.tvTime.text = data.createAt

    }

    override fun getItemCount(): Int {
        return list.size
    }

}
