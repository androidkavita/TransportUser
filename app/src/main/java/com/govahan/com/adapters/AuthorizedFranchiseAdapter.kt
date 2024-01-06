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
import com.govahan.com.activities.authorizedfranchise.AuthorizedFranchisesActivity
import com.govahan.com.customclick.franchiseclick
import com.govahan.com.databinding.RowAuthorizedFranchiseBinding
import com.govahan.com.model.authorizedfranchisesmodel.AuthorizedFranchisesData

class AuthorizedFranchiseAdapter(
    var context: Context,
    val list: List<AuthorizedFranchisesData>
    , private val listener: AuthorizedFranchisesActivity,var franchiseclick:franchiseclick
) : RecyclerView.Adapter<AuthorizedFranchiseAdapter.ViewHolder>() {

    inner  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var binding: RowAuthorizedFranchiseBinding = DataBindingUtil.bind(itemView)!!

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_authorized_franchise, parent, false)
        return ViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val data = list[position]

        /*holder.binding.linearItem.setOnClickListener(View.OnClickListener {
          val intent = Intent(context, TripDetailsActivity::class.java)
          intent.putExtra("orderType", "4")
            context.startActivity(intent)
        })*/

        holder.binding.tvEmail.text = data.email
        holder.binding.tvOwnerName.text = data.vehicle_type
        holder.binding.tvPhn.text = data.mobile_number
        holder.binding.numnber.text = data.vehicle_numbers
        holder.binding.tvOwner.text = data.name
       // holder.binding.tvAvailable.text = data.mobileNo
        holder.binding.ivCompanyLogo
        Glide.with(context)
            .load(data.profile_image)
            .error(R.drawable.user_image_place_holder)
            .into(holder.binding.ivCompanyLogo)
        holder.binding.btnCall.setOnClickListener {
            data.mobile_number.toString().let { it1 -> listener.onCallNowClicked(it1) }
        }

        holder.binding.linearItem.setOnClickListener {
            franchiseclick.franchiseclick(data.frenchise_id.toString())
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    interface OnClick{

        fun onCallNowClicked(number : String)

    }


}