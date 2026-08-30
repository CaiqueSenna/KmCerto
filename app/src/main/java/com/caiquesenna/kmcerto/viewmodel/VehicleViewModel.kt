package com.caiquesenna.kmcerto.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.caiquesenna.kmcerto.database.AppDatabase
import com.caiquesenna.kmcerto.model.Vehicle
import com.caiquesenna.kmcerto.repository.VehicleRepository
import kotlinx.coroutines.launch

class VehicleViewModel (application: Application) : AndroidViewModel(application) {
    private val repository: VehicleRepository
    val allVehicles: LiveData<List<Vehicle>>
    val vehicleCount: LiveData<Int>

    private val _operationResult = MutableLiveData<Result<Long>>()
    val operationResult: LiveData<Result<Long>> = _operationResult

    init {
        val vehicleDao = AppDatabase.getDatabase(application).vehicleDao()
        repository = VehicleRepository(vehicleDao)
        allVehicles = repository.allVehicles
        vehicleCount = repository.vehicleCount
    }
    fun insert(vehicle: Vehicle) = viewModelScope.launch {
        try {
            val id = repository.insert(vehicle)
            _operationResult.postValue(Result.success(id))
        } catch (e: Exception) {
            _operationResult.postValue(Result.failure(e))
        }
    }
    fun update(vehicle: Vehicle) = viewModelScope.launch {
        try {
            repository.update(vehicle)
            _operationResult.postValue(Result.success(-1L))
        } catch (e: Exception){
            _operationResult.postValue(Result.failure(e))
        }
    }
    fun delete(vehicle: Vehicle) = viewModelScope.launch {
        repository.delete(vehicle)
    }
    private val _selectedVehicle = MutableLiveData<Vehicle?>()
    val selectedVehicle: LiveData<Vehicle?> = _selectedVehicle

    fun loadVehicle(vehicleId: Int) = viewModelScope.launch {
        val vehicle = repository.getVehicleById(vehicleId)
        _selectedVehicle.postValue(vehicle)
    }
}