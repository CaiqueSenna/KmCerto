package com.caiquesenna.kmcerto.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.caiquesenna.kmcerto.model.OilChange

@Dao
interface OilChangeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(oilChange: OilChange) : Long

    @Update
    suspend fun update(oilChange: OilChange)

    @Delete
    suspend fun delete(oilChange: OilChange)

    @Query("SELECT * FROM oil_changes WHERE vehicleId = :vehicleId ORDER BY date DESC")
    fun getOilChangesByVehicle(vehicleId: Int) : LiveData<List<OilChange>>

    @Query("SELECT * FROM oil_changes ORDER BY nextChangeDate ASC LIMIT 1")
    fun getNextOilChange(): LiveData<OilChange?>

    @Query("SELECT COUNT (*) FROM oil_changes")
    fun getOilChangeCount(): LiveData<Int>

}