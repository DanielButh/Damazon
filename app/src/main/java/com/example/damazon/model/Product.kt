package com.example.damazon.model

import android.icu.text.CaseMap.Title
import com.google.gson.annotations.SerializedName

data class Product(
    val id: String,
    val title: String,
    val price: Double,
    @SerializedName("description") val description: String,
    val category: String,
    val image: String
)

/*
Los nombres tienen que ser iguales a los de las APIs
A veces las APIs los tienen con snakecase
En esas ocasiones usaremos:
    @SerializedName(aquí va el nombre de la llave como está en la API) val Aquí ya podemos usar el nombre que queramos
    La clase @SerializedName tiene que ser importada. Viene de gson
*/