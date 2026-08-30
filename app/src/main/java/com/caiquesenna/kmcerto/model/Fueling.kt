package com.caiquesenna.kmcerto.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = "fuelings",
    foreignKeys = [ForeignKey(
        entity = Vehicle::class,
        parentColumns = ["id"],
        childColumns = ["vehicleId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["vehicleId"])]
)
data class Fueling(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val vehicleId: Int, // FK para vehicle.id
    val date: Long, //Data em milissegundos
    val mileage: Double, //Quilometragem no momento
    val gasStation: String, // Nome do Posto
    val city: String, // Cidade
    val fuelType: String, // Gasolina, Etanol, Diesel, GNV
    val pricePerLiter: Double, // Preço do litro
    val liters: Double, // Quantidade abastecida
    val totalCost: Double, //Calculado: liters * pricePerLiter
    val kmPerLiter: Double = 0.0, //Consumo: KM percorridos / litros
    val costPerKm: Double = 0.0, //Custo por KM: totalCost / KM percorridos
    val notes: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)
