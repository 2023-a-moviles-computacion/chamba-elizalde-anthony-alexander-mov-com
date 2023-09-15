import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rv_anthonychamba_googleapp.R
import com.example.rv_anthonychamba_googleapp.Recycler_list_searchItem
import com.example.rv_anthonychamba_googleapp.providers.GN_Provider
import com.example.rv_anthonychamba_googleapp.providers.GT_Provider
import com.example.rv_anthonychamba_googleapp.recyclerv.FRecyclerViewAdaptadorGoogleNews
import com.example.rv_anthonychamba_googleapp.recyclerv.FRecyclerViewAdaptadorGoogleTool

class MainActivity : AppCompatActivity() {

    private val arregloGoogleNews = GN_Provider.googleNewsList
    private val arregloGoogleTools = GT_Provider.googleToolsList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val searchButton = findViewById<ImageView>(R.id.btn_searchInicio)
        searchButton.setOnClickListener {
            abrirActividad(Recycler_list_searchItem::class.java)
        }

        // Inicializa y configura el RecyclerView de noticias de Google
        val recyclerViewNews = findViewById<RecyclerView>(R.id.rv_googleNews)
        val adaptadorNews = FRecyclerViewAdaptadorGoogleNews(this, arregloGoogleNews)
        recyclerViewNews.adapter = adaptadorNews
        recyclerViewNews.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Inicializa y configura el RecyclerView de sugerencias de Google
        val recyclerViewTools = findViewById<RecyclerView>(R.id.rv_googleTools)
        val adaptadorTools = FRecyclerViewAdaptadorGoogleTool(this, arregloGoogleTools)
        recyclerViewTools.adapter = adaptadorTools
        recyclerViewTools.layoutManager = LinearLayoutManager(this)

        adaptadorNews.notifyDataSetChanged()
        adaptadorTools.notifyDataSetChanged()
    }

    private fun abrirActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}
