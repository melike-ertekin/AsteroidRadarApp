package com.example.asteroidradarapp.repository.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.asteroidradarapp.domain.Asteroid
import com.example.asteroidradarapp.domain.PictureOfDay

@Entity(tableName = "asteroids")
data class DatabaseAsteroid constructor(
    @PrimaryKey
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
 * Convert database objects to domain objects
 */
fun List<DatabaseAsteroid>.asDomainModelForAsteroid(): List<Asteroid>{
    return map{
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


@Entity(tableName = "pictureOfDay")
data class DatabasePictureOfDay(
    @PrimaryKey
    val url: String,
    val mediaType: String,
    val title: String)



fun DatabasePictureOfDay.asDomainModelForPicture(): PictureOfDay{
    return PictureOfDay(
            url = this.url,
            mediaType = this.mediaType,
            title = this.title
        )

}