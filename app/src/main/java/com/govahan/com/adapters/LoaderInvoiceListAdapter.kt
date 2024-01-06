package com.govahan.com.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.govahan.com.R
import com.govahan.com.databinding.RowInvoiceListBinding
import com.govahan.com.model.loaderinvoicelistmodel.LoaderInvoiceData


class LoaderInvoiceListAdapter (val list: List<LoaderInvoiceData>,
                                private val listener: OnClick
) : RecyclerView.Adapter<LoaderInvoiceListAdapter.ViewHolder>() {


    inner  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var binding: RowInvoiceListBinding = DataBindingUtil.bind(itemView)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_invoice_list, parent, false)
        return ViewHolder(itemView)    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val data = list[position]
        /*holder.binding.btnViewdetails.setOnClickListener(View.OnClickListener {
          val intent = Intent(context, InvoiceSummaryActivity::class.java)
          intent.putExtra("orderType", "4")
            context.startActivity(intent)
        })*/


        holder.binding.tvDate.text = data.bookingDate
       holder.binding.tvInvoicenumber.text = data.invoiceNumber
        holder.binding.tvFrom.text = data.picupLocation
        holder.binding.tvTo.text = data.dropLocation
        holder.binding.tvBookingid.text = data.bookingId



        holder.binding.btnViewdetails.setOnClickListener(View.OnClickListener {

            listener.onInvoiceClicked(data)

        })

    }

    override fun getItemCount(): Int {
        return list.size
    }



    interface OnClick{
        fun onInvoiceClicked(loaderInvoiceData: LoaderInvoiceData)
    }

}