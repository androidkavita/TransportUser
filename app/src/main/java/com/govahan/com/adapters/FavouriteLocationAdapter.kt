package com.govahan.com.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.govahan.com.R
import com.govahan.com.databinding.RowFavouriteLocationBinding
import com.govahan.com.model.getfavouritelocation.GetFavouriteLocationData


class FavouriteLocationAdapter(val list: List<GetFavouriteLocationData>,
                               private val listener : OnClick) : RecyclerView.Adapter<FavouriteLocationAdapter.ViewHolder>() {

    inner  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var binding: RowFavouriteLocationBinding = DataBindingUtil.bind(itemView)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_favourite_location, parent, false)
        return ViewHolder(itemView)    }


    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val data = list[position]




        holder.binding.tvStart.text = data.pickAddress
        holder.binding.tvStop.text = data.dropAddress


        holder.binding.btnRemove.setOnClickListener(View.OnClickListener {
            listener.onRemoveClicked(data.id.toString())
            val currentList =list.toMutableList()
            currentList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, currentList.size)
            notifyDataSetChanged()
        })


        holder.binding.btnBooktrip.setOnClickListener(View.OnClickListener {

            listener.onBookClicked(data)
        })




    }

    override fun getItemCount(): Int {
        return list.size
    }


    interface OnClick{
     fun onRemoveClicked(id: String)
     fun onBookClicked(getFavouriteLocationData: GetFavouriteLocationData)
    }


}