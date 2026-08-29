package com.caiquesenna.kmcerto.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vehicles")
data class Vehicle(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val brand: String, //Marca
    val model: String, //Modelo
    val year: Int, //Anode Fabricação
    val licensePlate: String, // Placa
    val color: String, //Cor
    val renavam: String? = null, //Renavam
    val chassis: String? = null, //Chassi
    val currentMileage: Double = 0.0, //Quilometragem atual
    val notes: String? = null, //Observações
    val photoPath: String? = null, //Caminho local da foto
    val createdAt: Long = System.currentTimeMillis(), //Data de criação
    val updatedAt: Long = System.currentTimeMillis() //Data de atualização
)
