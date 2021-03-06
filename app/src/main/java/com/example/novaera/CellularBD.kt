package com.example.novaera

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Cellular::class, BindCellular::class], version = 1 )
abstract class CellularBD : RoomDatabase(){
    abstract  fun getDaoCellular(): DaoCellular

    companion object {
        @Volatile
        private var INSTANCE: CellularBD? = null

        fun getDaoCellular(context: Context): CellularBD {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                    CellularBD::class.java, "CellularDB")
                    .build()
                INSTANCE = instance
                return instance
            }

        }
    }

}