package com.govahan.com.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.govahan.com.R
import com.govahan.com.databinding.RowWalletListBinding
import com.govahan.com.model.loaderwalletfiltermodel.LoaderWalletFilterData
import com.govahan.com.util.DateFormat

class LoaderWalletFilterAdapter (val list: List<LoaderWalletFilterData>
) : RecyclerView.Adapter<LoaderWalletFilterAdapter.ViewHolder>() {

    inner  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var binding: RowWalletListBinding = DataBindingUtil.bind(itemView)!!

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_wallet_list, parent, false)
        return ViewHolder(itemView)

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val data = list[position]
        try {
            holder.binding.tvDate.text = data.create_at
            holder.binding.transactionId.text =data.transaction_id
//            if (data.debit == "debit") {
//                holder.binding.tvName.text = "Money Paid to ${data.name}"
//            } else if (data.credit == "credit") {
                holder.binding.tvName.text = "Money added to wallet"
//            }
            holder.binding.tvAmount.text = " ₹${data.amount}"

            holder.binding.tvDate.text = DateFormat.TimeFormat(data.create_at)
            if (holder.binding.tvDate.text == "") {
                holder.binding.tvDate.text = data.create_at

            }



    }catch (e:Exception){
        e.printStackTrace()
    }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}