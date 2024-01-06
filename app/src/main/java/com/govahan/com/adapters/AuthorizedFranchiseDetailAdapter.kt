package com.govahan.com.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.govahan.com.R
import com.govahan.com.databinding.NoOfVehiclesBinding
import com.govahan.com.model.PostImage

class AuthorizedFranchiseDetailAdapter (val mcontext : Context, val list:ArrayList<PostImage>):
    RecyclerView.Adapter<AuthorizedFranchiseDetailAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var binding: NoOfVehiclesBinding = DataBindingUtil.bind(itemView)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.no_of_vehicles, parent, false)
        return ViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]

            holder.binding.vehicleName.text=data.vehicle_name

             Glide.with(mcontext).load(data.image).into(holder.binding.image)



    }

    override fun getItemCount(): Int {
        return list.size
    }


}