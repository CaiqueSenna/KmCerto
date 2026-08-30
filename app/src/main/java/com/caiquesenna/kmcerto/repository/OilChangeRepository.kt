package com.caiquesenna.kmcerto.repository

import androidx.lifecycle.LiveData
import com.caiquesenna.kmcerto.dao.OilChangeDao
import com.caiquesenna.kmcerto.model.OilChange

class OilChangeRepository (private val oilChangeDao: OilChangeDao) {
    suspend fun insert(oilChange: OilChange) : Long {
        return oilChangeDao.insert(oilChange)
    }
    suspend fun update(oilChange: OilChange) {
        oilChangeDao.update(oilChange)
    }
    suspend fun delete(oilChange: OilChange) {
        oilChangeDao.delete(oilChange)
    }
    fun getOilChangesByVehicle(vehicleId: Int): LiveData<List<OilChange>> {
        return oilChangeDao.getOilChangesByVehicle(vehicleId)
    }
    fun getNextOilChange(): LiveData<OilChange?> {
        return oilChangeDao.getNextOilChange()
    }

    fun getKmToNextOilChange(vehicleId: Int): LiveData<Double?> {}
    fun getAverageKmPerLiter(vehicleId: Int): androidx.lifecycle.LiveData<Double?> {}
}
