package com.example.rolcampeon

import android.content.DialogInterface
    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
import android.view.*
import android.widget.*
    import androidx.appcompat.app.AlertDialog
import com.example.rolcampeon.data.BDChamps
import com.example.rolcampeon.data.BDRoles
import com.example.rolcampeon.models.Champ
import com.example.rolcampeon.models.Role

class ChampsActivity : AppCompatActivity() {
    lateinit var role: List<Role>
    lateinit var champs: List<Champ>
    var positionChamp = 0
    var name = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_champs)

        name = intent.getStringExtra("name") ?: ""
        val tvNombreRol = findViewById<TextView>(R.id.rolTextView)

        if (name.isNullOrEmpty()) {
            tvNombreRol.text = ""
        } else {
            // Llama a obtenerRolesPorNombre con un callback
            BDRoles.obtenerRolesPorNombre(name) { rolesList ->
                role = rolesList
                tvNombreRol.text = role[0].name
            }
        }
        BDChamps.leerChamps {  }

        champs = BDChamps.champs ?: ArrayList()
        val listViewProgramas = findViewById<ListView>(R.id.lv_champs)

        val adaptador = object : ArrayAdapter<Champ>(
            this,
            R.layout.list_item_champ,
            champs
        ) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item_champ, parent, false)

                val nombreTextView = view.findViewById<TextView>(R.id.nombreTextView)
                val descripcionTextView = view.findViewById<TextView>(R.id.descripcionTextView)
                val releaseYearTextView = view.findViewById<TextView>(R.id.releaseYearTextView)
                val roleTextView = view.findViewById<TextView>(R.id.roleTextView)

                val champ = champs[position]
                nombreTextView.text = champ.name
                descripcionTextView.text = champ.description
                releaseYearTextView.text = champ.releaseYear.toString()
                roleTextView.text = champ.roles.firstOrNull()?.name ?: ""

                return view
            }
        }

        listViewProgramas.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonCrearPrograma = findViewById<Button>(R.id.agregarCampeonButton)
        botonCrearPrograma.setOnClickListener() {
            anadirPrograma(adaptador)
        }

        registerForContextMenu(listViewProgramas)
    }

            override fun onCreateContextMenu(
            menu: ContextMenu?,
            v: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
        ) {
            super.onCreateContextMenu(menu, v, menuInfo)
            val inflater = menuInflater
            inflater.inflate(R.menu.menu_champ, menu)
        }

        override fun onContextItemSelected(item: MenuItem): Boolean {
            val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
            positionChamp = info.position

            return when(item.itemId){
                R.id.mi_editar_champ -> {
                    editarPrograma()
                    true
                }
                R.id.mi_eliminar_champ -> {
                    abrirDialogo()
                    true
                }
                else -> super.onContextItemSelected(item)
            }
        }

        fun abrirDialogo(){
            val builder = AlertDialog.Builder(this)
            builder.setTitle("¿Desea eliminar el programa?")
            builder.setPositiveButton(
                "Aceptar",
                DialogInterface.OnClickListener { dialog, which ->
                    eliminarPrograma()
                }
            )
            builder.setNegativeButton("Cancelar", null)

            val dialogo = builder.create()
            dialogo.show()
        }

        fun eliminarPrograma() {
            BDChamps.champs.removeAt(positionChamp)

            val listViewProgramas = findViewById<ListView>(R.id.lv_champs)
            val adaptador = listViewProgramas.adapter as ArrayAdapter<String>
            adaptador.notifyDataSetChanged()
        }

    fun editarPrograma() {
        val champ = champs[positionChamp]

        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_edit_champ, null)
        dialogBuilder.setView(dialogView)

        val nombreEditText = dialogView.findViewById<EditText>(R.id.nombreChampEditText)
        val descripcionEditText = dialogView.findViewById<EditText>(R.id.descripcionChampEditText)
        val releaseYearEditText = dialogView.findViewById<EditText>(R.id.releaseYearEditText)
        val roleEditText = dialogView.findViewById<EditText>(R.id.champFirstRole)

        nombreEditText.setText(champ.name)
        descripcionEditText.setText(champ.description)
        releaseYearEditText.setText(champ.releaseYear.toString())
        roleEditText.setText(champ.roles[0].name)

        dialogBuilder.setTitle("Editar Campeon")
        dialogBuilder.setPositiveButton("Guardar") { dialog, which ->
            val nombre = nombreEditText.text.toString()
            val descripcion = descripcionEditText.text.toString()
            val releaseYear = releaseYearEditText.text.toString()

            // Llama a obtenerRolesPorNombre con un callback
            BDRoles.obtenerRolesPorNombre(roleEditText.text.toString()) { champRole ->
                val champActualizado = Champ(nombre, descripcion, releaseYear.toInt(), champRole)
                BDChamps.actualizarChamp(champ.name, champActualizado)

                // Actualiza la vista aquí si es necesario
                val listView = findViewById<ListView>(R.id.lv_roles)
                val adaptador = listView.adapter as ArrayAdapter<Role>
                adaptador.notifyDataSetChanged()
            }
        }
        dialogBuilder.setNegativeButton("Cancelar", null)

        val dialog = dialogBuilder.create()
        dialog.show()
    }

    fun anadirPrograma(adaptador: ArrayAdapter<Champ>) {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_edit_champ, null)
        dialogBuilder.setView(dialogView)

        val nombreEditText = dialogView.findViewById<EditText>(R.id.nombreChampEditText)
        val descripcionEditText = dialogView.findViewById<EditText>(R.id.descripcionChampEditText)
        val releaseYearEditText = dialogView.findViewById<EditText>(R.id.releaseYearEditText)
        val roleEditText = dialogView.findViewById<EditText>(R.id.champFirstRole)

        dialogBuilder.setTitle("Agregar Nuevo Rol")
        dialogBuilder.setPositiveButton("Agregar") { dialog, which ->
            val nombre = nombreEditText.text.toString()
            val descripcion = descripcionEditText.text.toString()
            val releaseYear = releaseYearEditText.text.toString()

            // Llama a obtenerRolesPorNombre con un callback
            BDRoles.obtenerRolesPorNombre(roleEditText.text.toString()) { champRole ->
                val nuevoChamp = Champ(nombre, descripcion, releaseYear.toInt(), champRole)
                BDChamps.champs.add(nuevoChamp)

                // Actualiza la vista aquí si es necesario
                val listView = findViewById<ListView>(R.id.lv_roles)
                val adaptador = listView.adapter as ArrayAdapter<Role>
                adaptador.notifyDataSetChanged()
            }
        }
        dialogBuilder.setNegativeButton("Cancelar", null)

        val dialog = dialogBuilder.create()
        dialog.show()
    }

}
