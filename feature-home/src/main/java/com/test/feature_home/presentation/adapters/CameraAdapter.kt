package com.test.feature_home.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.test.core_ui.databinding.ProductItemBinding
import com.test.repository_cameras.domain.CameraInfo

class CameraAdapter(private val inflater: LayoutInflater) :
    RecyclerView.Adapter<CameraAdapter.CameraAdapterViewHolder>() {

    private val camerasItems = mutableListOf<CameraInfo>()

    fun updateList(list: List<CameraInfo>) {
        camerasItems.clear()
        camerasItems.addAll(list)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CameraAdapterViewHolder {
        return CameraAdapterViewHolder(ProductItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: CameraAdapterViewHolder, position: Int) {
        val item = camerasItems[position]
        holder.onBind(item)
    }

    override fun getItemCount(): Int = camerasItems.size

    class CameraAdapterViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: CameraInfo) {
            with(binding) {
                name.text = item.name

                if (item.favorites == true) {
                    favoriteImage.visibility = View.VISIBLE
                } else {
                    favoriteImage.visibility = View.INVISIBLE
                }

                if (item.snapshot != null) {
                    Picasso.get()
                        .load(item.snapshot)
                        .into(imageDownload)
                } else {
                    recImage.visibility = View.INVISIBLE
                    imageDownload.setImageResource(com.test.core_ui.R.drawable.page_not_found)
                }

            }
        }
    }
}