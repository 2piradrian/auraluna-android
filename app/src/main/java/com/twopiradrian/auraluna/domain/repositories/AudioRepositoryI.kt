package com.twopiradrian.auraluna.domain.repositories

import com.twopiradrian.auraluna.domain.entities.Audio
import com.twopiradrian.auraluna.domain.entities.AudioCategory
import com.twopiradrian.auraluna.domain.entities.AudioType

interface AudioRepositoryI {

    suspend fun getById(id: Int): Audio?

    suspend fun getAll(): List<Audio>

    suspend fun getByCategories(categories: List<AudioCategory>): List<Audio>

    suspend fun getByType(type: AudioType): List<Audio>

}