package com.caiquesenna.kmcerto.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.caiquesenna.kmcerto.database.AppDatabase
import com.caiquesenna.kmcerto.model.OilChange
import com.caiquesenna.kmcerto.repository.OilChangeRepository
import kotlinx.coroutines.launch

class OilChangeViewModel (application: Application) : AndroidViewModel(application) {
    private val repository: OilChangeRepository

    init {
        val oilChangeDao = AppDatabase.getDatabase(application).oilChangeDao()
        repository = OilChangeRepository(oilChangeDao)
    }

    fun insert(oilChange: OilChange) = viewModelScope.launch {
        repository.insert(oilChange)
    }

    fun update(oilChange: OilChange) = viewModelScope.launch {
        repository.update(oilChange)
    }

    fun delete(oilChange: OilChange) = viewModelScope.launch {
        repository.delete(oilChange)
    }

    fun getOilChangesByVehicle(vehicleId: Int): LiveData<List<OilChange>> {
        return repository.getOilChangesByVehicle(vehicleId)
    }

    fun getNextOilChange(): LiveData<OilChange?> {
        return repository.getNextOilChange()
    }

    fun getKmToNextOilChange(vehicleId: Int): LiveData<Double?> {
        return repository.getKmToNextOilChange(vehicleId)
    }

    fun getCostPerKm(oilChange: OilChange): Double {
        return if (oilChange.totalCost == 0.0) {
            0.0
        } else {
            oilChange.totalCost / oilChange.mileage
        }
    }

}