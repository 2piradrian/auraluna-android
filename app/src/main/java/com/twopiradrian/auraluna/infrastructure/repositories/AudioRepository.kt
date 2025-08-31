package com.twopiradrian.auraluna.infrastructure.repositories

import com.twopiradrian.auraluna.data.json.mapper.AudioMapper
import com.twopiradrian.auraluna.data.json.model.AudioModel
import com.twopiradrian.auraluna.domain.entities.Audio
import com.twopiradrian.auraluna.domain.entities.AudioCategory
import com.twopiradrian.auraluna.domain.entities.AudioType
import com.twopiradrian.auraluna.domain.repositories.AudioRepositoryI
import com.twopiradrian.auraluna.infrastructure.datasources.json.JsonAudioDatasource

class AudioRepository(
    private val audios: JsonAudioDatasource,
    private val mapper: AudioMapper
): AudioRepositoryI {

    override suspend fun getById(id: Int): Audio? {
        try {
            val model: AudioModel = this.audios.getById(id)
            return mapper.toDomain(model)
        }
        catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    override suspend fun getAll(): List<Audio> {
        try {
            val models: List<AudioModel> = this.audios.getAll()
            return models.map { mapper.toDomain(it) }
        }
        catch (e: Exception) {
            e.printStackTrace()
            return emptyList()
        }

    }

    override suspend fun getByCategories(categories: List<AudioCategory>): List<Audio> {
        try {
            val models: List<AudioModel> = this.audios.getByCategories(categories.map { it.it })
            return models.map { mapper.toDomain(it) }
        }
        catch (e: Exception) {
            e.printStackTrace()
            return emptyList()
        }
    }

    override suspend fun getByType(type: AudioType): List<Audio> {
        try {
            val models: List<AudioModel> = this.audios.getByType(type.it)
            return models.map { mapper.toDomain(it) }
        }
        catch (e: Exception) {
            e.printStackTrace()
            return emptyList()
        }
    }

}