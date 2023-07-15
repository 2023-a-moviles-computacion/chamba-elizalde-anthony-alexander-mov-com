package com.example.debercrud

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.example.debercrud.databinding.ActivityMainBinding
import com.example.debercrud.models.Champ
import com.example.debercrud.models.Role
import com.example.debercrud.utils.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import parseChampionsFromJSON
import parseRolesFromJSON
import readJSONFromAssets
import readRolesFromAssets
import java.io.File


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        main()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    fun main() {
        // Ruta del archivo JSON de campeones y roles
        val championsFile = "path/to/champions.json"
        val rolesFile = "path/to/roles.json"
// Cargar los datos de campeones y roles desde los archivos JSON
        val champions = parseChampionsFromJSON(readJSONFromAssets(championsFile))
        val roles = parseRolesFromJSON(readRolesFromAssets(rolesFile))
        // Ejemplo: Mostrar la lista de campeones en la consola
        champions.forEach { champion ->
            println("Nombre: ${champion.name}")
            println("Descripción: ${champion.description}")
            println("Año de lanzamiento: ${champion.releaseYear}")
            println("Roles:")
            champion.roles.forEach { role ->
                println("- ${role.name}")
            }
            println("------------------------")
        }
    }





}