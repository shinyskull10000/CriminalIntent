package com.example.myapplication

import android.content.Context
import androidx.room.Room
import com.example.myapplication.database.CrimeDatabase
import kotlinx.coroutines.flow.Flow
import java.util.UUID

private const val DATABASE_NAME = "crime-database"
private const val ASSET_DATABASE_NAME = "databases/crime-database.db"

class CrimeRepository private constructor(context: Context) {

    private val database: CrimeDatabase = Room
        .databaseBuilder(
            context.applicationContext,
            CrimeDatabase::class.java ,
            DATABASE_NAME
        )
        .createFromAsset(ASSET_DATABASE_NAME) //Changed from DATABASE_NAME to ASSET_DATABASE_NAME
        .build()

    fun getCrimes(): Flow<List<Crime>> = database.crimeDao().getCrimes()
    fun getCrime(id: UUID): Flow<Crime> = database.crimeDao().getCrime(id)


    companion object {
        private var INSTANCE: CrimeRepository? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = CrimeRepository(context)
            }
        }
        fun get(): CrimeRepository {
            return INSTANCE ?:
            throw IllegalStateException("CrimeRepository must be initialized")
        }
    }
}
