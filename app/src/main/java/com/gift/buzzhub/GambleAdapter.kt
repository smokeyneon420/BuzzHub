package com.gift.buzzhub

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView

class GambleAdapter(
    private val gambleSites: ArrayList<String>,
    private val imagesGamble: ArrayList<Int>,
    private val siteNames: ArrayList<String>,
    private val context: Context
) : RecyclerView.Adapter<GambleAdapter.GambleViewHolder>() {

    class GambleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var siteName: TextView = itemView.findViewById(R.id.gambleSite)
        var imageView: ImageView = itemView.findViewById(R.id.gamblingImg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GambleViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.gambling_card_design, parent, false)
        return GambleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return gambleSites.size
    }

    override fun onBindViewHolder(holder: GambleViewHolder, position: Int) {
        val siteUrl = gambleSites[position] // Get the URL directly
        val image = imagesGamble[position]

        holder.siteName.text = siteNames[position]
        holder.imageView.setImageResource(image)

        holder.itemView.setOnClickListener {
            openWebPage(context, siteUrl) // Open the URL in a browser
        }
    }
    private fun openWebPage(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }
}