package com.example.asteroidradarapp.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DatabaseAsteroid::class, DatabasePictureOfDay::class], version = 1, exportSchema = false)
abstract class AsteroidDatabase: RoomDatabase() {

    abstract val astreoidsDao: AsteroidsDao
    abstract val pictureOfDayDao: PictureOfDayDao

    //companion object allows clients to access the methods for creating or getting the database without instantiating the class
    companion object{

        //@Volatile annotation means value of variable is always up to date and same to all execution threads
        //Value of a volatile variable will never be cached and all writes and reads will be done to and from the main memory
        //It means changes made by one thread to INSTANCE are visible to all other threads immediately.

        @Volatile
        private var INSTANCE: AsteroidDatabase? = null

        fun getDatabase(context: Context): AsteroidDatabase{
            synchronized(this){
                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AsteroidDatabase::class.java,
                        "asteroids")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE =instance
                }

                return instance
            }
        }
    }

}