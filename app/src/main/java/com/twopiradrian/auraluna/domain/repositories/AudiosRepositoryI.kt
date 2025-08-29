package com.twopiradrian.auraluna.domain.repositories

import com.twopiradrian.auraluna.domain.entities.Audio
import com.twopiradrian.auraluna.domain.entities.AudioCategory
import com.twopiradrian.auraluna.domain.entities.AudioType

interface AudiosRepositoryI {

    fun getById(id: Int): Audio?

    fun getAll(): List<Audio>

    fun getByCategories(categories: List<AudioCategory>): List<Audio>

    fun getByType(type: AudioType): List<Audio>

}