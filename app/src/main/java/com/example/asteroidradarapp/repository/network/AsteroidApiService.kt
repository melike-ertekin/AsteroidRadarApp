package com.example.asteroidradarapp.repository.network

import com.example.asteroidradarapp.utils.Constants.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//Moshi which handles the deserialization of the returned JSON to Kotlin data objects
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//Retrofit to make api calls to an HTTP web service
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface AsteroidApiService {

    @GET("/neo/rest/v1/feed")
    suspend fun getAsteroids(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") apiKey: String
    ): String

    @GET("/planetary/apod")
    suspend fun getPictureOfTheDay(
        @Query("api_key") apiKey: String
    ): NetworkPictureOfDay
}

object AsteroidApi{
    val retrofitService: AsteroidApiService by lazy {
        retrofit.create(AsteroidApiService::class.java)
    }
}