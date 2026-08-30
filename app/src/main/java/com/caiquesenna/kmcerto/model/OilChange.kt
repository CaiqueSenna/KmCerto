package com.caiquesenna.kmcerto.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "oil_changes",
    foreignKeys = [ForeignKey(
        entity = Vehicle::class,
        parentColumns = ["id"],
        childColumns = ["vehicleId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["vehicleId"])]
)
data class OilChange(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val vehicleId: Int,
    val date: Long,
    val mileage: Double, //KM no momento da troca
    val oilType: String, // Tipo de óleo
    val viscosity: String, // Viscosidade Ex: 5w30
    val brand: String, // Marca do Óleo
    val quantity: Double, // Litros de Óleo
    val oilFilter: Boolean = true,
    val airFilter: Boolean = false,
    val fuelFilter: Boolean = false,
    val totalCost: Double, // Calculado: quantity * price
    val nextChangeMileage: Double, // KM da próxima troca
    val nextChangeDate: Long, // Data da próxima troca
    val notes: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)
