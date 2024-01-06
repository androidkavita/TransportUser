package com.govahan.com.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.govahan.com.R
import com.govahan.com.databinding.RowOffersBinding
import com.govahan.com.model.myoffersmodel.MyOffersData

class MyOffersAdapter (private val list: List<MyOffersData>
) : RecyclerView.Adapter<MyOffersAdapter.ViewHolder>() {

    inner  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var binding: RowOffersBinding = DataBindingUtil.bind(itemView)!!

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_offers, parent, false)
        return ViewHolder(itemView)    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val data = list[position]



        holder.binding.tvDiscount.text = data.discount
//        holder.binding.tvDistance.text = data.discount
//        holder.binding.tvOnBooking.text = data.discount
        holder.binding.tvTruckName.text = data.discount

        Glide.with(holder.itemView.context).load(data.offerImages).into(holder.binding.ivVehicleImage)
/*        holder.binding.linearitem.setOnClickListener(View.OnClickListener {
          val intent = Intent(context, TripDetailsActivity::class.java)
          intent.putExtra("orderType", "4")
            context.startActivity(intent)
        })*/
    }

    override fun getItemCount(): Int {
        return list.size
    }



    /*interface OnClick{
        fun onViewDetail(loaderComplaintData: LoaderComplaintData)
    }
*/
}