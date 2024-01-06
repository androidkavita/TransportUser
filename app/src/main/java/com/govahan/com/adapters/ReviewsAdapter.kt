package com.govahan.com.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.govahan.com.R
import com.govahan.com.databinding.ReviewsItemsBinding
import com.govahan.com.model.ReviewsData


class ReviewsAdapter(private val list: List<ReviewsData>

) : RecyclerView.Adapter<ReviewsAdapter.ViewHolder>() {

    inner  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var binding: ReviewsItemsBinding = DataBindingUtil.bind(itemView)!!
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.reviews_items, parent, false)
        return ViewHolder(itemView)    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]


        holder.binding.date.text = data.date
        // holder.binding.tvAvailable.text = data.available
        holder.binding.username.text = data.user_name.toString()
       // holder.binding.tvFrom.text = data.fr
      //  holder.binding.userRating.text = data.dropupLocation
        holder.binding.userRating.setRating(data.rating!!.toFloat());
        holder.binding.comment.text=data.review

    }

    override fun getItemCount(): Int {
        return list.size
    }



    interface OnClick{

      //  fun onCallNowClicked(number : String)

      //  fun onProceedClicked(searchListModelData: SearchVehicleData)
    }
}