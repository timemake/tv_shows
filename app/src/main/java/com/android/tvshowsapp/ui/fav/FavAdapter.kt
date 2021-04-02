package com.android.tvshowsapp.ui.fav

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.tvshowsapp.R
import com.android.tvshowsapp.database.FavShow
import com.android.tvshowsapp.databinding.ShowItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class FavAdapter(val clickListener: ShowClickListener) : ListAdapter<FavShow, FavAdapter.ViewHolder>(DiffCallback){

    companion object DiffCallback : DiffUtil.ItemCallback<FavShow>() {
        override fun areItemsTheSame(oldItem: FavShow, newItem: FavShow) = oldItem.id == newItem.id
        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: FavShow, newItem: FavShow) = oldItem == newItem
    }

    class ViewHolder(private val binding:ShowItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(favShow: FavShow){
            binding.apply {
                textName.text = favShow.name
                textStartDate.text = favShow.start_date
                textCountry.text = favShow.country
                textStatus.text = favShow.satus
                Glide.with(itemView)
                        .load(favShow.image_thumbnail_path)
                        .apply(RequestOptions()
                                .placeholder(R.drawable.loading_animation)
                                .error(R.drawable.ic_broken_image))
                        .into(imageView)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ShowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            clickListener.onClick(item)
        }
    }

    class ShowClickListener(val clickListener: (favShow: FavShow) -> Unit){
        fun onClick(favShow: FavShow) = clickListener(favShow)
    }
}