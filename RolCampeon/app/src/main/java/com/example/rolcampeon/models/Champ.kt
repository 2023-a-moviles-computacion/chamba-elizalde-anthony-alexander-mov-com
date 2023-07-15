package com.example.rolcampeon.models

data class Champ(
    var name: String,
    var description: String,
    var releaseYear: Int,
    var roles: List<Role>
)