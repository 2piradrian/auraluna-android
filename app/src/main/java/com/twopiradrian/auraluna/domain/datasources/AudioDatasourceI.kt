package com.twopiradrian.auraluna.domain.datasources

import com.twopiradrian.auraluna.data.json.model.AudioModel

interface AudioDatasourceI {

    suspend fun getById(id: Int): AudioModel?

    suspend fun getAll(): List<AudioModel>

    suspend fun getByCategories(categories: List<String>): List<AudioModel>

    suspend fun getByType(type: String): List<AudioModel>

}