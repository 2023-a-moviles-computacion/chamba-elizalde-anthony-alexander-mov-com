package com.example.rv_anthonychamba_googleapp.recyclerv

import MainActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rv_anthonychamba_googleapp.R
import com.example.rv_anthonychamba_googleapp.models.GoogleSuggestion


class FRecyclerViewAdaptadorGoogleTool(
    private val contexto: MainActivity,
    private val lista: ArrayList<GoogleSuggestion>
) : RecyclerView.Adapter<FRecyclerViewAdaptadorGoogleTool.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView

        init {
            titleTextView = view.findViewById(R.id.rv_googleTools)

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FRecyclerViewAdaptadorGoogleTool.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.main_activity,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: FRecyclerViewAdaptadorGoogleTool.MyViewHolder,
        position: Int
    ) {
        val googleToolItem = lista[position]
        holder.titleTextView.text = googleToolItem.title
    }

    override fun getItemCount(): Int {
        return lista.size
    }
}
