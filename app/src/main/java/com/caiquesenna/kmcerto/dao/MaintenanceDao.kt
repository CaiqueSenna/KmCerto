package com.caiquesenna.kmcerto.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.caiquesenna.kmcerto.model.Maintenance

@Dao
interface MaintenanceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(maintenance: Maintenance): Long

    @Update
    suspend fun update(maintenance: Maintenance)

    @Delete
    suspend fun delete(maintenance: Maintenance)

    @Query("SELECT * FROM maintenances WHERE vehicleId = :vehicleId ORDER BY date DESC")
    fun getMaintenancesByVehicle(vehicleId: Int): LiveData<List<Maintenance>>

    @Query("SELECT * FROM maintenances ORDER BY date DESC LIMIT 1")
    fun getLastMaintenance(): LiveData<Maintenance?>

    @Query("SELECT COUNT (*) FROM maintenances")
    fun getMaintenanceCount(): LiveData<Int>


}