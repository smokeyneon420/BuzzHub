package com.gift.buzzhub

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView

class SettingsAdapter(
    var settingsList: ArrayList<String>,
    var imageList: ArrayList<Int>,
    val context: Context
    ) : RecyclerView.Adapter<SettingsAdapter.SettingsViewHolder>() {

    class SettingsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textSettings : TextView = itemView.findViewById(R.id.accountText)
        var imageView : CircleImageView = itemView.findViewById(R.id.profile_image)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.settings_design, parent, false)


        return SettingsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return settingsList.size
    }

    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
        holder.textSettings.text = settingsList[position]
        holder.imageView.setImageResource(imageList[position])

        holder.itemView.setOnClickListener {
            val intent = when (position) {
                0 -> Intent(context, AccountPage::class.java)
                1 -> Intent(context, DisplayPage::class.java)
                2 -> Intent(context, HelpCentrePage::class.java)
                3 -> {
                    val googlePrivacyPolicyUrl = "https://www.google.com/policies/privacy/"
                    Intent(Intent.ACTION_VIEW, Uri.parse(googlePrivacyPolicyUrl))
                }
                4 -> Intent(context, DeleteAccountPage::class.java)
                5 -> {
                    val instagramUrl = "https://www.instagram.com/buzzhubofficialapp?igsh=MWs2NHlmdTZncmJheA%3D%3D&utm_source=qr/"
                    Intent(Intent.ACTION_VIEW, Uri.parse(instagramUrl))
                }
                6 -> {
                    val facebookUrl = "https://www.facebook.com/profile.php?id=61566337812782&mibextid=LQQJ4d/"
                    Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrl))
                }
                else -> null
            }
            intent?.let { context.startActivity(it) }
        }

    }

}