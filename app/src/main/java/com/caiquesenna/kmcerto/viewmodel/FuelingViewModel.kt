package com.caiquesenna.kmcerto.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.caiquesenna.kmcerto.database.AppDatabase
import com.caiquesenna.kmcerto.model.Fueling
import com.caiquesenna.kmcerto.repository.FuelingRepository
import kotlinx.coroutines.launch

class FuelingViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: FuelingRepository

    init {
        val fuelingDao = AppDatabase.getDatabase(application).fuelingDao()
        repository = FuelingRepository(fuelingDao)
    }

    fun insert(fueling: Fueling) = viewModelScope.launch {
        repository.insert(fueling)
    }

    fun update(fueling: Fueling) = viewModelScope.launch {
        repository.update(fueling)
    }

    fun delete(fueling: Fueling) = viewModelScope.launch {
        repository.delete(fueling)
    }

    fun getFuelingsByVehicle(vehicleId: Int): LiveData<List<Fueling>> {
        return repository.getFuelingsByVehicle(vehicleId)
    }

    fun getLastFueling(): LiveData<Fueling?> {
        return repository.getLastFueling()
    }

    fun getTotalCostBetween(startDate: Long, endDate: Long): LiveData<Double?> {
        return repository.getTotalCostBetween(startDate, endDate)
    }

    fun getAverageKmPerLiter(vehicleId: Int): LiveData<Double?> {
        return repository.getAverageKmPerLiter(vehicleId)
    }

    fun getKmPerLiter(fueling: Fueling): Double {
        return if (fueling.liters == 0.0) {
            0.0
        } else {
            fueling.mileage / fueling.liters
        }
    }

    fun getCostPerKm(fueling: Fueling): Double {
        return if (fueling.totalCost == 0.0) {
            0.0
        } else {
            fueling.totalCost / fueling.mileage
        }
    }
}