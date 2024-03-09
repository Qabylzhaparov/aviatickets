package com.example.aviatickets.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.aviatickets.model.entity.Offer

class OffersCallback(private val oldList: List<Offer>, private val newList: List<Offer>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
