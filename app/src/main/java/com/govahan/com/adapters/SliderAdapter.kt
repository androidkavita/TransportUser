package com.govahan.com.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.govahan.com.R
import com.govahan.com.model.homebannermodel.HomeBannerData
import com.smarteist.autoimageslider.SliderViewAdapter


class SliderAdapter(private val sliderDataArrayList: ArrayList<HomeBannerData>) :
    SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterViewHolder {
        val inflate: View =
            LayoutInflater.from(parent.context).inflate(R.layout.slider_layout, null)
        return SliderAdapterViewHolder(inflate)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterViewHolder, position: Int) {
        val sliderItem = sliderDataArrayList[position]

        Glide.with(viewHolder.itemView)
            .load(sliderItem.bannerImg)
            .fitCenter()
            .into(viewHolder.imageViewBackground)
    }

    override fun getCount(): Int {
        return sliderDataArrayList.size
    }

    class SliderAdapterViewHolder(itemView: View) :
        ViewHolder(itemView) {

        var imageViewBackground: ImageView

        init {
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider)

        }
    }

}