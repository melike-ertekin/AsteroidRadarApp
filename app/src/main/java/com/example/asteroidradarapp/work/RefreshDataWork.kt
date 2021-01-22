package com.example.asteroidradarapp.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.asteroidradarapp.repository.AsteroidRepository
import com.example.asteroidradarapp.repository.database.AsteroidDatabase.Companion.getDatabase

import retrofit2.HttpException


class RefreshDataWorker(appContext: Context, params: WorkerParameters):
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    /**
     * A coroutine-friendly method to do your work.
     */
    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val asteroidRepository = AsteroidRepository(database)
        return try {
            asteroidRepository.refreshData()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}