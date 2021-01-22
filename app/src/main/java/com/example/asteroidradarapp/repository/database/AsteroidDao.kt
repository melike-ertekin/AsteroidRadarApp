package com.example.asteroidradarapp.repository.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query



//Data Access Object
@Dao
interface AsteroidsDao {

    // vararg -> multiple asteroid objects

    /*
   insertAll() is an upsert, so don’t forget to pass it onConflict=OnConflictStrategy.REPLACE!
   which solve the conflict when we try to add same primary key object to db again.
   */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg asteroids: DatabaseAsteroid)

    // Once we have all the asteroids we don't have to manage getting them again every time there is a change to any asteroids when we use Live data
    @Query("SELECT * FROM asteroids ORDER BY date DESC")
    fun getAllAsteroids(): LiveData<List<DatabaseAsteroid>>  // No need for a suspend function since LiveData is already asynchronous.


    @Query("SELECT * FROM asteroids WHERE date = :selectedDate")
    fun getFilteredAsteroids(selectedDate: String): LiveData<List<DatabaseAsteroid>>


    @Query("DELETE FROM asteroids")
    suspend fun deleteAllAsteroids()

}

@Dao
interface PictureOfDayDao {

    //It will return 1 picture
    @Query("SELECT * FROM pictureOfDay")
    fun getPictureOfDay(): LiveData<DatabasePictureOfDay>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPictureOfDay(pictureOfDay: DatabasePictureOfDay)

    @Query("DELETE FROM pictureOfDay")
    suspend fun deletePictureOfDay()
}