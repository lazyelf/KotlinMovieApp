package com.example.moviesapp.model

import android.os.Build
import android.os.Parcelable
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Entity(tableName = "Movies")
@Parcelize
@TypeConverters(DateConverter::class)
data class Movie(
    @PrimaryKey
    val id: Int,
    val movieImage: Int,
    val movieTitle: String,
    val description: String,
    val rating: Double,
    val duration: String,
    val genre: String,
    val releasedDate: LocalDate,
    val trailerLink: String,
    val isOnWishlist: Boolean
):Parcelable {
    val releasedDateFormatted : String
        @RequiresApi(Build.VERSION_CODES.O)
        get() = releasedDate.format(DateTimeFormatter.ofPattern("yyyy, d, MMMM"))
}

class DateConverter {
    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromTimestamp(value: String?): LocalDate? {
        return value?.let { LocalDate.parse(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDate?): String? {
        return date?.toString()
    }
}
