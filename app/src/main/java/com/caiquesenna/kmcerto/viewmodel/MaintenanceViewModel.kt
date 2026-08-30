package com.caiquesenna.kmcerto.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.caiquesenna.kmcerto.database.AppDatabase
import com.caiquesenna.kmcerto.model.Maintenance
import com.caiquesenna.kmcerto.repository.MaintenanceRepository
import kotlinx.coroutines.launch
import java.util.Calendar

class MaintenanceViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MaintenanceRepository

    init {
        val maintenanceDao = AppDatabase.getDatabase(application).maintenanceDao()
        repository = MaintenanceRepository(maintenanceDao)
    }

    fun insert(maintenance: Maintenance) = viewModelScope.launch {
        repository.insert(maintenance)
    }

    fun update(maintenance: Maintenance) = viewModelScope.launch {
        repository.update(maintenance)
    }

    fun delete(maintenance: Maintenance) = viewModelScope.launch {
        repository.delete(maintenance)
    }

    fun getMaintenancesByVehicle(vehicleId: Int): LiveData<List<Maintenance>> {
        return repository.getMaintenancesByVehicle(vehicleId)
    }

    fun getMaintenanceCost(maintenance: Maintenance): Double {
        return maintenance.laborCost + maintenance.partsCost
    }

    fun getMaintenanceWarrantyMileage(maintenance: Maintenance): Double {
        return maintenance.mileage + maintenance.warrantyMileage
    }

    fun getMaintenanceWarrantyDays(maintenance: Maintenance): Int {
        return maintenance.warrantyDays
    }

    fun getMaintenanceWarrantyDate(maintenance: Maintenance): Long {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = maintenance.date
        calendar.add(Calendar.DAY_OF_YEAR, maintenance.warrantyDays)
        return calendar.timeInMillis
    }

    fun getMaintenanceNextWarrantyDate(maintenance: Maintenance): Long {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = maintenance.date
        calendar.add(Calendar.DAY_OF_YEAR, maintenance.warrantyDays)
        return calendar.timeInMillis
    }

    fun getMaintenanceNextWarrantyMileage(maintenance: Maintenance): Double {
        return maintenance.mileage + maintenance.warrantyMileage
    }

    fun getMaintenanceNextWarrantyCategory(maintenance: Maintenance): String {
        return maintenance.category
    }

    fun getMaintenanceNextWarrantyDescription(maintenance: Maintenance): String {
        return maintenance.description
    }

}