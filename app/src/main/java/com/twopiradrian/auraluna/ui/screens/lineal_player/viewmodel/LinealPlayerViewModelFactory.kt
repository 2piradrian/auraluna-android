package com.twopiradrian.auraluna.ui.screens.lineal_player.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.twopiradrian.auraluna.infrastructure.repositories.AudioRepository
import com.twopiradrian.auraluna.infrastructure.repositories.FavoriteRepository

class LinealPlayerViewModelFactory(
    private val favoritesRepository: FavoriteRepository,
    private val audiosRepository: AudioRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(LinealPlayerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LinealPlayerViewModel(
                favoritesRepository = favoritesRepository,
                audiosRepository = audiosRepository
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }

}