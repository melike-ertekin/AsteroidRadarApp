package com.example.asteroidradarapp

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}/*

package com.example.asteroidradarapp

import androidx.room.Room
import com.example.asteroidradarapp.model.Asteroid
import com.example.asteroidradarapp.repository.room.AsteroidDao
import com.example.asteroidradarapp.repository.room.AsteroidDatabase
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.time.LocalDate
import kotlin.jvm.Throws

//@RunWith(AndroidJUnit4::class)
class AsteroidDatabaseTest {

    private lateinit var asteroidDao: AsteroidDao
    private lateinit var db: AsteroidDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, AsteroidDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        asteroidDao = db.astreoidDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    suspend fun insertAndGetAsteroids() {
        val asteroid1 = Asteroid(1, "001", "07.07.2020", 1.0, 1.0, 1.0, 1.0, true, LocalDate.now())
        val asteroid2 = Asteroid(2, "002", "07.07.2020", 2.0, 2.0, 2.0, 2.0, true, LocalDate.now())
        val asteroidList = arrayListOf<Asteroid>(asteroid1, asteroid2)
        asteroidDao.insertAll(*asteroidList.toTypedArray())
        val list = asteroidDao.getAllAsteroids()
        assertEquals(list?.value, asteroidList)
    }
}


*/