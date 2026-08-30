package com.caiquesenna.kmcerto.repository

import androidx.lifecycle.LiveData
import com.caiquesenna.kmcerto.dao.MaintenanceDao
import com.caiquesenna.kmcerto.model.Maintenance

class MaintenanceRepository (private val maintenanceDao: MaintenanceDao) {
    suspend fun insert(maintenance: Maintenance) : Long {
        return maintenanceDao.insert(maintenance)
    }
    suspend fun update(maintenance: Maintenance) {
        maintenanceDao.update(maintenance)
    }
    suspend fun delete(maintenance: Maintenance) {
        maintenanceDao.delete(maintenance)
    }
    fun getMaintenancesByVehicle(vehicleId: Int): LiveData<List<Maintenance>> {
        return maintenanceDao.getMaintenancesByVehicle(vehicleId)
    }
}