package com.govahan.com.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.govahan.com.R
import com.govahan.com.databinding.RowPassengerAvailableVehiclesBinding
import com.govahan.com.model.searchPassengerVehicle.SearchPassengerData

class AvailablePVehiclesAdapter (private val list: List<SearchPassengerData>
,private val listener : OnClick,var reviewsclick: OnClick
) : RecyclerView.Adapter<AvailablePVehiclesAdapter.ViewHolder>() {

    inner  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var binding: RowPassengerAvailableVehiclesBinding = DataBindingUtil.bind(itemView)!!

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_passenger_available_vehicles, parent, false)
        return ViewHolder(itemView)    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val data = list[position]
        /*holder.binding.btnProceed.setOnClickListener(View.OnClickListener {
          val intent = Intent(context, BookingReviewPActivity::class.java)
          intent.putExtra("orderType", "4")
            context.startActivity(intent)
        })

        holder.binding.tvOwner.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, TransportOwnerActivity::class.java)
            intent.putExtra("orderType", "4")
            context.startActivity(intent)
        })*/




        holder.binding.vehicleName.text = data.vehicleName
        // holder.binding.tvAvailable.text = data.available
//        holder.binding.wheelerType.text = data.noTyres.toString()
        holder.binding.tvFrom.text = data.picupLocation
        holder.binding.tvTo.text = data.dropupLocation
        holder.binding.vehicleNumber.text = data.vehicleNo
        holder.binding.tvFinalFare.text = "₹${data.totalFare}"
        holder.binding.tvCapacity.text = data.seat.toString()
        holder.binding.tvDistance.text = data.distance.toString()
        holder.binding.tvBodytype.text = data.bodytype.toString()
        holder.binding.tvDrivername.text = data.driverName.toString()
        holder.binding.tvOwnername.text = data.ownerName.toString()
        holder.binding.tvCompletedtrip.text = data.driver_total_booking.toString()
        holder.binding.tvRating.text = data.rating.toString()




        if(data.available.toString().equals("0")){
            holder.binding.tvAvailable.text = "Not Available"
            holder.binding.ivCheck.visibility = View.GONE
        }
        else if(data.available.toString().equals("1")){
            holder.binding.tvAvailable.text = "Available"
            holder.binding.ivCheck.visibility = View.VISIBLE
        }

        // holder.binding.tvDistance.text = data.heightId + " Feet"
        //  holder.binding.vehicleName.text = data.na
        //   holder.binding.vehicleName.text = data.name

        Glide.with(holder.itemView.context).load(data.mainImage).into(holder.binding.vehicleImage)

       /* holder.binding.btnCallNow.setOnClickListener {
            data.contactNumber?.let { it1 -> listener.onCallNowClicked(it1) }
        }*/

        holder.binding.btnCallNow.setOnClickListener {
            data.mobileNumber.toString().let { it1 -> listener.onCallNowClicked(it1) }
        }


        holder.binding.btnProceed.setOnClickListener {
            listener.onProceedClicked(data)
        }


        holder.binding.reviews.setOnClickListener {
            reviewsclick.reviewsclick(data.driverId.toString()  )
        }


    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnClick{

        fun onCallNowClicked(number : String)

        fun onProceedClicked(searchPassListModelData: SearchPassengerData)
        fun reviewsclick(id:String)

    }


}