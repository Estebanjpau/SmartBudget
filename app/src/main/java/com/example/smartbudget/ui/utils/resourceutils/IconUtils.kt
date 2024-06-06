package com.example.smartbudget.ui.utils.resourceutils

import com.example.smartbudget.R

object IconUtils {
    fun setIconCategory(category: String) : Int {
       return when (category){
            "Entretenimiento" -> R.drawable.c_entertainment
            "Comida" -> R.drawable.c_food
            "Viajes" -> R.drawable.c_travels
            "Hogar" -> R.drawable.c_home
            "Medicina" -> R.drawable.c_medicine
            "Salud" -> R.drawable.c_health
            "Educacion" -> R.drawable.c_education
            "Ocio" -> R.drawable.c_idleness
            "Mascotas" -> R.drawable.c_pets
            "Ropa" -> R.drawable.c_clothes
            "Transporte" -> R.drawable.c_vehicle
            else -> R.drawable.c_others
        }
    }
}