package com.gift.buzzhub

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView

class comedyAdapter (
    var comedyEventList : ArrayList<String>,
    var comedyEventDetails : ArrayList<String>,
    var comedyEventPrice : ArrayList<String>,
    var imageList : ArrayList<Int>,

    var context : Context) : RecyclerView.Adapter<comedyAdapter.EventViewHolder>() {

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var textViewEventName : TextView = itemView.findViewById(R.id.txtEventName)
        var textViewEventDetails : TextView = itemView.findViewById(R.id.txtEventDetails)
        var textViewEventPrice : TextView = itemView.findViewById(R.id.txtPrice)
        var imageView: ImageView = itemView.findViewById(R.id.EventImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)

        return EventViewHolder(view)
    }

    override fun getItemCount(): Int {

        return comedyEventList.size
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {

        holder.textViewEventName.text = comedyEventList[position]
        holder.textViewEventDetails.text = comedyEventDetails[position]
        holder.textViewEventPrice.text = comedyEventPrice[position]
        holder.imageView.setImageResource(imageList[position])

    }
}
