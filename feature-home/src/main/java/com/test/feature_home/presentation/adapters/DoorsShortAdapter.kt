package com.test.feature_home.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.core_ui.databinding.DoorItemBinding
import com.test.repository_doors.domain.DoorsInfo


class DoorsShortAdapter(
    private val inflater: LayoutInflater,
    private val itemEditClick: () -> Unit
) : RecyclerView.Adapter<DoorsShortAdapter.DoorsShortAdapterViewHolder>() {

    private val doorsItems = mutableListOf<DoorsInfo>()

    fun updateList(list: List<DoorsInfo>) {
        doorsItems.clear()
        doorsItems.addAll(list)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoorsShortAdapterViewHolder {
        return DoorsShortAdapterViewHolder(DoorItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: DoorsShortAdapterViewHolder, position: Int) {
        val item = doorsItems[position]
        holder.onBind(item, itemEditClick)
    }

    override fun getItemCount(): Int = doorsItems.size

    class DoorsShortAdapterViewHolder(private val binding: DoorItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(
            item: DoorsInfo,
            itemEditClick: () -> Unit
        ) {
            with(binding) {
                doorName.text = item.name
                edit.setOnClickListener {
                    itemEditClick.invoke()
                }
            }
        }
    }
}