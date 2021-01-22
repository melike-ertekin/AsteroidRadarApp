package com.example.asteroidradarapp.repository.network

import com.example.asteroidradarapp.domain.Asteroid
import com.example.asteroidradarapp.domain.PictureOfDay
import com.example.asteroidradarapp.repository.database.DatabaseAsteroid
import com.example.asteroidradarapp.repository.database.DatabasePictureOfDay
import com.squareup.moshi.Json


data class NetworkAsteroid(
    val id: Long,
    val codeName: String,
    val closeApproachDate: String,
    val absoluteMagnitude: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean,
    val date: String
)

/**
 * Convert network results to domain objects
 */
fun List<NetworkAsteroid>.asDomainModel(): List<Asteroid>{
    return this.map {
        Asteroid(
            id = it.id,
            codeName = it.codeName,
            closeApproachDate =  it.closeApproachDate,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardous = it.isPotentiallyHazardous,
            date = it.date
        )
    }
}

/*
 * Convert data transfer objects(network objects) to database objects
 */
fun List<NetworkAsteroid>.asDatabaseModel(): Array<DatabaseAsteroid>{
    return this.map {
        DatabaseAsteroid(
            id = it.id,
            codeName = it.codeName,
            closeApproachDate =  it.closeApproachDate,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardous = it.isPotentiallyHazardous,
            date = it.date
        )
    }.toTypedArray()
}




data class NetworkPictureOfDay(
    val url: String,
    @Json(name = "media_type") val mediaType: String,
    val title: String
    )

/**
 * Convert network results to domain objects
 */
fun NetworkPictureOfDay.asDomainModel(): PictureOfDay {
    return PictureOfDay(
            url = this.url,
            mediaType = this.mediaType,
            title = this.title
        )

}


/*
 * Convert data transfer objects(network objects) to database objects
 */
fun NetworkPictureOfDay.asDatabaseModel(): DatabasePictureOfDay{
    return DatabasePictureOfDay(
            url = this.url,
            mediaType = this.mediaType,
            title = this.title
        )

}