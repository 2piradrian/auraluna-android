package com.twopiradrian.auraluna.infrastructure.datasources.json

import android.content.Context
import com.twopiradrian.auraluna.data.json.model.AudioModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.twopiradrian.auraluna.R

class JsonAudioDatasource(
    private val context: Context
) {

    private fun getAudioJson(): String {
        val json = R.raw.audio
        return context.resources.openRawResource(json).bufferedReader().use { it.readText() }
    }

    fun getAll(): List<AudioModel> {
        val json = getAudioJson()
        val type = object : TypeToken<List<AudioModel>>() {}.type

        return Gson().fromJson(json, type)
    }

    fun getById(id: Int): AudioModel {
        return getAll().first { it.id == id }
    }

    fun getByCategories(categories: List<String>): List<AudioModel> {
        val allAudioModels = getAll()

        return allAudioModels.filter { audioModel ->
            audioModel.categories.any { categoryInAudioModel ->
                categories.contains(categoryInAudioModel)
            }
        }
    }

    fun getByType(type: String): List<AudioModel> {
        return getAll().filter { it.type == type }
    }

}