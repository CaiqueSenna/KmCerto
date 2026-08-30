package com.caiquesenna.kmcerto.repository

import androidx.lifecycle.LiveData
import com.caiquesenna.kmcerto.dao.FuelingDao
import com.caiquesenna.kmcerto.model.Fueling

class FuelingRepository (private val fuelingDao: FuelingDao) {
    suspend fun insert(fueling: Fueling) : Long {
        return fuelingDao.insert(fueling)
    }
    suspend fun update(fueling: Fueling) {
        fuelingDao.update(fueling)
    }
    suspend fun delete(fueling: Fueling) {
        fuelingDao.delete(fueling)
    }
    fun getFuelingsByVehicle(vehicleId: Int): LiveData<List<Fueling>> {
        return fuelingDao.getFuelingsByVehicle(vehicleId)
    }
    fun getLastFueling(): LiveData<Fueling?> {
        return fuelingDao.getLastFueling()
    }
    fun getTotalCostBetween(startDate: Long, endDate: Long): LiveData<Double?> {
        return fuelingDao.getTotalCostBetween(startDate, endDate)
    }
    fun getAverageKmPerLiter(vehicleId: Int): LiveData<Double?> {
        return fuelingDao.getAverageKmPerLiter(vehicleId)
    }
}