package com.example.rv_anthonychamba_googleapp.recyclerv
import MainActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rv_anthonychamba_googleapp.R
import com.example.rv_anthonychamba_googleapp.models.GoogleNews

class FRecyclerViewAdaptadorGoogleNews(
    private val contexto: MainActivity,
    private val lista: ArrayList<GoogleNews>
) : RecyclerView.Adapter<FRecyclerViewAdaptadorGoogleNews.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView

        val iconImageView: ImageView

        init {
            titleTextView = view.findViewById(R.id.rv_googleNews)

            iconImageView = view.findViewById(R.id.iv_news_image)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FRecyclerViewAdaptadorGoogleNews.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.rv_google_new,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: FRecyclerViewAdaptadorGoogleNews.MyViewHolder,
        position: Int
    ) {
        val googleNewsItem = lista[position]
        holder.titleTextView.text = googleNewsItem.title

        // Utiliza Glide o cualquier otra biblioteca para cargar el ícono desde la URL
        Glide.with(holder.iconImageView.context).load(googleNewsItem.imageUrl).into(holder.iconImageView)
    }

    override fun getItemCount(): Int {
        return lista.size
    }
}
