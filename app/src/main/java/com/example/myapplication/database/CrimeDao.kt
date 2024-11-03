package com.example.myapplication.database

import androidx.room.Dao
import androidx.room.Query
import com.example.myapplication.Crime
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface CrimeDao {
    @Query("SELECT * FROM crime")
    fun getCrimes(): Flow<List<Crime>>
    @Query("SELECT * FROM crime WHERE id=(:id)")
    fun getCrime(id: UUID): Flow<Crime>  // Change to Flow<Crime> for consistency
}
    //fun getCrime(id: UUID): Crime
