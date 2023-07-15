package com.example.crudexample.utils
import com.example.debercrud.models.Champ
import com.example.debercrud.models.Role
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.OutputStreamWriter

// Leer el archivo JSON
fun readJSONFromAssets(fileName: String): String {
    return File(fileName).readText()
}

// Convertir el JSON a objetos
fun parseChampionsFromJSON(jsonString: String): List<Champ> {
    val champions: List<Champ> = Gson().fromJson(jsonString, object : TypeToken<List<Champ>>() {}.type)
    return champions
}

// Agregar un nuevo campeón al archivo JSON
fun addChampionToJSON(fileName: String, champion: Champ) {
    val jsonString: String = readJSONFromAssets(fileName)
    val champions: MutableList<Champ> = parseChampionsFromJSON(jsonString).toMutableList()
    champions.add(champion)

    val newJsonString: String = Gson().toJson(champions)
    saveJSONToAssets(fileName, newJsonString)
}

// Guardar el archivo JSON modificado
fun saveJSONToAssets(fileName: String, jsonString: String) {
    try {
        val outputStreamWriter = OutputStreamWriter(File(fileName).outputStream())
        outputStreamWriter.write(jsonString)
        outputStreamWriter.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

// Roles part

fun readRolesFromAssets(fileName: String): String {
    return File(fileName).readText()
}

fun parseRolesFromJSON(jsonString: String): List<Role> {
    val roles: List<Role> = Gson().fromJson(jsonString, object : TypeToken<List<Role>>() {}.type)
    return roles
}

fun saveJSONRolesToAssets(fileName: String, jsonString: String) {
    try {
        val outputStreamWriter = OutputStreamWriter(File(fileName).outputStream())
        outputStreamWriter.write(jsonString)
        outputStreamWriter.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun addRoleToJSON(fileName: String, role: Role) {
    val jsonString: String = readRolesFromAssets(fileName)
    val roles: MutableList<Role> = parseRolesFromJSON(jsonString).toMutableList()
    roles.add(role)

    val newJsonString: String = Gson().toJson(roles)
    saveJSONRolesToAssets(fileName, newJsonString)
}

fun deleteChampionFromJSON(fileName: String, championName: String) {
    val jsonString: String = readJSONFromAssets(fileName)
    val champions: MutableList<Champ> = parseChampionsFromJSON(jsonString).toMutableList()

    val championToRemove = champions.find { it.name == championName }
    if (championToRemove != null) {
        champions.remove(championToRemove)
        val newJsonString: String = Gson().toJson(champions)
        saveJSONToAssets(fileName, newJsonString)
    }
}

fun deleteRoleFromJSON(fileName: String, roleName: String) {
    val jsonString: String = readRolesFromAssets(fileName)
    val roles: MutableList<Role> = parseRolesFromJSON(jsonString).toMutableList()

    val roleToRemove = roles.find { it.name == roleName }
    if (roleToRemove != null) {
        roles.remove(roleToRemove)
        val newJsonString: String = Gson().toJson(roles)
        saveJSONRolesToAssets(fileName, newJsonString)
    }
}
fun updateChampionInJSON(fileName: String, championName: String, updatedChampion: Champ) {
    val jsonString: String = readJSONFromAssets(fileName)
    val champions: MutableList<Champ> = parseChampionsFromJSON(jsonString).toMutableList()

    val championToUpdate = champions.find { it.name == championName }
    if (championToUpdate != null) {
        val championIndex = champions.indexOf(championToUpdate)
        champions[championIndex] = updatedChampion
        val newJsonString: String = Gson().toJson(champions)
        saveJSONToAssets(fileName, newJsonString)
    }
}
fun updateRoleInJSON( fileName: String, roleName: String, updatedRole: Role) {
    val jsonString: String = readRolesFromAssets(fileName)
    val roles: MutableList<Role> = parseRolesFromJSON(jsonString).toMutableList()

    val roleToUpdate = roles.find { it.name == roleName }
    if (roleToUpdate != null) {
        val roleIndex = roles.indexOf(roleToUpdate)
        roles[roleIndex] = updatedRole
        val newJsonString: String = Gson().toJson(roles)
        saveJSONRolesToAssets(fileName, newJsonString)
    }
}