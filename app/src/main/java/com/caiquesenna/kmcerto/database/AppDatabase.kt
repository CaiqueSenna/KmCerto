package com.caiquesenna.kmcerto.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.caiquesenna.kmcerto.dao.FuelingDao
import com.caiquesenna.kmcerto.dao.MaintenanceDao
import com.caiquesenna.kmcerto.dao.OilChangeDao
import com.caiquesenna.kmcerto.dao.VehicleDao
import com.caiquesenna.kmcerto.model.Fueling
import com.caiquesenna.kmcerto.model.Maintenance
import com.caiquesenna.kmcerto.model.OilChange
import com.caiquesenna.kmcerto.model.Vehicle


@Database (
    entities = [
        Vehicle::class,
        Fueling::class,
        OilChange::class,
        Maintenance::class
               ],
    version = 1,
    exportSchema = false)

abstract class AppDatabase : RoomDatabase() {
    abstract fun vehicleDao () : VehicleDao
    abstract fun fuelingDao () : FuelingDao
    abstract fun oilChangeDao () : OilChangeDao
    abstract fun maintenanceDao () : MaintenanceDao
}

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase{
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "controle_veiculos_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}