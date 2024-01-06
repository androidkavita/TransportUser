package com.govahan.com.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.govahan.com.R
import com.govahan.com.databinding.RowTriphistoryListBinding
import com.govahan.com.model.completedpassengertriphistorymodel.CompletedPassengerHistoryData

class CompletedPassengerTripHistoryAdapter (val list: List<CompletedPassengerHistoryData>,
                                            private val listener: OnClick
) : RecyclerView.Adapter<CompletedPassengerTripHistoryAdapter.ViewHolder>() {

    inner  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var binding: RowTriphistoryListBinding = DataBindingUtil.bind(itemView)!!
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_triphistory_list, parent, false)
        return ViewHolder(itemView)    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val data = list[position]
        /*holder.binding.linearItem.setOnClickListener(View.OnClickListener {
           val intent = Intent(context, BookingDetailsActivity::class.java)
          intent.putExtra("orderType", "4")
            context.startActivity(intent)
        })*/


        holder.binding.tvDate.text = data.bookingDate
        holder.binding.tvTime.text = data.bookingTime
        holder.binding.tvPartyname.text = data.partyName
        holder.binding.tvUserName.text = data.partyName
        holder.binding.tvDetail.text = data.partyName
        holder.binding.tvFrom.text = data.picupLocation
        holder.binding.tvTo.text = data.dropLocation
        holder.binding.tvVehicleNumber.text = data.vehicleNumber







        holder.binding.linearItem.setOnClickListener(View.OnClickListener {

            listener.onCompletedDetailClicked(data)

        })

    }

    override fun getItemCount(): Int {
        return list.size
    }


    interface OnClick{
        fun onCompletedDetailClicked(completedPassengerHistoryData: CompletedPassengerHistoryData)
    }


}