package com.govahan.com.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.govahan.com.R
import com.govahan.com.databinding.RowTermsLayoutBinding

class TermsAdapter (val context : Context, val list: List<String>) : RecyclerView.Adapter<TermsAdapter.ViewHolder>() {
    private var listener: OnItemClickListener? = null

    inner  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var binding: RowTermsLayoutBinding = DataBindingUtil.bind(itemView)!!

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_terms_layout, parent, false)
        return ViewHolder(itemView)    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val data = list[position]

        /*holder.binding.linearitem.setOnClickListener(View.OnClickListener {
          val intent = Intent(context, TripDetailsActivity::class.java)
          intent.putExtra("orderType", "4")
            context.startActivity(intent)
        })*/
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, view: View)
    }

}