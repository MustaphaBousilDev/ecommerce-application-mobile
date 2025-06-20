package com.mustapha.application_android_kotlin.database.models

data class Product(
    val id: String,
    val name: String,
    val price: Double,
    val imageRes: Int,
    val category: String,
    val rating: Float = 4.5f,
    val isNew: Boolean = false,
    val discount: Int? = null
)