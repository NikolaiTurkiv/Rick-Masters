package com.test.feature_home.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.test.core_ui.databinding.ProductItemBinding
import com.test.feature_home.R
import com.test.repository_cameras.domain.CameraInfo
import com.test.repository_doors.domain.DoorsInfo

class DoorsAdapter(
    private val inflater: LayoutInflater,
) : RecyclerView.Adapter<DoorsAdapter.DoorsAdapterViewHolder>() {

    private val doorsItems = mutableListOf<DoorsInfo>()

    fun updateList(list: List<DoorsInfo>) {
        doorsItems.clear()
        doorsItems.addAll(list)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoorsAdapterViewHolder {
        return DoorsAdapterViewHolder(ProductItemBinding.inflate(inflater, parent, false))
     }

    override fun onBindViewHolder(holder: DoorsAdapterViewHolder, position: Int) {
        val item = doorsItems[position]
        holder.onBind(item)
    }


    override fun getItemCount(): Int = doorsItems.size


    class DoorsAdapterViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(
            item: DoorsInfo,
        ) {
            with(binding) {
                name.text = item.name
                recImage.visibility = View.INVISIBLE
                onlineText.visibility = View.VISIBLE
                safeImage.setImageResource(com.test.core_ui.R.drawable.ic_lockon)

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
                    playButton.visibility = View.INVISIBLE
                    imageDownload.setImageResource(com.test.core_ui.R.drawable.page_not_found)
                }
            }
        }
    }


}