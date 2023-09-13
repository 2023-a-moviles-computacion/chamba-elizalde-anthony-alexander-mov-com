package com.example.rolcampeon.data

import com.example.rolcampeon.models.Champ
import com.google.firebase.database.*

class BDChamps {
    companion object {
        private val database: DatabaseReference = FirebaseDatabase.getInstance().reference.child("champs")
        var champs: MutableList<Champ> = mutableListOf()


        fun leerChamps(callback: (List<Champ>) -> Unit) {
            database.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val champsList = mutableListOf<Champ>()
                    for (champSnapshot in snapshot.children) {
                        val champ = champSnapshot.getValue(Champ::class.java)
                        champ?.let { champsList.add(it) }
                    }
                    champs = champsList // Actualizar la lista de campeones
                    callback(champsList)
                }

                override fun onCancelled(error: DatabaseError) {
                    // Manejar el error de lectura de la base de datos
                }
            })
        }

        fun crearChamp(champ: Champ) {
            val champRef = database.push()
            champRef.setValue(champ)
        }

        fun actualizarChamp(nombre: String, nuevoChamp: Champ) {
            val champRef = database.child(nombre)
            champRef.setValue(nuevoChamp)
        }

        fun eliminarChamp(nombreChamp: String) {
            val champRef = database.child(nombreChamp)
            champRef.removeValue()
        }
    }
}
