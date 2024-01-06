package com.govahan.com.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.govahan.com.R
import com.govahan.com.databinding.RowAvailableVehiclesBinding
import com.govahan.com.model.searchvehiclemodel.SearchVehicleData

class AvailableVehiclesAdapter(private val list: List<SearchVehicleData>,
                               private val listener : OnClick,var reviewsclick: OnClick) : RecyclerView.Adapter<AvailableVehiclesAdapter.ViewHolder>() {


    inner  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var binding: RowAvailableVehiclesBinding = DataBindingUtil.bind(itemView)!!
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_available_vehicles , parent, false)
        return ViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        holder.binding.vehicleName.text = data.vehicleName
        holder.binding.tvAvailableat.text = data.avl_time
       // holder.binding.tvAvailable.text = data.available
//        holder.binding.wheelerType.text = data.noOfTyres.toString()
        holder.binding.tvFrom.text = data.picupLocation
        holder.binding.tvTo.text = data.dropupLocation
        holder.binding.vehicleNumber.text = data.vehicleNumber.toString()
        holder.binding.tvBodytype.text = data.bodytype
        holder.binding.tvCapacity.text = data.capacity
        holder.binding.tvDistance.text = data.distance
        holder.binding.tvDrivername.text = data.driverName.toString()
        holder.binding.tvFinalFare.text = "₹" + data.totalFare
        holder.binding.tvCompletedtrips.text= data.driver_total_booking.toString()
        holder.binding.tvRating.text=data.rating.toString()
        holder.binding.tvOwnername.text=data.vehicleOwnerName.toString()



        if(data.available.toString().equals("0")){
            holder.binding.tvAvailable.text = "Not Available"
            holder.binding.ivCheck.visibility = View.GONE
        }
        else if(data.available.toString().equals("1")){
            holder.binding.tvAvailable.text = "Available"
            holder.binding.ivCheck.visibility = View.VISIBLE
        }


        Glide.with(holder.itemView.context).load(data.mainImage).into(holder.binding.vehicleImage)

        holder.binding.btnCallNow.setOnClickListener {
            data.mobile_number.toString().let { it1 -> listener.onCallNowClicked(it1) }
        }

        holder.binding.btnProceed.setOnClickListener {
            listener.onProceedClicked(data  )
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
        fun onProceedClicked(searchListModelData: SearchVehicleData)
        fun reviewsclick(id:String)
    }

}