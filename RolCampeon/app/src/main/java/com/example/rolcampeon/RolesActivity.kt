package com.example.rolcampeon

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rolcampeon.data.BDRoles
import com.example.rolcampeon.models.Role


class RolesActivity : AppCompatActivity() {

    var array: List<Role> = emptyList() // Inicializa con una lista vacía o algún valor predeterminado

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_roles)

        val listView = findViewById<ListView>(R.id.lv_roles)

        // Llama a obtenerRoles y espera a que se complete antes de continuar
        val rolesFuture = BDRoles.obtenerRoles()
        try {
            array = rolesFuture.get() // Esto bloqueará hasta que se complete la operación
        } catch (e: Exception) {
            // Maneja cualquier excepción que pueda ocurrir al obtener los roles
            Log.e("Firebase", "Error al obtener roles: ${e.message}")
        }

        val adaptador = object : ArrayAdapter<Role>(
            this,
            R.layout.list_item_role,
            array.toTypedArray()
        ) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item_role, parent, false)
                val roleNameTextView = view.findViewById<TextView>(R.id.nameTextView)
                val roleDescriptionTextView = view.findViewById<TextView>(R.id.descriptionTextView)

                val roleName = array[position].name
                val roleDescription = array[position].description

                roleNameTextView.text = roleName
                roleDescriptionTextView.text = roleDescription
                return view
            }
        }

        listView.adapter = adaptador

        val btnAddListView = findViewById<Button>(R.id.btn_anadir_list_view)
        btnAddListView.setOnClickListener() {
            agregarNuevoRol()
        }

        registerForContextMenu(listView)
    }





    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val position = info.position
        return when(item.itemId){
            R.id.mi_editar ->{
                editarRol(array[position])
                //Hacer algo con idSeleccionado
                return true
            }
            R.id.mi_eliminar ->{
                abrirDialogo(position)
                //Hacer algo con idSeleccionado
                return true
            }
            R.id.mi_ver_champs -> {
                mostrarCampeonesFiltrados(array[position])
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
    fun abrirDialogo(position: Int){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea elminar?")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener(){
                    dialog, which ->  eliminarElemento(position)//Hacer Algo
            }
        )
        builder.setNegativeButton("Cancelar", null)

        val dialogo = builder.create()
        dialogo.show()
    }

    fun eliminarElemento(position: Int) {
        // Asegúrate de que array sea una MutableList<Role>
        val array = BDRoles.obtenerRoles().get() ?: mutableListOf()

        // Verifica si la posición es válida
        if (position >= 0 && position < array.size) {
            // Elimina el elemento localmente
            array.removeAt(position)



            val listView = findViewById<ListView>(R.id.lv_roles)
            val adaptador = listView.adapter as ArrayAdapter<Role>
            adaptador.notifyDataSetChanged()
        }
    }


    private fun agregarNuevoRol() {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_nuevo_rol, null)
        dialogBuilder.setView(dialogView)

        val nombreEditText = dialogView.findViewById<EditText>(R.id.nombreEditText)
        val descripcionEditText = dialogView.findViewById<EditText>(R.id.descripcionEditText)
        val iconoEditText = dialogView.findViewById<EditText>(R.id.iconoEditText)

        dialogBuilder.setTitle("Agregar Nuevo Rol")
        dialogBuilder.setPositiveButton("Agregar") { dialog, which ->
            val nombre = nombreEditText.text.toString()
            val descripcion = descripcionEditText.text.toString()
            val icono = iconoEditText.text.toString()

            val nuevoRol = Role(nombre, descripcion, icono)
            BDRoles.agregarRol(nuevoRol)
            val listView = findViewById<ListView>(R.id.lv_roles)
            val adaptador = listView.adapter as ArrayAdapter<Role>
            adaptador.notifyDataSetChanged()
        }
        dialogBuilder.setNegativeButton("Cancelar", null)

        val dialog = dialogBuilder.create()
        dialog.show()
    }
    private fun mostrarCampeonesFiltrados(rol: Role) {
        val nombreRol = rol.name
        val intent = Intent(this, ChampsActivity::class.java)
        intent.putExtra("name", nombreRol)
        startActivity(intent)
    }


    private fun editarRol(rol: Role) {

        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_nuevo_rol, null)
        dialogBuilder.setView(dialogView)

        val nombreEditText = dialogView.findViewById<EditText>(R.id.nombreEditText)
        val descripcionEditText = dialogView.findViewById<EditText>(R.id.descripcionEditText)
        val iconoEditText = dialogView.findViewById<EditText>(R.id.iconoEditText)

        nombreEditText.setText(rol.name)
        descripcionEditText.setText(rol.description)
        iconoEditText.setText(rol.icon)

        dialogBuilder.setTitle("Editar Rol")
        dialogBuilder.setPositiveButton("Guardar") { dialog, which ->
            val nombre = nombreEditText.text.toString()
            val descripcion = descripcionEditText.text.toString()
            val icono = iconoEditText.text.toString()

            val rolActualizado = Role(nombre, descripcion, icono)
            BDRoles.actualizarRol(rol.name, rolActualizado)
            val listView = findViewById<ListView>(R.id.lv_roles)
            val adaptador = listView.adapter as ArrayAdapter<Role>
            adaptador.notifyDataSetChanged()
        }
        dialogBuilder.setNegativeButton("Cancelar", null)

        val dialog = dialogBuilder.create()
        dialog.show()
    }


}
