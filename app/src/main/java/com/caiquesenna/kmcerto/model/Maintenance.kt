package com.caiquesenna.kmcerto.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "maintenances",
    foreignKeys = [ForeignKey(
        entity = Vehicle::class,
        parentColumns = ["id"],
        childColumns = ["vehicleId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["vehicleId"])]
)
data class Maintenance(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val vehicleId: Int,
    val date: Long,
    val mileage: Double,
    val category: String, //Freios, Suspensão, Motor, Eeltrica...
    val description: String,
    val workshop: String, //Nome da oficina
    val city: String,
    val phone: String? = null,
    val laborCost: Double = 0.0,
    val partsCost: Double = 0.0,
    val totalCost: Double = 0.0,
    val warrantyDays: Int = 0,
    val warrantyMileage: Double = 0.0,
    val invoicePhotoPath: String? = null,
    val notes: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)
