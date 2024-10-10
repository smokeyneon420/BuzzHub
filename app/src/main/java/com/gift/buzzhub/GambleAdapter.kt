package com.gift.buzzhub

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView

class GambleAdapter(
    var gambleSites: ArrayList<String>,
    var imagesGamble: ArrayList<Int>,
    var context: Context,
):RecyclerView.Adapter<GambleAdapter.GambleViewHolder>() {

    class GambleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var SitesName : TextView = itemView.findViewById(R.id.gambleSite)
        var imageView : CircleImageView = itemView.findViewById(R.id.hollywoodImg)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GambleViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.gambling_card_design, parent, false)
        return GambleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return gambleSites.size
    }

    override fun onBindViewHolder(holder: GambleViewHolder, position: Int) {
        holder.SitesName.text = gambleSites.get(position)
        holder.imageView.setImageResource(imagesGamble.get(position))
    }

}