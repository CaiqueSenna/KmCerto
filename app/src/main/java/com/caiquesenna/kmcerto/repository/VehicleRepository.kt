package com.caiquesenna.kmcerto.repository

import androidx.lifecycle.LiveData
import com.caiquesenna.kmcerto.dao.VehicleDao
import com.caiquesenna.kmcerto.model.Vehicle

class VehicleRepository(private val vehicleDao: VehicleDao) {
    val allVehicles: LiveData<List<Vehicle>> = vehicleDao.getAllVehicles()
    val vehicleCount: LiveData<Int> = vehicleDao.getVehicleCount()

    suspend fun insert(vehicle: Vehicle) : Long {
        return vehicleDao.insert(vehicle)
    }
    suspend fun update(vehicle: Vehicle) {
        vehicleDao.update(vehicle)
    }
    suspend fun delete(vehicle: Vehicle) {
        vehicleDao.delete(vehicle)
    }
    suspend fun getVehicleById(id: Int): Vehicle? {
        return vehicleDao.getVehicleById(id)
    }
    fun searchVehicles(query: String): LiveData<List<Vehicle>> {
        return vehicleDao.searchVehicles(query)
    }
}