package com.caiquesenna.kmcerto.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.caiquesenna.kmcerto.model.Fueling

@Dao
interface FuelingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(fueling: Fueling) : Long

    @Update
    suspend fun update(fueling: Fueling)

    @Delete
    suspend fun delete(fueling: Fueling)

    @Query("SELECT * FROM fuelings WHERE vehicleId = :vehicleId ORDER BY date DESC")
    fun getFuelingsByVehicle(vehicleId: Int) : LiveData<List<Fueling>>

    @Query("SELECT * FROM fuelings ORDER BY date DESC LIMIT 1")
    fun getLastFueling() : LiveData<Fueling?>

    @Query("SELECT SUM(totalCost) FROM fuelings WHERE date BETWEEN :startDate AND :endDate")
    fun getTotalCostBetween(startDate: Long, endDate: Long) : LiveData<Double?>

    @Query("SELECT COUNT(*) FROM fuelings")
    fun getFuelingCount(): LiveData<Int>

    @Query("SELECT AVG(kmPerLiter) FROM fuelings WHERE vehicleId = :vehicleId AND kmPerLiter > 0")
    fun getAverageKmPerLiter(vehicleId: Int): LiveData<Double?>
}