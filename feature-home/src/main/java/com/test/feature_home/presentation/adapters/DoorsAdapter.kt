package com.test.feature_home.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.test.core_ui.databinding.DoorItemBinding
import com.test.core_ui.databinding.ProductItemBinding
import com.test.feature_home.presentation.domain.DoorsUI

class DoorsAdapter(
    private val inflater: LayoutInflater,
    private val itemEditClick: (item: DoorsUI) -> Unit,
    private val itemContainerClick: (Int) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val doorsItems = mutableListOf<DoorsUI>()

    fun updateList(list: List<DoorsUI>) {
        doorsItems.clear()
        doorsItems.addAll(list)

        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = doorsItems[position]
        if (item.viewType == 0) {
            (holder as DoorsShortAdapterViewHolder).onBind(
                item,
                itemEditClick,
                itemContainerClick,
                position
            )
        } else {
            (holder as DoorsAdapterViewHolder).onBind(item, itemContainerClick, position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> DoorsShortAdapterViewHolder(DoorItemBinding.inflate(inflater, parent, false))
            else -> DoorsAdapterViewHolder(ProductItemBinding.inflate(inflater, parent, false))
        }
    }

    override fun getItemViewType(position: Int): Int = when (doorsItems[position].viewType) {
        0 -> 0
        else -> 1
    }


    override fun getItemCount(): Int = doorsItems.size


    class DoorsAdapterViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(
            item: DoorsUI,
            itemContainerClick: (Int) -> Unit,
            position: Int
        ) {
            with(binding) {
                productItemContainer.setOnClickListener {
                    itemContainerClick.invoke(position)
                }
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

    class DoorsShortAdapterViewHolder(private val binding: DoorItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(
            item: DoorsUI,
            itemEditClick: (doorUi: DoorsUI) -> Unit,
            itemContainerClick: (Int) -> Unit,
            position: Int
        ) {
            with(binding) {
                doorName.text = item.name
                edit.setOnClickListener {
                    itemEditClick.invoke(item)
                }
                doorItemContainer.setOnClickListener {
                    itemContainerClick.invoke(position)
                }
            }
        }
    }


}