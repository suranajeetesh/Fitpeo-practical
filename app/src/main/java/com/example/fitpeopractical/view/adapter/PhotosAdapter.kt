package com.example.fitpeopractical.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fitpeopractical.R
import com.example.fitpeopractical.data.remote.model.response.PhotosResponseItem
import com.example.fitpeopractical.databinding.BannerItemBinding

/**
 * Created by Abhin.
 */
class PhotosAdapter(
    private var mList: List<PhotosResponseItem>,
    private val mItemClickListener: ItemClickListener
) : RecyclerView.Adapter<PhotosAdapter.CommonAdapterViewHolder>() {
    var binding: BannerItemBinding? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonAdapterViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_photos,
            parent,
            false
        )
        return CommonAdapterViewHolder(binding!!)
    }

    override fun getItemCount() = mList.size

    override fun onBindViewHolder(holder: CommonAdapterViewHolder, position: Int) {
        val banner = mList[position]
        holder.bindData(banner)
        holder.itemView.setOnClickListener {
            mItemClickListener.itemClick(position)
        }
    }

    class CommonAdapterViewHolder(var binding: BannerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(list: PhotosResponseItem) =
            binding.apply {
                mData = list
                executePendingBindings()
            }
    }

    interface ItemClickListener {
        fun itemClick(position: Int)
    }
}
