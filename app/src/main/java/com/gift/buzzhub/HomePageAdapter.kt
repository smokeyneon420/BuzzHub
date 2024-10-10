package com.gift.buzzhub

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class HomePageAdapter(var nameList:ArrayList<String>,
                      var detailsList:ArrayList<String>,
                      var imageList:ArrayList<Int>,
                      var context: Context,
                        private val activityContext: Context): RecyclerView.Adapter<HomePageAdapter.ViewHolder>() {
     class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
         var textViewName: TextView = itemView.findViewById(R.id.textViewCategoryName)
         var textViewDetail: TextView = itemView.findViewById(R.id.textViewCategoryDetails)
         var imageView: ImageView = itemView.findViewById(R.id.category_image)
         var cardView: CardView = itemView.findViewById(R.id.category_cardView)

     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.home_recycle_view,parent,false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return nameList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewName.text = nameList.get(position)
        holder.textViewDetail.text = detailsList.get(position)
        holder.imageView.setImageResource(imageList.get(position))
        holder.cardView.setOnClickListener{
            categoryScreenIntent(nameList[position],position)

        }
    }

    //function to take user to category screen if they click a category on HomePage Activity
    fun categoryScreenIntent(screen: String,position: Int) {
        when (screen) {
            "Festivals" -> {
                val intent = Intent(activityContext, FestivalsActivity::class.java)
                activityContext.startActivity(intent)
                Toast.makeText(context,"You have selected ${nameList[position]}",
                    Toast.LENGTH_LONG).show()
            }
            "Comedy shows" -> {
                val intent = Intent(activityContext, Comedy::class.java)
                activityContext.startActivity(intent)
                Toast.makeText(context,"You have selected ${nameList[position]}",
                    Toast.LENGTH_LONG).show()
            }
            "Exhibitions" -> {
                val intent = Intent(activityContext, Exhibitions::class.java)
                activityContext.startActivity(intent)
                Toast.makeText(context,"You have selected ${nameList[position]}",
                    Toast.LENGTH_LONG).show()
            }
            "Sporting Events" -> {
                val intent = Intent(activityContext, SportingEventsActivity::class.java)
                activityContext.startActivity(intent)
                Toast.makeText(context,"You have selected ${nameList[position]}",
                    Toast.LENGTH_LONG).show()
            }
            "Gambling" -> {
                val intent = Intent(activityContext, GambleActivity::class.java)
                activityContext.startActivity(intent)
                Toast.makeText(context,"You have selected ${nameList[position]}",
                    Toast.LENGTH_LONG).show()
            }
            else -> { Toast.makeText(context,"You have selected ${nameList[position]}",
                Toast.LENGTH_LONG).show()}
        }
    }




}