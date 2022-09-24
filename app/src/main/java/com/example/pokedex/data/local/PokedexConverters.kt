package com.example.pokedex.data.local

import androidx.room.TypeConverter
import com.example.pokedex.data.PokemonType
import com.example.pokedex.data.model.PokemonImage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
/*
    @TypeConverter
    fun fromThumbnail(thumbnailModel: ThumbnailModel): String = Gson().toJson(thumbnailModel)

    @TypeConverter
    fun toThumbnail(thumbnailModel: String): ThumbnailModel =
        Gson().fromJson(thumbnailModel, ThumbnailModel::class.java)
 */
class PokedexConverters {
//    @TypeConverter
//    fun fromType(type: List<PokemonType>): String = Gson().toJson(type)
//
//    @TypeConverter
//    fun toType(type: String) = Gson().fromJson(type, PokemonType::class.java)

//    private val gson = Gson()
//    private val type: Type = object : TypeToken<List<String>>() {}.type
//
//    @TypeConverter
//    fun fromString(json: String?): List<String> {
//        return gson.fromJson(json, type)
//    }
//
//    @TypeConverter
//    fun fromList(list: List<String?>?): String {
//        return gson.toJson(list, type)
//    }

    @TypeConverter
    fun fromString(value: List<PokemonType>): String {
        val gson = Gson()
        val type = object : TypeToken<List<PokemonType>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toString(value: String): List<PokemonType> {
        val gson = Gson()
        val type = object : TypeToken<List<PokemonType>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromImage(image: PokemonImage): String = Gson().toJson(image)

    @TypeConverter
    fun fromImage(image: String): PokemonImage = Gson().fromJson(image, PokemonImage::class.java)

    /*

    @TypeConverter
    fun fromThumbnail(thumbnailModel: ThumbnailModel): String = Gson().toJson(thumbnailModel)

    @TypeConverter
    fun toThumbnail(thumbnailModel: String): ThumbnailModel =
        Gson().fromJson(thumbnailModel, ThumbnailModel::class.java)
     */

//    @TypeConverter
//    fun fromInfoType(type: List<PokemonType>?): String {
//        val listType =
//            Types.newParameterizedType(List::class.java, PokemonType::class.java)
//        val adapter: JsonAdapter<List<PokemonInfo.TypeResponse>> = moshi.adapter(listType)
//        return adapter.toJson(type)
//    }
}
