package com.example.rolcampeon.data

import android.util.Log
import com.example.rolcampeon.models.Role
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.concurrent.CompletableFuture

class BDRoles {
    companion object {
        private val database: DatabaseReference = Firebase.database.reference.child("roles")

        // Obtener todos los roles desde Firebase
        fun obtenerRoles(): CompletableFuture<MutableList<Role>> {
            val future = CompletableFuture<MutableList<Role>>()

            database.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val rolesList = mutableListOf<Role>()
                    for (roleSnapshot in snapshot.children) {
                        val role = roleSnapshot.getValue(Role::class.java)
                        Log.d("Firebase", "Role encontrado: ${role?.name}, ${role?.description}")

                        role?.let { rolesList.add(it) }
                    }
                    future.complete(rolesList)
                }

                override fun onCancelled(error: DatabaseError) {
                    future.completeExceptionally(error.toException())
                }
            })

            return future
        }
        // Obtener un rol por su nombre desde Firebase
        fun obtenerRolesPorNombre(nombre: String, callback: (List<Role>) -> Unit) {
            database.orderByChild("name").equalTo(nombre)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val rolesList = mutableListOf<Role>()
                        for (roleSnapshot in snapshot.children) {
                            val role = roleSnapshot.getValue(Role::class.java)
                            Log.d("Firebase", "Champ encontrado: ${role?.name}, ${role?.description}")

                            role?.let { rolesList.add(it) }
                        }
                        callback(rolesList)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        // Manejar el error aquí si es necesario
                    }
                })
        }

        // Agregar un nuevo rol a Firebase
        fun agregarRol(rol: Role) {
            val newRoleRef = database.push()
            newRoleRef.setValue(rol)
        }

        // Actualizar un rol existente en Firebase por su nombre
        fun actualizarRol(nombre: String, nuevoRol: Role) {
            obtenerRolesPorNombre(nombre) { rolesList ->
                if (rolesList.isNotEmpty()) {
                    val roleToUpdate = rolesList[0]
                    val roleRef = database.child(roleToUpdate.name)
                    roleRef.setValue(nuevoRol)
                }
            }
        }

        // Eliminar un rol en Firebase por su nombre
        fun eliminarRol(nombre: String) {
            obtenerRolesPorNombre(nombre) { rolesList ->
                if (rolesList.isNotEmpty()) {
                    val roleToDelete = rolesList[0]
                    val roleRef = database.child(roleToDelete.name)
                    roleRef.removeValue()
                }
            }
        }
    }

}
