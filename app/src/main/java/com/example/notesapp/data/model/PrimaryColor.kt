package com.example.notesapp.data.model

import java.io.Serializable

data class PrimaryColor(
    val colorName: String,
    val hexNumber: String? = null,
    val id: Int,
    var selected: Boolean = false
) : Serializable