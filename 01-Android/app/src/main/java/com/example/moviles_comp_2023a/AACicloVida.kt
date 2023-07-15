package com.example.moviles_comp_2023a

import android.os.Bundle
import android.os.PersistableBundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.moviles_comp_2023a.databinding.ActivityAacicloVidaBinding

class AACicloVida : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityAacicloVidaBinding

    var globalText = ""
    fun showSnackbar(texto:String){
        globalText +=texto
        Snackbar.make(findViewById(R.id.cl_ciclo_vida),
            globalText, Snackbar.LENGTH_LONG).setAction("Action",null).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAacicloVidaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_aaciclo_vida)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        showSnackbar("onCreate")
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_aaciclo_vida)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onStart() {
        super.onStart()
        showSnackbar("onStart")
    }
    override fun onResume(){
        super.onResume()
        showSnackbar("onResume")
    }
    override fun onRestart() {
        super.onRestart()
        showSnackbar("onRestar")
    }
    override fun onPause() {
        super.onPause()
        showSnackbar("onPause")
    }
    override fun onStop() {
        super.onStop()
        showSnackbar("onStop")
    }
    override fun onDestroy() {
        super.onDestroy()
        showSnackbar("onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run{
            putString("textoGuardado", globalText)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(
        savedInstanceState: Bundle
    ) {
        super.onRestoreInstanceState(savedInstanceState)
        val textoRecuperado:String? = savedInstanceState.getString("textoGuardado")
        if(textoRecuperado !=null){
            showSnackbar(textoRecuperado)
            globalText = textoRecuperado
        }
    }


}