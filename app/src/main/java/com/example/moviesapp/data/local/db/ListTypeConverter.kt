package com.example.moviesapp.data.local.db
import androidx.room.TypeConverter
import com.google.gson.Gson

/**
 * Created by Ahmed Rabee for AREEB task on 10/14/2023
 */

class ListTypeConverter {

    @TypeConverter
    fun listToJson(value: List<Int>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String): List<Int> {
        val objects = Gson().fromJson(value, Array<Int>::class.java) as Array<Int>
        return objects.toList()
    }
}
