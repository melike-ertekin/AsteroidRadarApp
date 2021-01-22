package com.example.asteroidradarapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.asteroidradarapp.domain.Asteroid

class DetailAsteroidViewModel(asteroid: Asteroid, app: Application): AndroidViewModel(app)  {

    private val _selectedAsteroid = MutableLiveData<Asteroid>()

    // The external LiveData for the SelectedProperty
    val selectedAsteroid: LiveData<Asteroid>
        get() = _selectedAsteroid

    // Initialize the _selectedProperty MutableLiveData
    init {
        _selectedAsteroid.value = asteroid
    }




    /**
     * Simple ViewModel factory that provides the Asteroid and context to the ViewModel.
     */
    class Factory(
        private val asteroid: Asteroid,
        private val application: Application) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailAsteroidViewModel::class.java)) {
                return DetailAsteroidViewModel(asteroid, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

