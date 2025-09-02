package com.twopiradrian.auraluna.infrastructure.datasources.json

import android.content.Context
import com.twopiradrian.auraluna.data.json.model.AudioModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.twopiradrian.auraluna.R
import com.twopiradrian.auraluna.domain.datasources.AudioDatasourceI

class JsonAudioDatasource(
    private val context: Context
): AudioDatasourceI {

    private fun getAudioJson(): String {
        val json = R.raw.audio
        return context.resources.openRawResource(json).bufferedReader().use { it.readText() }
    }

    override suspend fun getAll(): List<AudioModel> {
        val json = getAudioJson()
        val type = object : TypeToken<List<AudioModel>>() {}.type

        return Gson().fromJson(json, type)
    }

    override suspend fun getById(id: Int): AudioModel {
        return getAll().first { it.id == id }
    }

    override suspend fun getByCategories(categories: List<String>): List<AudioModel> {
        val allAudioModels = getAll()

        return allAudioModels.filter { audioModel ->
            audioModel.categories.any { categoryInAudioModel ->
                categories.contains(categoryInAudioModel)
            }
        }
    }

    override suspend fun getByType(type: String): List<AudioModel> {
        return getAll().filter { it.type == type }
    }

}