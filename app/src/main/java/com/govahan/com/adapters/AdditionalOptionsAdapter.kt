package com.govahan.com.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.govahan.com.databinding.RowAdditionalOptionsBinding
import javax.inject.Inject

class AdditionalOptionsAdapter @Inject constructor() : RecyclerView.Adapter<AdditionalViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdditionalViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowAdditionalOptionsBinding.inflate(inflater, parent, false)
        return AdditionalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdditionalViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return 6
    }
}

class AdditionalViewHolder(val binding: RowAdditionalOptionsBinding) : RecyclerView.ViewHolder(binding.root) {

}