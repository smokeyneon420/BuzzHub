package com.gift.buzzhub

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView

class events_adapter(
    var event_name: ArrayList<String>,
    var image_list: ArrayList<Int>,
    var context: Context) : RecyclerView.Adapter<events_adapter.eventviewHolder>() {

    class eventviewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var textViewEventname: TextView = itemView.findViewById(R.id.txtEventName)
        var imageView : ImageView = itemView.findViewById(R.id.EventImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): eventviewHolder {
        val view:View = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view,parent,false )

        return eventviewHolder(view)
    }

    override fun getItemCount(): Int {

        return event_name.size
    }

    override fun onBindViewHolder(holder: eventviewHolder, position: Int) {
        holder.textViewEventname.text = event_name.get(position)
        holder.imageView.setImageResource(image_list.get(position) )
    }
}