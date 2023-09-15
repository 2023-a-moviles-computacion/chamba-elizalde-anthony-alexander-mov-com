package com.example.rv_anthonychamba_googleapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.rv_anthonychamba_googleapp.providers.GT_Provider
import com.example.rv_anthonychamba_googleapp.recyclerv.FRecyclerViewAdaptadorGoogleTool

class Recycler_list_searchItem : AppCompatActivity() {

    val arregloRedditSearchItem = GT_Provider.googleToolsList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv_si)

        inicializarRecycleView()

    }

    private fun inicializarRecycleView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rv_searchitems)

        val adaptador = FRecyclerViewAdaptadorGoogleTool(
            this,
            arregloRedditSearchItem
        )

        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget
            .DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget
            .LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()

    }
}