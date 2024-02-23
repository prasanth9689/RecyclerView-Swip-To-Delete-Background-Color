package com.skyblue.recyclerview_swip_to_delete_background_color

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MyAdapter(private val itemsList: ArrayList<Post>) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvItems : TextView = itemView.findViewById(R.id.txt_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvItems.text = itemsList[position].title
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    fun deleteItem(adapterPosition: Int) {
        itemsList.removeAt(adapterPosition)
        notifyItemRemoved(adapterPosition)
    }

}