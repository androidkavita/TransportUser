package com.govahan.com.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.govahan.com.R
import com.govahan.com.databinding.RowTripManagementBinding
import com.govahan.com.model.tripmanagementloadermodel.LoaderTripManagementData

class LoaderTripManagementAdapter (
    val list: List<LoaderTripManagementData>,
    private val listener: OnClick
) : RecyclerView.Adapter<LoaderTripManagementAdapter.ViewHolder>() {

    inner  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var binding: RowTripManagementBinding = DataBindingUtil.bind(itemView)!!

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_trip_management, parent, false)
        return ViewHolder(itemView)    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val data = list[position]

        holder.binding.tvDate.text = data.bookingDate
        holder.binding.tvTime.text = data.bookingTime
        holder.binding.tvPartyname.text = data.fare
        holder.binding.tvUsername.text = data.vehicleName
        holder.binding.tvVehicleName.text = data.vehicleName
        holder.binding.tvBodytype.text = data.bodyname
        holder.binding.tvVehicleNumber.text = data.vehicleNumber
        holder.binding.tvFrom.text = data.picupLocation
        holder.binding.tvTo.text = data.dropLocation
        holder.binding.tvAmount.text = data.fare

        /*holder.binding.llViewdetails.setOnClickListener(View.OnClickListener {
          val intent = Intent(context, TripDetailsActivity::class.java)
          intent.putExtra("orderType", "4")
            context.startActivity(intent)
        })*/

        holder.binding.llViewdetails.setOnClickListener(View.OnClickListener {

            listener.onProceedClicked(data.bookingId)

        })


    }

    override fun getItemCount(): Int {
        return list.size
    }



    interface OnClick{
        fun onProceedClicked(booking_id: String?)
    }
}