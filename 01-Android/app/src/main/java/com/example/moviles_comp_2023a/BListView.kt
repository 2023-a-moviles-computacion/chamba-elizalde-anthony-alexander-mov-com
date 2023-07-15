package com.example.moviles_comp_2023a

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView


class BListView : AppCompatActivity() {
    val arreglo = BBaseDatosMemoria.arregloBEntrenador
    var idItemSeleccionado = 0
    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)
        val listView = findViewById<ListView> (R.id.lv_list_view)
        val adaptador = ArrayAdapter(
            this, // Contexto
            android.R.layout.simple_list_item_1, // como se va a ver (XML)
            arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonAniadirListView = findViewById<Button>(R.id.btn_anadir_list_view)
        botonAniadirListView.setOnClickListener{
            anadirEntrenador(adaptador)
        }
        registerForContextMenu(listView)
    }

    fun anadirEntrenador(
        adaptador: ArrayAdapter<BEntrenador>){
        arreglo.add(
        BEntrenador (
        1,
    "Adrian",
            "Descripcion"
       )
    )
    adaptador.notifyDataSetChanged()
}

    override fun onCreateContextMenu(
        menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // Llenamos las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        // Obtener el ia del ArrayListSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
        R.id.mi_editar ->{
            "${idItemSeleccionado}"
            return true
        }
        R.id.mi_eliminar ->{
            "${idItemSeleccionado}"
            abrirDialogo()
            return true
        }
        else -> super.onContextItemSelected(item)
        }
    }

    fun abrirDialogo(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea eliminar")
        builder.setPositiveButton(
                "Aceptar",
        DialogInterface.OnClickListener { dialog, which ->
        })
        builder.setNegativeButton(
            "Cancelar",
            null
        )
        val opciones = resources.getStringArray(
            R.array.string_array_opciones_dialogo
        )
        val seleccionPrevia = booleanArrayOf(
            true, // Lunes seleccionado
            false, // Martes NO seleccionado
            false, // Hiercoles NO seleccionado
        )
            builder.setMultiChoiceItems( opciones, seleccionPrevia, { dialog, which, isChecked ->
                "Dio clic en el item ${which}"
            }
            )
            val dialogo = builder.create()
            dialogo.show()
    }
}