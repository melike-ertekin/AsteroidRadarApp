package com.example.asteroidradarapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.asteroidradarapp.domain.Asteroid
import com.example.asteroidradarapp.domain.PictureOfDay
import com.example.asteroidradarapp.repository.database.AsteroidDatabase
import com.example.asteroidradarapp.repository.database.asDomainModelForAsteroid
import com.example.asteroidradarapp.repository.database.asDomainModelForPicture
import com.example.asteroidradarapp.repository.network.*
import com.example.asteroidradarapp.utils.Constants.API_KEY
import com.example.asteroidradarapp.utils.Global.addDayToToday
import com.example.asteroidradarapp.utils.Global.getToday


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.lang.Exception

/*
 * Repository for fetching astreoids and pictureOfDay from network and storing them on the disk
*/
class AsteroidRepository(private val database: AsteroidDatabase) {

    var client: AsteroidApiService = AsteroidApi.retrofitService

    /**
     * Asteroidlist that can be shown on the screen.
     */
    val allAsteroids: LiveData<List<Asteroid>> =
        Transformations.map(database.astreoidsDao.getAllAsteroids()) {
            it?.asDomainModelForAsteroid()
        }


    /**
     * filtered Asteroidlist that can be shown on the screen.
     */

    fun getFilteredAsteroids(selectedDate:String): LiveData<List<Asteroid>> {
        return Transformations.map(database.astreoidsDao.getFilteredAsteroids(selectedDate)){
            Log.d("hey1",selectedDate)
            it?.asDomainModelForAsteroid()
        }
    }


    /**
     * PictureOfDay that can be shown on the screen.
     */
    val pictureOfDay: LiveData<PictureOfDay> =
        Transformations.map(database.pictureOfDayDao.getPictureOfDay()) {
            it?.asDomainModelForPicture()
        }


    suspend fun refreshData(){

        refreshPictureOfDay()
        refreshAsteroids()


    }
    /**
     * Refresh the picture stored in the offline cache.
     *
     * This function uses the IO dispatcher to ensure the database insert database operation
     * happens on the IO dispatcher. By switching to the IO dispatcher using `withContext` this
     * function is now safe to call from any thread including the Main thread.
     *
     * To actually load the picture for use, observe [pictureOfDay]
     */
    private suspend fun refreshPictureOfDay() {

        withContext(Dispatchers.IO) {
            try {
              val databasePictureOfDay = client.getPictureOfTheDay(API_KEY).asDatabaseModel()

                if(databasePictureOfDay.mediaType == "image"){
                    //Clean db
                    database.pictureOfDayDao.deletePictureOfDay()
                    //insert pictureOfDay
                    database.pictureOfDayDao.insertPictureOfDay(databasePictureOfDay)
                }else{
                    Log.d("pictureOfDay.mediaType","video")
                }

            } catch (e: Exception) {
                Log.d("ExceptionInRepo",e.toString())
            }


        }
    }

    /**
     * Refresh the asteroids stored in the offline cache.
     *
     * This function uses the IO dispatcher to ensure the database insert database operation
     * happens on the IO dispatcher. By switching to the IO dispatcher using `withContext` this
     * function is now safe to call from any thread including the Main thread.
     *
     * To actually load the asteroids for use, observe [allAsteroids]
     */
    private suspend fun refreshAsteroids() {
        withContext(Dispatchers.IO) {

            try {
                val asteroidsString = client.getAsteroids(getToday(), addDayToToday(7), API_KEY)
                //val asteroidsString = client.getAsteroids(START_DATE, END_DATE, API_KEY)
                val networkAsteroidsList = parseAsteroidsJsonResult(JSONObject(asteroidsString))
                val databaseAsteroidsList = networkAsteroidsList.asDatabaseModel()
                database.astreoidsDao.insertAll(*databaseAsteroidsList)

            } catch (e: Exception) {
                Log.d("ExceptionInRepo",e.toString())
            }


        }
    }

}