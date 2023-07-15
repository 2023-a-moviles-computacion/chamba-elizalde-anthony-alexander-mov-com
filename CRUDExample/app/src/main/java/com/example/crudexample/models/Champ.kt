package com.example.debercrud.models

data class Champ(
    val name: String,
    val description: String,
    val releaseYear: Int,
    val roles: List<Role>
)