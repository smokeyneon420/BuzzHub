package com.gift.buzzhub

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class events_adapter(
    var event_name: ArrayList<String>,
    var event_details: ArrayList<String>,
    var event_price: ArrayList<String>,
    var image_list: ArrayList<Int>,
    var context: Context) : RecyclerView.Adapter<events_adapter.eventviewHolder>() {

    class eventviewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var textViewEventname: TextView = itemView.findViewById(R.id.txtEventName)
        var textViewEventDetails: TextView = itemView.findViewById(R.id.txtEventDetails)
        var textViewEventPrice: TextView = itemView.findViewById(R.id.txtPrice)
        var imageView : ImageView = itemView.findViewById(R.id.sportingEventProfileImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): eventviewHolder {
        val view:View = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_design,parent,false )

        return eventviewHolder(view)
    }

    override fun getItemCount(): Int {

        return event_name.size
    }

    override fun onBindViewHolder(holder: eventviewHolder, position: Int) {
        holder.textViewEventname.text = event_name.get(position)
        holder.textViewEventDetails.text = event_details.get(position)
        holder.textViewEventPrice.text = event_price.get(position)
        holder.imageView.setImageResource(image_list.get(position) )
    }
}