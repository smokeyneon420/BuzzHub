package com.gift.buzzhub

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ConcertsHostAdapter(var nameList: ArrayList<String>,
                            var detailsList: ArrayList<String>) : RecyclerView.Adapter<ConcertsHostAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textViewName: TextView = itemView.findViewById(R.id.concertsViewName)
        val textViewDetails: TextView = itemView.findViewById(R.id.concertsViewDetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.concerts_category_card_design,parent,false)

        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int =nameList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       holder.textViewName.text = nameList[position]
        holder.textViewDetails.text = detailsList[position]
    }
}