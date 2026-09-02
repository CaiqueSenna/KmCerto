package com.caiquesenna.kmcerto.utils

import java.text.NumberFormat
import java.util.Locale

object CurrencyUtils {
    fun format(value: Double): String {
        val ptBr = Locale("pt", "BR")
        return NumberFormat.getCurrencyInstance(ptBr).format(value)
    }
}