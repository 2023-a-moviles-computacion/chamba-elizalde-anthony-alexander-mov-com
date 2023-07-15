package com.example.rolcampeon.data

import com.example.rolcampeon.models.Role

class BDRoles {
    companion object {
        val roles = mutableListOf(
            Role(
                "Top",
                "El rol de Top Lane se caracteriza por ser un campeón resistente y capaz de enfrentarse a los oponentes en la línea superior del mapa.",
                "top_icon.png"
            ),
            Role(
                "Jungle",
                "El rol de Jungla se encarga de neutralizar monstruos en la selva y brindar apoyo a las diferentes líneas del mapa.",
                "jungle_icon.png"
            ),
            Role(
                "Mid",
                "El rol de Mid Lane se destaca por tener un control importante del mapa y realizar emboscadas a los oponentes en la línea del medio.",
                "mid_icon.png"
            ),
            Role(
                "ADC",
                "El rol de AD Carry (ADC) se centra en infligir daño a larga distancia y es fundamental en el late game para llevar al equipo a la victoria.",
                "adc_icon.png"
            ),
            Role(
                "Support",
                "El rol de Soporte es responsable de proteger y ayudar al ADC y al equipo en general, brindando control de masas y sanación.",
                "support_icon.png"
            )
        )


        // Obtener todos los roles
        fun obtenerRoles(): List<Role> {
            return roles.toList()
        }

        // Obtener un rol por su nombre
        fun obtenerRolesPorNombre(nombre: String): List<Role> {
            val foundRoles = roles.filter { it.name == nombre }
            return if (foundRoles.isNotEmpty()) {
                foundRoles
            } else {
                roles
            }
        }


        // Agregar un nuevo rol
        fun agregarRol(rol: Role) {
            roles.add(rol)
        }

        // Actualizar un rol existente por su nombre
        fun actualizarRol(nombre: String, nuevoRol: Role) {
            val rolExistente = roles.find { it.name == nombre }
            rolExistente?.let {
                it.name = nuevoRol.name
                it.description = nuevoRol.description
                it.icon = nuevoRol.icon
            }
        }

        // Eliminar un rol por su nombre
        fun eliminarRol(nombre: String) {
            roles.removeAll { it.name == nombre }
        }


    }
}