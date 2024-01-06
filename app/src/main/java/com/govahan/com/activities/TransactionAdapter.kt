package com.govahan.com.activities

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.govahan.com.R
import com.govahan.com.databinding.RowWalletListBinding
import com.govahan.com.util.DateFormat

class TransactionAdapter (val list: ArrayList<TransactionReportData>
) : RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

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
            holder.binding.transactionId.text=data.transaction_id
            if (data.booking_amount==null){
                holder.binding.tvAmount.text="₹${data.wallet_amount} added to wallet"
            }else{
                holder.binding.tvAmount.text="Payment of ₹${data.booking_amount} for booking"
            }
            holder.binding.tvDate.text=DateFormat.TimeFormat(data.transaction_date.toString())

        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


}