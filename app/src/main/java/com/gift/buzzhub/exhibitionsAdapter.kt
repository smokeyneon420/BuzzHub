package com.gift.buzzhub

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView


class exhibitionsAdapter (
    var exhibitionsEventList : ArrayList<String>,
    var detailsEventList : ArrayList<String>,
    var imageList : ArrayList<Int>,

    var context : Context) : RecyclerView.Adapter<exhibitionsAdapter.EventViewHolder>() {

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var textViewEventDetails : TextView = itemView.findViewById(R.id.textViewEventDetails)
        var detailsEventList : TextView = itemView.findViewById(R.id.txtEventName)
        var imageView: ImageView = itemView.findViewById(R.id.eventImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)

        return EventViewHolder(view)
    }

    override fun getItemCount(): Int {

        return exhibitionsEventList.size
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {

        holder.textViewEventDetails.text =exhibitionsEventList[position]

        holder.imageView.setImageResource(imageList[position])

    }
}