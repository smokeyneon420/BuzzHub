package com.gift.buzzhub

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class UserEventOverviewAdapter(var eventList:ArrayList<Events>,
                                var imgList:ArrayList<Int>,
                                var context:Context): RecyclerView.Adapter<UserEventOverviewAdapter.ViewHolder>(){

                                    class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
                                        var textViewName: TextView = itemView.findViewById(R.id.txtEventName)
                                        var textViewDetail: TextView = itemView.findViewById(R.id.txtEventDetails)
                                        var textViewPrice: TextView = itemView.findViewById(R.id.txtPrice)
                                        var eventImg: ImageView = itemView.findViewById(R.id.EventImage)
                                        var cardView: CardView = itemView.findViewById(R.id.eventsCardView)
                                    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.card_view,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewName.text = eventList[position].eventName
        holder.textViewDetail.text = eventList[position].eventDetails
        holder.textViewPrice.text = "R${eventList[position].eventPrice.toString()}"
        holder.eventImg.setImageResource(imgList[position])
        val eventId = eventList[position].eventId


        holder.itemView.setOnClickListener{

            openPayment(eventList[position].eventName,eventList[position].eventDetails,eventList[position].eventPrice)
        }
    }


    fun openPayment(eventName:String, eventDetails:String, eventPrice:Double){
        val intent = Intent(context,EventPayment::class.java)
        intent.putExtra("eventName",eventName)
        intent.putExtra("eventDetails",eventDetails)
        intent.putExtra("eventPrice",eventPrice)
        context.startActivity(intent)

    }
}