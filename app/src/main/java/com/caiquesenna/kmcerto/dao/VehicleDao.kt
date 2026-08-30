package com.caiquesenna.kmcerto.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.caiquesenna.kmcerto.model.Vehicle


@Dao
interface VehicleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vehicle: Vehicle) : Long

    @Update
    suspend fun update(vehicle: Vehicle)

    @Delete
    suspend fun delete(vehicle: Vehicle)

    @Query("SELECT * FROM vehicles ORDER BY brand ASC, model ASC")
    fun getAllVehicles () : LiveData<List<Vehicle>>

    @Query("SELECT * FROM vehicles WHERE id = :vehicleId")
    suspend fun getVehicleById (vehicleId: Int) : Vehicle?

    @Query("SELECT * FROM vehicles WHERE brand LIKE '%' || :query || '%' " + "OR model LIKE '%' || :query || '%'" + "OR licensePlate LIKE '%' || :query || '%'")
    fun searchVehicles (query: String) : LiveData<List<Vehicle>>

    @Query("SELECT COUNT(*) FROM vehicles")
    fun getVehicleCount () : LiveData<Int>

    @Query("DELETE FROM vehicles WHERE id = :vehicleId")
    suspend fun deleteById(vehicleId: Int)

}