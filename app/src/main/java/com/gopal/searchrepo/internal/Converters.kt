package com.gopal.searchrepo.internal

import androidx.room.TypeConverter
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import com.gopal.searchrepo.data.model.Owner
import java.lang.reflect.Type


object Converters {
    val gsonObj = Gson()

    /*@TypeConverter
    fun fromString(value: String?): List<Owner> {
        val listType: Type = object : TypeToken<List<Owner?>?>() {}.type
        return gsonObj.fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: List<Owner?>?): String {
        return gsonObj.toJson(list)
    }*/


    @TypeConverter
    @JvmStatic
    fun fromString(value: String?): Owner {
        val listType: Type = object : TypeToken<Owner?>() {}.type
        return gsonObj.fromJson(value, listType)
    }

    @TypeConverter
    @JvmStatic
    fun fromArrayList(list: Owner?): String {
        return gsonObj.toJson(list)
    }
}