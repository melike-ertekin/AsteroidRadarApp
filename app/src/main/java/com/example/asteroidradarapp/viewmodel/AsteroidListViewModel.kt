package com.example.asteroidradarapp.viewmodel


import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.asteroidradarapp.domain.Asteroid
import com.example.asteroidradarapp.repository.AsteroidRepository
import com.example.asteroidradarapp.repository.database.AsteroidDatabase.Companion.getDatabase
import com.example.asteroidradarapp.utils.Global.getToday
import kotlinx.coroutines.launch


/**
 * AsteroidListViewModel designed to store and manage UI-related data in a lifecycle conscious way. This
 * allows data to survive configuration changes such as screen rotations. In addition, background
 * work such as fetching network results can continue through configuration changes and deliver
 * results after the new Fragment or Activity is available.
 *
 * @param application The application that this viewmodel is attached to, it's safe to hold a
 * reference to applications across rotation since Application is never recreated during activity
 * or fragment lifecycle events.
 */

class AsteroidListViewModel(application: Application) : AndroidViewModel(application){

    private val database = getDatabase(application)

    private val asteroidsRepository: AsteroidRepository = AsteroidRepository(database)

    private val asteroidFilter = MutableLiveData(AsteroidFilter.ALL_ASTEROIDS)

    val pictureOfDay = asteroidsRepository.pictureOfDay

    private val selectedDate = MutableLiveData<String>()

    val asteroidList = Transformations.switchMap(asteroidFilter) {
        when (it!!) {
            AsteroidFilter.ALL_ASTEROIDS -> asteroidsRepository.allAsteroids
            AsteroidFilter.FILTER_ASTEROIDS -> {
                selectedDate.value?.let { it1 -> asteroidsRepository.getFilteredAsteroids(it1) }
            }
        }
    }
    /**
     * init{} is called immediately when this ViewModel is created.
     */
    init {

        viewModelScope.launch {
            asteroidsRepository.refreshData()
        }

    }


    private val _navigateToSelectedAsteroid = MutableLiveData<Asteroid>()

    val navigateToSelectedAsteroid: LiveData<Asteroid>
        get() = _navigateToSelectedAsteroid

    fun displayAsteroidDetails(asteroid: Asteroid) {
        _navigateToSelectedAsteroid.value = asteroid
    }
    fun displayAsteroidDetailsComplete() {
        _navigateToSelectedAsteroid.value = null
    }

    fun setAsteroidFilter(filter: AsteroidFilter) {
        asteroidFilter.postValue(filter)
    }

    fun updateFilter(selectedDate: String) {

        this.selectedDate.postValue(selectedDate)
        setAsteroidFilter(AsteroidFilter.FILTER_ASTEROIDS)


    }


    /**
     * Factory for constructing AsteroidListViewModel with parameter
     */
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AsteroidListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AsteroidListViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}

enum class AsteroidFilter {
    ALL_ASTEROIDS,
    FILTER_ASTEROIDS
}