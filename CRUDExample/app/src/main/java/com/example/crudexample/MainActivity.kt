package com.example.crudexample


import com.example.crudexample.utils.*
import com.example.debercrud.models.*


fun main() {
    // Ruta del archivo JSON de campeones y roles
    //reemplazar la ruta, con la ruta absoluta de su equipo, esta corresponde a mi ordenador
    val championsFile = "/home/noobgrammer1/Documents/8vo Semestre/AppMoviles/RepositorioMoviles/chamba-elizalde-anthony-alexander-mov-com/CRUDExample/app/assets/champs.json"
    val rolesFile = "/home/noobgrammer1/Documents/8vo Semestre/AppMoviles/RepositorioMoviles/chamba-elizalde-anthony-alexander-mov-com/CRUDExample/app/assets/roles.json"

    var option: Int
        do {
            println("------- MENÚ CRUD -------")
            println("1. Crear campeón")
            println("2. Actualizar campeón")
            println("3. Eliminar campeón")
            println("4. Ver campeones")
            println("5. Crear rol")
            println("6. Actualizar rol")
            println("7. Eliminar rol")
            println("8. Ver roles")
            println("0. Salir")
            println("-------------------------")
            print("Ingresa una opción: ")
            option = readLine()?.toIntOrNull() ?: -1

            when (option) {
                1 -> createChampion(championsFile)
                2 -> updateChampion(championsFile)
                3 -> deleteChampion(championsFile)
                4 -> showChampions(championsFile)
                5 -> createRole(rolesFile)
                6 -> updateRole(rolesFile)
                7 -> deleteRole(rolesFile)
                8 -> showRoles(rolesFile)
                0 -> println("Saliendo...")
                else -> println("Opción inválida. Inténtalo nuevamente.")
            }

            println()
        } while (option != 0)
    }

    fun createChampion(fileName: String) {
        println("--- Crear campeón ---")
        print("Ingrese el nombre del campeón: ")
        val name = readLine()
        print("Ingrese la descripción del campeón: ")
        val description = readLine()
        print("Ingrese el año de lanzamiento del campeón: ")
        val releaseYear = readLine()?.toIntOrNull()

        if (name != null && description != null && releaseYear != null) {
            val champion = Champ(name, description, releaseYear, listOf())
            addChampionToJSON(fileName, champion)
            println("Campeón creado exitosamente.")
        } else {
            println("Datos inválidos. No se pudo crear el campeón.")
        }
    }

    fun updateChampion(fileName: String) {
        println("--- Actualizar campeón ---")
        print("Ingrese el nombre del campeón a actualizar: ")
        val championName = readLine()
        print("Ingrese el nuevo nombre del campeón: ")
        val newName = readLine()
        print("Ingrese la nueva descripción del campeón: ")
        val newDescription = readLine()
        print("Ingrese el nuevo año de lanzamiento del campeón: ")
        val newReleaseYear = readLine()?.toIntOrNull()

        if (championName != null && newName != null && newDescription != null && newReleaseYear != null) {
            val updatedChampion = Champ(newName, newDescription, newReleaseYear, listOf())
            updateChampionInJSON(fileName, championName, updatedChampion)
            println("Campeón actualizado exitosamente.")
        } else {
            println("Datos inválidos. No se pudo actualizar el campeón.")
        }
    }

    fun deleteChampion(fileName: String) {
        println("--- Eliminar campeón ---")
        print("Ingrese el nombre del campeón a eliminar: ")
        val championName = readLine()

        if (championName != null) {
            deleteChampionFromJSON(fileName, championName)
            println("Campeón eliminado exitosamente.")
        } else {
            println("Nombre inválido. No se pudo eliminar el campeón.")
        }
    }

    fun showChampions(fileName: String) {
        println("--- Campeones ---")
        val champions = parseChampionsFromJSON(readJSONFromAssets(fileName))
        champions.forEach { champion ->
            println("Nombre: ${champion.name}")
            println("Descripción: ${champion.description}")
            println("Año de lanzamiento: ${champion.releaseYear}")
            println("Roles:")
            champion.roles.forEach { role ->
                println("Nombre: ${role.name}")
                println("Descripción: ${role.description}")
                println("Icono: ${role.icon}")
            }
            println()
        }
    }

    fun createRole(fileName: String) {
        println("--- Crear rol ---")
        print("Ingrese el nombre del rol: ")
        val name = readLine()
        print("Ingrese la descripción del rol: ")
        val description = readLine()
        print("Ingrese el icono del rol: ")
        val icon = readLine()

        if (name != null && description != null && icon != null) {
            val role = Role(name, description, icon)
            addRoleToJSON(fileName, role)
            println("Rol creado exitosamente.")
        } else {
            println("Datos inválidos. No se pudo crear el rol.")
        }
    }

    fun updateRole(fileName: String) {
        println("--- Actualizar rol ---")
        print("Ingrese el nombre del rol a actualizar: ")
        val roleName = readLine()
        print("Ingrese el nuevo nombre del rol: ")
        val newName = readLine()
        print("Ingrese la nueva descripción del rol: ")
        val newDescription = readLine()
        print("Ingrese el nuevo icono del rol: ")
        val newIcon = readLine()

        if (roleName != null && newName != null && newDescription != null && newIcon != null) {
            val updatedRole = Role(newName, newDescription, newIcon)
            updateRoleInJSON(fileName, roleName, updatedRole)
            println("Rol actualizado exitosamente.")
        } else {
            println("Datos inválidos. No se pudo actualizar el rol.")
        }
    }

fun deleteRole(fileName: String) {
    println("--- Eliminar rol ---")
    print("Ingrese el nombre del rol a eliminar: ")
    val roleName = readLine()

    if (roleName != null) {
        deleteRoleFromJSON(fileName, roleName)
        println("Rol eliminado exitosamente.")
    } else {
        println("Nombre inválido. No se pudo eliminar el rol.")
    }
}

fun showRoles(fileName: String) {
    println("--- Roles ---")
    val roles = parseRolesFromJSON(readJSONFromAssets(fileName))
    roles.forEach { role ->
        println("Nombre: ${role.name}")
        println("Descripción: ${role.description}")
        println("Icono: ${role.icon}")
        println()
    }
}
