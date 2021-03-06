package com.example.novaera

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DaoCellular {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun InsertAllDaoCellular(list: List<Cellular>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertBindCellular(list: List<BindCellular>)

    @Query("SELECT * FROM cellular_table")
    fun getAllCellular(): LiveData<List<Cellular>>

    @Query("SELECT * FROM bind_table WHERE id = :id")
    fun getBindCellular(id:Int) : LiveData<List<BindCellular>>

    @Update
    suspend fun updateCellular(cellular: Cellular)

}