package com.caiquesenna.kmcerto.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.caiquesenna.kmcerto.database.AppDatabase
import com.caiquesenna.kmcerto.model.Fueling
import com.caiquesenna.kmcerto.model.OilChange
import java.util.Calendar

class DashboardViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getDatabase(application)
    val vehicleCount: LiveData<Int> = db.vehicleDao().getVehicleCount()
    val fuelingCount: LiveData<Int> = db.fuelingDao().getFuelingCount()
    val oilChangeCount: LiveData<Int> = db.oilChangeDao().getOilChangeCount()
    val maintenanceCount: LiveData<Int> = db.maintenanceDao().getMaintenanceCount()
    
    val monthCost: LiveData<Double?> = liveData { 
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        val startMonth = calendar.timeInMillis
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        val endMonth = calendar.timeInMillis
        emitSource(db.fuelingDao().getTotalCostBetween(startMonth, endMonth))
    }

    val yearCost: LiveData<Double?> = liveData {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_YEAR, 1)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        val startYear = calendar.timeInMillis
        calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMaximum(Calendar.DAY_OF_YEAR))
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        val endYear = calendar.timeInMillis
        emitSource(db.fuelingDao().getTotalCostBetween(startYear, endYear))
    }
    val lastFueling: LiveData<Fueling?> = db.fuelingDao().getLastFueling()
    val nextOilChange: LiveData<OilChange?> = db.oilChangeDao().getNextOilChange()
}
